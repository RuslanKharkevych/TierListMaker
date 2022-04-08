package me.khruslan.tierlistmaker.repository.file

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.CoroutineDispatcher
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.utils.extensions.displayWidthPixels
import me.shouheng.compress.Compress
import me.shouheng.compress.concrete
import me.shouheng.compress.strategy.config.ScaleMode
import timber.log.Timber
import java.io.File

private const val IMAGE_QUALITY_PERCENT = 90
private const val DISPLAY_WIDTH_FRACTION = 0.33f

/**
 * [ImageCompressor] implementation.
 *
 * @property context application context.
 * @property dispatcherProvider provider of [CoroutineDispatcher] for running suspend functions.
 */
class ImageCompressorImpl(
    private val context: Context,
    private val dispatcherProvider: DispatcherProvider
) : ImageCompressor {

    /**
     * Max size of the compressed image calculated based on [DISPLAY_WIDTH_FRACTION]
     */
    private val imageSize by lazy {
        context.displayWidthPixels * DISPLAY_WIDTH_FRACTION
    }

    override suspend fun compress(uri: Uri, targetDir: String): File? {
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