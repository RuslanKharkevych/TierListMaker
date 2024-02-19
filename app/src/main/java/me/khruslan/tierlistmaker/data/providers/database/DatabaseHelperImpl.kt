package me.khruslan.tierlistmaker.data.providers.database

import io.paperdb.Paper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.util.performance.PerformanceService
import me.khruslan.tierlistmaker.util.performance.ReadTierListsTrace
import me.khruslan.tierlistmaker.util.performance.UpdateTierListsTrace
import timber.log.Timber
import javax.inject.Inject

/**
 * [DatabaseHelper] implementation.
 *
 * Implemented with [Paper](https://github.com/pilgr/Paper) database. On the first app launch
 * provides the default tier list collection. Moves all transactions to the background thread. Uses
 * retry policy in case transaction fails. Logs all transactions and their results. Collects
 * performance traces for all transactions. This class must be injected as a singleton.
 *
 * @property dispatcherProvider Provides [Dispatchers.IO] context.
 * @property defaultTierListCollectionProvider Provides the default tier list collection.
 * @property performanceService Traces database transactions.
 * @constructor Creates a new database helper instance.
 */
class DatabaseHelperImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val defaultTierListCollectionProvider: DefaultTierListCollectionProvider,
    private val performanceService: PerformanceService
) : DatabaseHelper {

    /**
     * Database helper constants for internal usage.
     */
    private companion object Constants {

        /**
         * Maximum number of transaction attempts before returning error.
         */
        private const val MAX_TRANSACTION_ATTEMPTS = 3

        /**
         * Key used for storing tier lists in the Paper database.
         */
        private const val KEY_TIER_LISTS = "tier-lists"
    }

    /**
     * Whether the default tier list collection has already been provided.
     */
    private val defaultTierListCollectionProvided
        get() = defaultTierListCollectionProvider.collectionProvided

    /**
     * Fetches all saved tier lists.
     *
     * If it's the first application launch, returns the default tier list collection. The full
     * algorithm is the following:
     * 1. Read all tier lists from the database.
     *    - If transaction fails, or it's successful and tier lists are not empty, or the default
     *    tier list collection has been already provided - return the result.
     *    - If tier lists are empty and the default tier list collection wasn't provided before -
     *    move to step 2.
     * 2. Provide the default tier list collection and save it in the database.
     * 3. Read all tier lists from the database again and return the result.
     *
     * @return Fetched tier lists or null if error occurred.
     */
    override suspend fun getTierLists(): MutableList<TierList>? {
        return withContext(dispatcherProvider.io) {
            Timber.i("Reading tier lists from database")
            var tierLists = readTierLists()

            if (tierLists?.isEmpty() == true && !defaultTierListCollectionProvided) {
                updateTierLists(
                    tierLists = defaultTierListCollectionProvider.provideCollection(),
                    transactionTag = "getTierLists"
                )
                tierLists = readTierLists()
            }

            tierLists
        }
    }

    /**
     * Reads all tier lists from the database.
     *
     * Transaction is traced with [ReadTierListsTrace].
     *
     * @return Fetched tier lists or null if error occurred.
     */
    private fun readTierLists(): MutableList<TierList>? {
        val trace = performanceService.startTrace(ReadTierListsTrace.NAME)
        trace.putMetric(ReadTierListsTrace.METRIC_ATTEMPTS, 1L)

        val tierLists = executeTransaction<MutableList<TierList>?>(
            transaction = {
                Paper.book().read(KEY_TIER_LISTS, mutableListOf())
            },
            onError = { error, attempt ->
                trace.putMetric(ReadTierListsTrace.METRIC_ATTEMPTS, attempt)
                logError(
                    transactionTag = "getTierLists",
                    attempt = attempt,
                    cause = error
                )
            }
        )

        trace.putAttribute(ReadTierListsTrace.ATTR_IS_SUCCESSFUL, tierLists != null)
        if (tierLists != null) trace.putMetric(ReadTierListsTrace.METRIC_COUNT, tierLists.size)
        trace.stop()
        Timber.i("Read tier lists from database: $tierLists")

        return tierLists
    }

    /**
     * Saves tier list in the database.
     *
     * Due to the limitations of the Paper database, the entire tier list collection must be
     * overwritten. The full algorithm is the following:
     * 1. Read all saved tier lists.
     *    - If transaction fails - return false.
     *    - If transaction is successful - move to step 2.
     * 2. Search a tier list by ID.
     *    - If tier list exists - replace it with the new one.
     *    - If tier list doesn't exist - insert the new tier list at the start of the list.
     * 3. Save updated tier lists in the database and return the result of this transaction.
     *
     * @param tierList Tier list to save.
     * @return Whether the tier list was saved successfully.
     */
    override suspend fun saveTierList(tierList: TierList): Boolean {
        Timber.i("Saving tier list to database: $tierList")
        return withContext(dispatcherProvider.io) {
            val tierLists = readTierLists() ?: run {
                Timber.i("Unable to read tier lists, cancelling transaction")
                return@withContext false
            }
            val index = tierLists.indexOfFirst { it.id == tierList.id }

            if (index == -1) {
                Timber.i("Adding new tier list")
                tierLists.add(0, tierList)
            } else {
                Timber.i("Replacing existing tier list at index $index")
                tierLists[index] = tierList
            }

            updateTierLists(tierLists, transactionTag = "saveTierList($tierList)")
        }
    }

    /**
     * Removes tier list from the database by identifier.
     *
     * Due to the limitations of the Paper database, the entire tier list collection must be
     * overwritten. The full algorithm is the following:
     *
     * 1. Read all saved tier lists.
     *    - If transaction fails - return false.
     *    - If transaction is successful - move to step 2.
     * 2. Search a tier list by ID.
     *    - If tier list exists - remove it.
     *    - If tier list doesn't exist - return false.
     * 3. Save updated tier lists in the database and return the result of this transaction.
     *
     * @param id Identifier of the tier list to remove.
     * @return Whether the tier list was removed successfully.
     */
    override suspend fun removeTierListById(id: String): Boolean {
        Timber.i("Removing tier list from database by id $id")
        return withContext(dispatcherProvider.io) {
            val tierLists = readTierLists() ?: run {
                Timber.i("Unable to read tier lists, cancelling transaction")
                return@withContext false
            }
            val tierList = tierLists.find { it.id == id }

            if (tierList != null) {
                Timber.i("Removing tier list: $tierList")
                tierLists.remove(tierList)
                updateTierLists(tierLists, transactionTag = "removeTierListById($id)")
            } else {
                logError("Unable to remove tier list by id: $id. Not found in $tierLists")
                false
            }
        }
    }

    /**
     * Replaces all tier lists with the new ones.
     *
     * @param tierLists Tier lists to save.
     * @return Whether tier lists were updated successfully.
     */
    override suspend fun updateTierLists(tierLists: MutableList<TierList>): Boolean {
        return withContext(dispatcherProvider.io) {
            updateTierLists(tierLists, transactionTag = "updateTierLists($tierLists)")
        }
    }

    /**
     * Updates all tier lists in the database with the new ones.
     *
     * Transaction is traced with [UpdateTierListsTrace].
     *
     * @param tierLists Tier lists to save.
     * @param transactionTag Tag used for error logging.
     * @return Whether tier lists was updated successfully.
     */
    private fun updateTierLists(tierLists: MutableList<TierList>, transactionTag: String): Boolean {
        Timber.i("Updating tier lists in database. Transaction tag: $transactionTag")
        val trace = performanceService.startTrace(UpdateTierListsTrace.NAME)
        trace.putMetric(UpdateTierListsTrace.METRIC_COUNT, tierLists.size)
        trace.putMetric(UpdateTierListsTrace.METRIC_ATTEMPTS, 1L)

        val result = executeTransaction(
            transaction = {
                Paper.book().write(KEY_TIER_LISTS, tierLists)
            },
            onError = { error, attempt ->
                trace.putMetric(UpdateTierListsTrace.METRIC_ATTEMPTS, attempt)
                logError(
                    transactionTag = transactionTag,
                    attempt = attempt,
                    cause = error
                )
            }
        )

        val isSuccessful = result != null
        trace.putAttribute(UpdateTierListsTrace.ATTR_IS_SUCCESSFUL, isSuccessful)
        trace.stop()
        Timber.i("Finished update transaction. Success: $isSuccessful")

        return isSuccessful
    }

    /**
     * Executes the [transaction] and retries in case of failure.
     *
     * Each time transaction fails [onError] callback is invoked. Returns null if transaction fails
     * [MAX_TRANSACTION_ATTEMPTS] times.
     *
     * @param T Return type of the transaction.
     * @param transaction Read/write transaction.
     * @param onError Error callback.
     * @param attempt Transaction attempt.
     * @return Result of the [transaction] or **null** if all attempts failed.
     */
    private fun <T> executeTransaction(
        transaction: () -> T,
        onError: (error: Throwable?, attempt: Int) -> Unit,
        attempt: Int = 1
    ): T? {
        val result = runCatching(transaction)

        return if (result.isSuccess) {
            result.getOrNull()
        } else {
            onError(result.exceptionOrNull(), attempt)

            if (attempt < MAX_TRANSACTION_ATTEMPTS) {
                executeTransaction(transaction, onError, attempt + 1)
            } else {
                null
            }
        }
    }

    /**
     * Logs generic error.
     *
     * @param message Error message.
     */
    private fun logError(message: String) {
        val exception = PaperException(message)
        Timber.e(exception, "Database error")
    }

    /**
     * Logs transaction error.
     *
     * @param transactionTag Tag of the transaction.
     * @param attempt Transaction attempt.
     * @param cause Cause of the transaction failure.
     */
    private fun logError(transactionTag: String, attempt: Int, cause: Throwable?) {
        val exception = PaperException(transactionTag, attempt, cause)
        Timber.e(exception, "Unable to execute transaction")
    }

    /**
     * Exception that can be thrown in case of errors inside the [DatabaseHelperImpl].
     */
    private class PaperException : Exception {

        /**
         * Creates generic [PaperException].
         *
         * @param message Error message.
         */
        constructor(message: String) : super(message)

        /**
         * Creates [PaperException] caused by transaction failure.
         *
         * @param transactionTag Tag of the transaction.
         * @param attempt Transaction attempt.
         * @param cause Cause of the transaction failure.
         */
        constructor(transactionTag: String, attempt: Int, cause: Throwable?) :
                super("Transaction failed (tag = $transactionTag, attempt = $attempt)", cause)
    }
}