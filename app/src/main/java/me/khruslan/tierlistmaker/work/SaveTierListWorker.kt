package me.khruslan.tierlistmaker.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.repository.db.PaperRepository
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable

@HiltWorker
class SaveTierListWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val dispatcherProvider: DispatcherProvidable,
    private val argsProvider: SaveTierListArgsProvider,
    private val paperRepository: PaperRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        val tierList = argsProvider.tierList ?: return Result.failure()
        withContext(dispatcherProvider.io) {
            paperRepository.saveTierList(tierList)
        }
        return Result.success()
    }
}