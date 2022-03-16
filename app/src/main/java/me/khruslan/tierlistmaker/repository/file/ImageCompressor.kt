package me.khruslan.tierlistmaker.repository.file

import android.content.Context
import android.net.Uri
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable
import me.khruslan.tierlistmaker.utils.extensions.displayWidthPixels
import me.shouheng.compress.Compress
import me.shouheng.compress.concrete
import me.shouheng.compress.strategy.config.ScaleMode
import timber.log.Timber

private const val IMAGE_QUALITY_PERCENT = 90
private const val DISPLAY_WIDTH_FRACTION = 0.33f

class ImageCompressor(
    private val context: Context,
    private val dispatcherProvider: DispatcherProvidable
) {
    private val imageSize by lazy {
        context.displayWidthPixels * DISPLAY_WIDTH_FRACTION
    }

    suspend fun compress(uri: Uri, targetDir: String) = try {
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