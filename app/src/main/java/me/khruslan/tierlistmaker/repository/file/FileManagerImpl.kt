package me.khruslan.tierlistmaker.repository.file

import android.content.Context
import android.net.Uri
import android.os.Environment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvider
import timber.log.Timber
import java.io.File

/**
 * [FileManager] implementation. All functions are running in [Dispatchers.IO] context.
 *
 * @property context application context.
 * @property imageCompressor compressor of image files.
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 */
class FileManagerImpl(
    private val context: Context,
    private val imageCompressor: ImageCompressor,
    private val dispatcherProvider: DispatcherProvider
) : FileManager {

    override suspend fun createImageFileFromUri(uri: Uri): File? {
        return try {
            val directory = getPicturesDirectory()
            imageCompressor.compress(uri, directory.path)
        } catch (e: Exception) {
            Timber.e(e, "I/O error")
            null
        }
    }

    /**
     * Attempts to get external pictures directory. No permissions are required for this action.
     *
     * @return external picture directory or null in case of error.
     */
    private suspend fun getPicturesDirectory(): File {
        return withContext(dispatcherProvider.io) {
            val result = runCatching {
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            }
            result.getOrNull() ?: throw FileManagerException(
                "Unable to access pictures directory",
                result.exceptionOrNull()
            )
        }
    }

    /**
     * [Exception] implementation for the errors that could happen inside the [FileManagerImpl].
     *
     * @param message description of the error.
     * @param cause cause of the error.
     */
    private class FileManagerException(message: String, cause: Throwable?) :
        Exception(message, cause)
}