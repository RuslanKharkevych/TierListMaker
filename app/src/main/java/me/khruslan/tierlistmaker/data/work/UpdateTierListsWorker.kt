package me.khruslan.tierlistmaker.data.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import me.khruslan.tierlistmaker.data.repositories.db.PaperRepository
import timber.log.Timber

/**
 * [CoroutineWorker] implementation that updates tier lists in local storage. In order to pass
 * tier lists to the worker set [UpdateTierListsArgsProvider.tierLists] before enqueuing the work.
 *
 * @property argsProvider provider of arguments for this worker (used to pass tier lists).
 * @property paperRepository repository that persists tier lists (used to update tier lists).
 * @param appContext application context.
 * @param workerParams worker parameters.
 */
@HiltWorker
class UpdateTierListsWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val argsProvider: UpdateTierListsArgsProvider,
    private val paperRepository: PaperRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        Timber.i("Enqueued update tier lists work")
        val tierLists = argsProvider.tierLists ?: run {
            Timber.i("Couldn't get arguments, cancelling update tier lists work")
            return Result.failure()
        }

        val result = paperRepository.updateTierLists(tierLists)
        Timber.i("Update tier lists work completed. Success: $result")
        return if (result) Result.success() else Result.failure()
    }
}