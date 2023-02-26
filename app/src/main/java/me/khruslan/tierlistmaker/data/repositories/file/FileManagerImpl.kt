package me.khruslan.tierlistmaker.data.repositories.file

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.BuildConfig
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProvider
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

/**
 * [FileManager] implementation. All functions are running in [Dispatchers.IO] context.
 *
 * @property context application context.
 * @property imageCompressor compressor of image files.
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 */
class FileManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val imageCompressor: ImageCompressor,
    private val dispatcherProvider: DispatcherProvider
) : FileManager {

    /**
     * [FileManagerImpl] companion object. Used for storing constants.
     */
    companion object {
        private const val FILE_PROVIDER_AUTHORITY = "${BuildConfig.APPLICATION_ID}.fileprovider"
        private const val BITMAP_QUALITY = 100
    }

    override suspend fun createImageFileFromUri(uri: Uri): File? {
        return try {
            val directory = getPicturesDirectory()
            imageCompressor.compress(uri, directory.path)
        } catch (e: Exception) {
            Timber.e(e, "I/O error")
            null
        }
    }

    override suspend fun provideContentUriFromBitmap(bitmap: Bitmap, fileName: String): Uri? {
        return withContext(dispatcherProvider.io) {
            val cacheDirPath = context.cacheDir.path
            val file = File("$cacheDirPath/$fileName")

            try {
                bitmap.writeToFile(file)
                file.getContentUri()
            } catch (e: FileManagerException) {
                Timber.e(e, "I/O error")
                null
            }
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
     * Writes [Bitmap] to a given [file].
     *
     * @receiver bitmap that should be written to file.
     * @param file the file to which the bitmap should be written.
     */
    private fun Bitmap.writeToFile(file: File) {
        runCatching {
            FileOutputStream(file).use { stream ->
                compress(Bitmap.CompressFormat.JPEG, BITMAP_QUALITY, stream)
            }
        }.onSuccess { result ->
            if (!result) throw FileManagerException("Unable to compress bitmap")
        }.onFailure { throwable ->
            throw FileManagerException("Unable to write bitmap to file $file", throwable)
        }
    }

    /**
     * Returns a content [Uri] for the [File].
     *
     * @receiver a file pointing to the filename for which you want a content Uri.
     * @return A content URI of the given file.
     */
    private fun File.getContentUri(): Uri {
        return try {
            FileProvider.getUriForFile(context, FILE_PROVIDER_AUTHORITY, this)
        } catch (e: Exception) {
            throw FileManagerException("Unable to get Uri for file $this", e)
        }
    }

    /**
     * [Exception] implementation for the errors that could happen inside the [FileManagerImpl].
     *
     * @param message description of the error.
     * @param cause cause of the error.
     */
    private class FileManagerException(message: String, cause: Throwable? = null) :
        Exception(message, cause)
}