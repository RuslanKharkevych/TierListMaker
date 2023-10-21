package me.khruslan.tierlistmaker.data.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.repositories.db.PaperRepository
import timber.log.Timber

/**
 * [CoroutineWorker] implementation that saves tier list in local storage.
 * In order to pass the [TierList] to the worker set [SaveTierListArgsProvider.tierList]
 * before enqueuing the work.
 *
 * @property argsProvider provider of arguments for this worker (used to pass [TierList]).
 * @property paperRepository repository that persists tier lists (used to save [TierList]).
 * @param appContext application context.
 * @param workerParams worker parameters.
 */
@HiltWorker
class SaveTierListWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val argsProvider: SaveTierListArgsProvider,
    private val paperRepository: PaperRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Timber.i("Enqueued save tier list work")
        val tierList = argsProvider.tierList ?: run {
            Timber.i("Couldn't get arguments, cancelling save tier list work")
            return Result.failure()
        }

        val result = paperRepository.saveTierList(tierList)
        Timber.i("Save tier list work completed. Success: $result")
        return if (result) Result.success() else Result.failure()
    }
}