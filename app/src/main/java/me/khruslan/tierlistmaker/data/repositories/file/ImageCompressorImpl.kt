package me.khruslan.tierlistmaker.data.repositories.file

import android.content.Context
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.utils.TIER_IMAGE_WIDTH_FRACTION
import me.khruslan.tierlistmaker.utils.displayWidthPixels
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
 */
class ImageCompressorImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatcherProvider: DispatcherProvider
) : ImageCompressor {

    /**
     * Companion object of [ImageCompressorImpl] used for storing constants.
     */
    companion object {
        private const val IMAGE_QUALITY_PERCENT = 90
    }

    /**
     * Max size of the compressed image calculated based on [TIER_IMAGE_WIDTH_FRACTION].
     */
    private val imageSize by lazy {
        context.displayWidthPixels * TIER_IMAGE_WIDTH_FRACTION
    }

    override suspend fun compress(uri: Uri, targetDir: String): File {
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
            throw ImageCompressorException(uri, targetDir, e)
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