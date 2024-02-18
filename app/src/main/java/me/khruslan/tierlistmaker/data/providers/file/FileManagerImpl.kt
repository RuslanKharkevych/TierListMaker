package me.khruslan.tierlistmaker.data.providers.file

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.BuildConfig
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.util.performance.PerformanceService
import me.khruslan.tierlistmaker.util.performance.WriteBitmapToFileTrace
import me.shouheng.compress.utils.size
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

/**
 * [FileManager] implementation.
 *
 * Moves all operations to the background thread. Collects performance traces. No manifest
 * permissions are required to use this class.
 *
 * @property context Application context.
 * @property imageCompressor Compresses image files.
 * @property dispatcherProvider Provides [Dispatchers.IO] context.
 * @property performanceService Traces performance of the operations.
 * @constructor Creates a new file manager instance.
 */
class FileManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val imageCompressor: ImageCompressor,
    private val dispatcherProvider: DispatcherProvider,
    private val performanceService: PerformanceService
) : FileManager {

    /**
     * File manager constants for internal use.
     */
    private companion object Constants {

        /**
         * The authority of the [FileProvider].
         *
         * Must be defined in a provider element in the app's manifest.
         */
        private const val FILE_PROVIDER_AUTHORITY = "${BuildConfig.APPLICATION_ID}.fileprovider"

        /**
         * The maximum quality of a bitmap as a percentage.
         */
        private const val BITMAP_MAX_QUALITY = 100
    }

    /**
     * Saves image file from URI into the external pictures directory.
     *
     * Note that the image is compressed before it's saved.
     *
     * @param uri URI of the image file.
     * @return Created file or null in case of error.
     */
    override suspend fun createImageFileFromUri(uri: Uri): File? {
        Timber.i("Creating image file from $uri")
        return try {
            val directory = getPicturesDirectory()
            imageCompressor.compress(uri, directory.path)
        } catch (e: Exception) {
            Timber.e(e, "I/O error")
            null
        }.also { file ->
            Timber.i("Created image file: $file")
        }
    }

    /**
     * Returns a content URI for a file, created from bitmap.
     *
     * The file is saved to the cache directory.
     *
     * @param bitmap Bitmap that should be written to file.
     * @param fileName The name of the file to be created.
     * @return Content URI of the created file or null in case of error.
     */
    override suspend fun provideContentUriFromBitmap(bitmap: Bitmap, fileName: String): Uri? {
        Timber.i("Providing content Uri for file $fileName")
        return withContext(dispatcherProvider.io) {
            val cacheDirPath = context.cacheDir.path
            val file = File("$cacheDirPath/$fileName")

            try {
                bitmap.writeToFile(file)
                file.getContentUri()
            } catch (e: FileManagerException) {
                Timber.e(e, "I/O error")
                null
            }.also { uri ->
                Timber.i("Provided content Uri: $uri")
            }
        }
    }

    /**
     * Attempts to get external pictures directory.
     *
     * @return External pictures directory or null in case of error.
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
     * Writes bitmap to a given file.
     *
     * The operation is traced with [WriteBitmapToFileTrace].
     *
     * @receiver Bitmap that should be written to file.
     * @param file The file to which the bitmap should be written.
     */
    private fun Bitmap.writeToFile(file: File) {
        val trace = performanceService.startTrace(WriteBitmapToFileTrace.NAME)
        trace.putMetric(WriteBitmapToFileTrace.METRIC_BITMAP_SIZE, size())

        runCatching {
            FileOutputStream(file).use { stream ->
                compress(Bitmap.CompressFormat.JPEG, BITMAP_MAX_QUALITY, stream)
            }
        }.onSuccess { isSuccessful ->
            trace.putAttribute(WriteBitmapToFileTrace.ATTR_IS_SUCCESSFUL, isSuccessful)
            if (isSuccessful) {
                trace.putMetric(WriteBitmapToFileTrace.METRIC_FILE_SIZE, file.length())
            } else {
                throw FileManagerException("Unable to compress bitmap")
            }
        }.onFailure { throwable ->
            trace.putAttribute(WriteBitmapToFileTrace.ATTR_IS_SUCCESSFUL, false)
            throw FileManagerException("Unable to write bitmap to file $file", throwable)
        }

        trace.stop()
    }

    /**
     * Returns a content URI for the file.
     *
     * Uses [FILE_PROVIDER_AUTHORITY].
     *
     * @receiver A file pointing to the filename for which you want a content Uri.
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
     * Thrown on I/O errors caught inside the file manager.
     *
     * When a system exception is rethrown, make sure to pass it as a cause.
     *
     * @param message Description of the error.
     * @param cause Cause of the error.
     * @constructor Creates the exception with message and (optionally) cause.
     */
    private class FileManagerException(message: String, cause: Throwable? = null) :
        Exception(message, cause)
}