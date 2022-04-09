package me.khruslan.tierlistmaker.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.repository.db.PaperRepository
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvider

/**
 * [CoroutineWorker] implementation that saves tier list in local storage.
 * In order to pass the [TierList] to the worker set [SaveTierListArgsProvider.tierList]
 * before enqueuing the work.
 *
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 * @property argsProvider provider of arguments for this worker (used to pass [TierList]).
 * @property paperRepository repository that persists tier lists (used to save [TierList]).
 * @param appContext application context.
 * @param workerParams worker parameters.
 */
@HiltWorker
class SaveTierListWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val dispatcherProvider: DispatcherProvider,
    private val argsProvider: SaveTierListArgsProvider,
    private val paperRepository: PaperRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val tierList = argsProvider.tierList ?: return Result.failure()
        val result = withContext(dispatcherProvider.io) {
            paperRepository.saveTierList(tierList)
        }
        return if (result) Result.success() else Result.failure()
    }
}