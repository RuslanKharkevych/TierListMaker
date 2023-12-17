package me.khruslan.tierlistmaker.data.providers.file

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.data.providers.db.PreferencesHelper
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.utils.TIER_IMAGE_WIDTH_FRACTION
import me.khruslan.tierlistmaker.utils.displayWidthPixels
import me.khruslan.tierlistmaker.utils.performace.CompressImageTrace
import me.khruslan.tierlistmaker.utils.performace.PerformanceService
import me.shouheng.compress.Compress
import me.shouheng.compress.concrete
import me.shouheng.compress.strategy.config.ScaleMode
import java.io.File
import javax.inject.Inject

/**
 * [ImageCompressor] implementation.
 *
 * @property context application context.
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 * @property preferencesHelper helper class for accessing [SharedPreferences].
 */
class ImageCompressorImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatcherProvider: DispatcherProvider,
    private val preferencesHelper: PreferencesHelper,
    private val performanceService: PerformanceService
) : ImageCompressor {

    /**
     * Max size of the compressed image calculated based on [TIER_IMAGE_WIDTH_FRACTION].
     */
    private val imageSize by lazy {
        context.displayWidthPixels * TIER_IMAGE_WIDTH_FRACTION
    }

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
     * @return image quality in percents.
     */
    private suspend fun getImageQuality(): Int {
        return withContext(dispatcherProvider.io) {
            preferencesHelper.imageQuality
        }
    }

    /**
     * Exception that should be used for errors that happen during compressing of an image.
     *
     * @param uri [Uri] of an image to compress.
     * @param targetDir target directory of the output file.
     * @param cause cause of an error.
     */
    private class ImageCompressorException(uri: Uri, targetDir: String, cause: Throwable) :
        Exception("Failed to compress $uri into $targetDir", cause)
}