package me.khruslan.tierlistmaker.data.providers.file

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.providers.database.PreferencesHelper
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.util.TIER_IMAGE_WIDTH_FRACTION
import me.khruslan.tierlistmaker.util.displayWidthPixels
import me.khruslan.tierlistmaker.util.performance.CompressImageTrace
import me.khruslan.tierlistmaker.util.performance.PerformanceService
import me.shouheng.compress.Compress
import me.shouheng.compress.concrete
import me.shouheng.compress.strategy.config.ScaleMode
import java.io.File
import javax.inject.Inject

/**
 * [ImageCompressor] implementation.
 *
 * Implemented with [Compressor](https://github.com/Shouheng88/Compressor) library. Moves the
 * compression to the background thread. Traces the performance of the compression.
 *
 * @property context Application context.
 * @property dispatcherProvider Provides IO dispatcher.
 * @property preferencesHelper Reads image quality preference.
 * @constructor Creates a new image compressor instance.
 */
class ImageCompressorImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatcherProvider: DispatcherProvider,
    private val preferencesHelper: PreferencesHelper,
    private val performanceService: PerformanceService
) : ImageCompressor {

    /**
     * Maximum size of the compressed image.
     *
     * Calculated based on the display width and [TIER_IMAGE_WIDTH_FRACTION].
     */
    private val imageSize by lazy {
        context.displayWidthPixels * TIER_IMAGE_WIDTH_FRACTION
    }

    /**
     * Compresses the image from Uri and saves the resulting file into the target directory.
     *
     * The quality of the image depends on user preference. The image is scaled down to the
     * [imageSize]. The operation is traced with [CompressImageTrace].
     *
     * @param uri URI of the image to compress.
     * @param targetDir Target directory path.
     * @return Compressed file.
     * @throws [ImageCompressorException] when compression fails.
     */
    override suspend fun compress(uri: Uri, targetDir: String): File {
        val trace = performanceService.startTrace(CompressImageTrace.NAME)
        return try {
            Compress.with(context, uri)
                .setQuality(getImageQuality())
                .setTargetDir(targetDir)
                .concrete {
                    withMaxWidth(imageSize)
                    withMaxHeight(imageSize)
                    withScaleMode(ScaleMode.SCALE_SMALLER)
                    withIgnoreIfSmaller(true)
                }
                .get(dispatcherProvider.io)
                .also { file ->
                    trace.putAttribute(CompressImageTrace.ATTR_IS_SUCCESSFUL, true)
                    trace.putMetric(CompressImageTrace.METRIC_FILE_SIZE, file.length())
                }
        } catch (e: Exception) {
            trace.putAttribute(CompressImageTrace.ATTR_IS_SUCCESSFUL, false)
            throw ImageCompressorException(uri, targetDir, e)
        } finally {
            trace.stop()
        }
    }

    /**
     * Asynchronously fetches image quality from user preferences.
     *
     * @return Image quality as a percentage.
     */
    private suspend fun getImageQuality(): Int {
        return withContext(dispatcherProvider.io) {
            preferencesHelper.imageQuality
        }
    }

    /**
     * Thrown when image compression fails.
     *
     * Exception message is built from the constructor parameters.
     *
     * @param uri URI of an image that should have been compressed.
     * @param targetDir Target directory of the output file.
     * @param cause Cause of an error.
     * @constructor Creates a new exception instance.
     */
    private class ImageCompressorException(uri: Uri, targetDir: String, cause: Throwable) :
        Exception("Failed to compress $uri into $targetDir", cause)
}