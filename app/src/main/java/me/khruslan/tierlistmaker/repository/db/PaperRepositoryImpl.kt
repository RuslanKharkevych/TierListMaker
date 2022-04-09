package me.khruslan.tierlistmaker.repository.db

import io.paperdb.Paper
import kotlinx.coroutines.*
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvider
import timber.log.Timber

private const val MAX_TRANSACTION_ATTEMPTS = 3
private const val KEY_TIER_LISTS = "tier-lists"

/**
 * [PaperRepository] implementation. Implemented with [Paper] database.
 *
 * All functions are running in [Dispatchers.IO] context.
 *
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 */
class PaperRepositoryImpl(private val dispatcherProvider: DispatcherProvider) : PaperRepository {

    override suspend fun getTierLists(): MutableList<TierList>? {
        return withContext(dispatcherProvider.io) {
            executeTransaction(
                transaction = {
                    Paper.book().read(KEY_TIER_LISTS, mutableListOf())
                },
                onError = { error, attempt ->
                    Timber.e(error, "Failed to getTierLists, attempt = $attempt")
                }
            )
        }
    }

    override suspend fun saveTierList(tierList: TierList): Boolean {
        return withContext(dispatcherProvider.io) {
            val tierLists = getTierLists() ?: return@withContext false
            val index = tierLists.indexOfFirst { it.id == tierList.id }

            if (index == -1) {
                tierLists += tierList
            } else {
                tierLists[index] = tierList
            }

            val result = executeTransaction(
                transaction = {
                    Paper.book().write(KEY_TIER_LISTS, tierLists)
                },
                onError = { error, attempt ->
                    Timber.e(error, "Failed to saveTierList($tierList), attempt = $attempt")
                }
            )

            return@withContext result != null
        }
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

            if (attempt <= MAX_TRANSACTION_ATTEMPTS) {
                executeTransaction(transaction, onError, attempt + 1)
            } else {
                null
            }
        }
    }
}