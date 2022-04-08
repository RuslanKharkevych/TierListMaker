package me.khruslan.tierlistmaker.repository.file

import android.content.Context
import android.net.Uri
import android.os.Environment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable
import timber.log.Timber
import java.io.File

/**
 * Manager for working with files. Used for persisting tier list images.
 * All functions are running in [Dispatchers.IO] context.
 *
 * @property context application context.
 * @property imageCompressor compressor of image files.
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 */
class FileManager(
    private val context: Context,
    private val imageCompressor: ImageCompressor,
    private val dispatcherProvider: DispatcherProvidable
) {

    /**
     * [FileManager] companion object. Used for storing constants.
     */
    companion object {
        const val MIME_TYPE_IMAGE = "image/*"
    }

    /**
     * Saves [File] from [Uri] into the external pictures directory.
     * This function should be used only for creating image files.
     *
     * @param uri [Uri] of the image file.
     * @return created [File] or null in case of error.
     */
    suspend fun createFileFromUri(uri: Uri): File? {
        val directory = getPicturesDirectory()
        return if (directory != null) {
            imageCompressor.compress(uri, directory.path)
        } else {
            Timber.e("Unable to create file form $uri: directory is null")
            null
        }
    }

    /**
     * Attempts to get external pictures directory. No permissions are required for this action.
     *
     * @return external picture directory or null in case of error.
     */
    private suspend fun getPicturesDirectory(): File? {
        return withContext(dispatcherProvider.io) {
            try {
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            } catch (e: Exception) {
                Timber.e(e, "Unable to access pictures directory")
                null
            }
        }
    }
}