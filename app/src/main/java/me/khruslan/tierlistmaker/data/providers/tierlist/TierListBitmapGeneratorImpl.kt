package me.khruslan.tierlistmaker.data.providers.tierlist

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.data.models.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.data.providers.db.PreferencesHelper
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.utils.TIER_IMAGE_WIDTH_FRACTION
import me.khruslan.tierlistmaker.utils.displayWidthPixels
import me.khruslan.tierlistmaker.utils.performace.GenerateBitmapFromTierListTrace
import me.khruslan.tierlistmaker.utils.performace.PerformanceService
import me.shouheng.compress.utils.size
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.max

/**
 * Implementation of [TierListBitmapGenerator].
 *
 * @property context application context.
 * @property dispatcherProvider provider of [CoroutineDispatcher].
 * @property preferencesHelper helper class for accessing [SharedPreferences].
 * @property performanceService service that starts performance traces.
 */
class TierListBitmapGeneratorImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dispatcherProvider: DispatcherProvider,
    private val preferencesHelper: PreferencesHelper,
    private val performanceService: PerformanceService
) : TierListBitmapGenerator {

    /**
     * Size of the single image (column width) in the bitmap. Calculated based on
     * [TIER_IMAGE_WIDTH_FRACTION].
     */
    private val cellSize by lazy {
        (context.displayWidthPixels * TIER_IMAGE_WIDTH_FRACTION).toInt()
    }

    /**
     * [Paint] for the tier title text.
     */
    private val textPaint by lazy {
        Paint().apply {
            color = ContextCompat.getColor(context, R.color.grey800_dark)
            textAlign = Paint.Align.CENTER
            textSize = context.resources.getDimension(R.dimen.text_24)
            typeface = Typeface.create(
                ResourcesCompat.getFont(context, R.font.open_sans_regular),
                Typeface.BOLD
            )
        }
    }

    /**
     * [Paint] for the dark icons.
     */
    private val darkIconPaint by lazy {
        Paint().apply {
            colorFilter = PorterDuffColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN)
        }
    }

    override suspend fun generate(tierList: TierList): Bitmap {
        Timber.i("Generating bitmap from the tier list: $tierList")
        val trace = performanceService.startTrace(GenerateBitmapFromTierListTrace.NAME)

        return withContext(dispatcherProvider.io) {
            val nightModeEnabled = preferencesHelper.nightModeEnabled
            val bitmap = createBackgroundBitmap(tierList)
            val canvas = createBackgroundCanvas(bitmap, nightModeEnabled)
            val cursorPosition = PointF()

            tierList.tiers.forEach { tier ->
                canvas.drawTierHeader(tier, tierList.zoomValue, cursorPosition)
                if (tier.images.isNotEmpty()) {
                    // Moves cursor to the start of the first image.
                    cursorPosition.x += cellSize
                } else {
                    // Moves cursor to the start of the next tier.
                    cursorPosition.y += cellSize
                }

                tier.images.forEachIndexed { index, image ->
                    canvas.drawTierImage(image, cursorPosition, nightModeEnabled)
                    when {
                        index == tier.images.lastIndex -> {
                            // Moves cursor to the start of the next tier.
                            cursorPosition.x = 0f
                            cursorPosition.y += cellSize
                        }
                        index + 1 >= tierList.zoomValue - 1 && (index + 1) % (tierList.zoomValue - 1) == 0 -> {
                            // Moves cursor to the new line within the same tier.
                            cursorPosition.x = cellSize.toFloat()
                            cursorPosition.y += cellSize
                        }
                        else -> {
                            // Moves cursor to the next image within the same line.
                            cursorPosition.x += cellSize
                        }
                    }
                }
            }

            trace.putMetric(GenerateBitmapFromTierListTrace.METRIC_BITMAP_SIZE, bitmap.size())
            trace.stop()
            Timber.i("Generated bitmap. Size: ${bitmap.size()} bytes")

            bitmap
        }
    }

    /**
     * Creates background bitmap for the given tier list. The size of the bitmap is calculated
     * based [cellSize] and the number of rows and columns that tier list takes.
     *
     * @param tierList tier list which contents are used for bitmap size calculations.
     * @return Created bitmap.
     */
    private fun createBackgroundBitmap(tierList: TierList): Bitmap {
        val columnsCount = tierList.zoomValue
        val rowsCount = tierList.tiers.sumOf { it.getRowsCount(tierList.zoomValue) }

        return Bitmap.createBitmap(
            cellSize * columnsCount,
            cellSize * rowsCount,
            Bitmap.Config.ARGB_8888
        )
    }

    /**
     * Creates background canvas with the size of the provided [bitmap]. Background color is
     * determined by [nightModeEnabled] flag.
     *
     * @param bitmap bitmap for the canvas to draw into.
     * @param nightModeEnabled whether dark theme is used.
     * @return Created canvas.
     */
    private fun createBackgroundCanvas(bitmap: Bitmap, nightModeEnabled: Boolean): Canvas {
        val bgColorResId = if (nightModeEnabled) {
            R.color.grey800_dark
        } else {
            R.color.grey50
        }

        return Canvas(bitmap).apply {
            drawColor(ContextCompat.getColor(context, bgColorResId))
        }
    }

    /**
     * Draws tier header on the canvas.
     *
     * @receiver the canvas that hosts draw calls.
     * @param tier tier which header should be drawn.
     * @param zoomValue [TierList.zoomValue] used to [getRowsCount].
     * @param cursorPosition position of the tier header on canvas.
     */
    private fun Canvas.drawTierHeader(tier: Tier, zoomValue: Int, cursorPosition: PointF) {
        val bitmap = Bitmap.createBitmap(
            cellSize,
            cellSize * tier.getRowsCount(zoomValue),
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        canvas.drawColor(tier.style.color)
        canvas.drawTierTitle(tier.style.title)
        drawBitmap(bitmap, cursorPosition.x, cursorPosition.y, null)
    }

    /**
     * Draws tier title on the canvas. Text is aligned to the center of the canvas.
     *
     * @receiver the canvas that hosts draw calls.
     * @param title text to draw.
     */
    private fun Canvas.drawTierTitle(title: String) {
        val x = width / 2f
        val y = height / 2f - (textPaint.descent() + textPaint.ascent()) / 2f
        drawText(title, x, y, textPaint)
    }

    /**
     * Draws tier image on the canvas. If the image is an instance of [ResourceImage], its tint
     * is determined by [nightModeEnabled] flag.
     *
     * @receiver the canvas that hosts draw calls.
     * @param image image to draw.
     * @param cursorPosition position of the tier image on canvas.
     * @param nightModeEnabled whether dark theme is used.
     */
    private fun Canvas.drawTierImage(
        image: Image,
        cursorPosition: PointF,
        nightModeEnabled: Boolean
    ) {
        val paint = if (image is ResourceImage && !nightModeEnabled) {
            darkIconPaint
        } else {
            null
        }

        drawBitmap(image.toBitmap(), cursorPosition.x, cursorPosition.y, paint)
    }

    /**
     * Calculates number of rows that [Tier] takes.
     *
     * @receiver a tier for which rows count should be calculated.
     * @param zoomValue max number of images (including tier view) displayed in a row (see
     * [TierList.zoomValue]).
     * @return Number of rows.
     */
    private fun Tier.getRowsCount(zoomValue: Int): Int {
        return max((images.size + (zoomValue - 2)) / (zoomValue - 1), 1)
    }

    /**
     * Converts [Image] to the [Bitmap]. The output bitmap has size same as [cellSize] and is
     * center cropped.
     *
     * @receiver [Image] to convert.
     * @return output [Bitmap].
     */
    private fun Image.toBitmap(): Bitmap {
        return Glide.with(context)
            .asBitmap()
            .loadImage(this)
            .centerCrop()
            .submit(cellSize, cellSize)
            .get()
    }

    /**
     * Loads [Image] from file or resource depending on the image type.
     *
     * @receiver [RequestBuilder] created using [RequestManager.asBitmap] function.
     * @param image image to load.
     * @return the same [RequestBuilder] passed as receiver.
     */
    private fun RequestBuilder<Bitmap>.loadImage(image: Image): RequestBuilder<Bitmap> {
        return when (image) {
            is ResourceImage -> load(image.resId)
            is StorageImage -> load(image.filePath)
        }
    }
}