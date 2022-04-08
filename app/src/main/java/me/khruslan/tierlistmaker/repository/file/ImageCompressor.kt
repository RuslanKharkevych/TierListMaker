package me.khruslan.tierlistmaker.repository.file

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable
import me.khruslan.tierlistmaker.utils.extensions.displayWidthPixels
import me.shouheng.compress.Compress
import me.shouheng.compress.concrete
import me.shouheng.compress.strategy.config.ScaleMode
import timber.log.Timber
import java.io.File

private const val IMAGE_QUALITY_PERCENT = 90
private const val DISPLAY_WIDTH_FRACTION = 0.33f

/**
 * Compressor of image files.
 *
 * @property context application context.
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 */
class ImageCompressor(
    private val context: Context,
    private val dispatcherProvider: DispatcherProvidable
) {

    /**
     * Max size of the compressed image calculated based on [DISPLAY_WIDTH_FRACTION]
     */
    private val imageSize by lazy {
        context.displayWidthPixels * DISPLAY_WIDTH_FRACTION
    }

    /**
     * Compresses the image from [Uri] and saves the resulting file into the target directory.
     * Sets [imageSize] as max image size and [IMAGE_QUALITY_PERCENT] as image quality.
     *
     * @param uri [Uri] of the image to compress.
     * @param targetDir target directory path.
     * @return
     */
    suspend fun compress(uri: Uri, targetDir: String): File? {
        return try {
            Compress.with(context, uri)
                .setQuality(IMAGE_QUALITY_PERCENT)
                .setTargetDir(targetDir)
                .concrete {
                    withMaxWidth(imageSize)
                    withMaxHeight(imageSize)
                    withScaleMode(ScaleMode.SCALE_SMALLER)
                    withIgnoreIfSmaller(true)
                }
                .get(dispatcherProvider.io)
        } catch (e: Exception) {
            Timber.e(e, "Failed to compress $uri into $targetDir")
            null
        }
    }
}