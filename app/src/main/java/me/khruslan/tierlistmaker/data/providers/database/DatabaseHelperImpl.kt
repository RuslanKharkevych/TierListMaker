package me.khruslan.tierlistmaker.data.providers.database

import io.paperdb.Paper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.util.performace.PerformanceService
import me.khruslan.tierlistmaker.util.performace.ReadTierListsTrace
import me.khruslan.tierlistmaker.util.performace.UpdateTierListsTrace
import timber.log.Timber
import javax.inject.Inject

/**
 * [DatabaseHelper] implementation. Implemented with [Paper] database.
 *
 * All functions are running in [Dispatchers.IO] context.
 *
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 * @property defaultTierListCollectionProvider provider of the default tier list collection.
 * @property performanceService service that starts performance traces.
 */
class DatabaseHelperImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val defaultTierListCollectionProvider: DefaultTierListCollectionProvider,
    private val performanceService: PerformanceService
) : DatabaseHelper {

    /**
     * Companion object of [DatabaseHelperImpl] used for storing keys and other constants.
     */
    private companion object {
        private const val MAX_TRANSACTION_ATTEMPTS = 3
        private const val KEY_TIER_LISTS = "tier-lists"
    }

    /**
     * Whether the default tier list collection has already been provided.
     */
    private val defaultTierListCollectionProvided
        get() = defaultTierListCollectionProvider.collectionProvided

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

    override suspend fun saveTierList(tierList: TierList): Boolean {
        Timber.i("Saving tier list to database: $tierList")
        return withContext(dispatcherProvider.io) {
            val tierLists = getTierLists() ?: run {
                Timber.i("Unable to read tier lists, cancelling transaction")
                return@withContext false
            }
            val index = tierLists.indexOfFirst { it.id == tierList.id }

            if (index == -1) {
                Timber.i("Adding new tier list at index ${tierLists.size}")
                tierLists += tierList
            } else {
                Timber.i("Replacing existing tier list at index $index")
                tierLists[index] = tierList
            }

            updateTierLists(tierLists, transactionTag = "saveTierList($tierList)")
        }
    }

    override suspend fun removeTierListById(id: String): Boolean {
        Timber.i("Removing tier list from database by id $id")
        return withContext(dispatcherProvider.io) {
            val tierLists = getTierLists() ?: run {
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

    override suspend fun updateTierLists(tierLists: MutableList<TierList>): Boolean {
        return withContext(dispatcherProvider.io) {
            updateTierLists(tierLists, transactionTag = "updateTierLists($tierLists)")
        }
    }

    /**
     * Updates all tier lists with the new ones with retry policy (see [executeTransaction]).
     *
     * @param tierLists updated tier lists.
     * @param transactionTag tag used for error logging.
     * @return **true** if transaction was successful, **false** if transaction failed
     * [MAX_TRANSACTION_ATTEMPTS] times.
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
     * Each time transaction fails [onError] callback is invoked.
     * Returns **null** if transaction fails [MAX_TRANSACTION_ATTEMPTS] times.
     *
     * @param T return type of the transaction.
     * @param transaction [Paper] read/write transaction.
     * @param onError error callback.
     * @param attempt transaction attempt.
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
     * @param message error message.
     */
    private fun logError(message: String) {
        val exception = PaperException(message)
        Timber.e(exception, "Database error")
    }

    /**
     * Logs transaction error.
     *
     * @param transactionTag tag of the transaction.
     * @param attempt transaction attempt.
     * @param cause cause of the transaction failure.
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
         * @param message error message.
         * @constructor Creates generic [PaperException].
         */
        constructor(message: String) : super(message)

        /**
         * @param transactionTag tag of the transaction.
         * @param attempt transaction attempt.
         * @param cause cause of the transaction failure.
         * @constructor Creates [PaperException] caused by transaction failure.
         */
        constructor(transactionTag: String, attempt: Int, cause: Throwable?) :
                super("Transaction failed (tag = $transactionTag, attempt = $attempt)", cause)
    }
}