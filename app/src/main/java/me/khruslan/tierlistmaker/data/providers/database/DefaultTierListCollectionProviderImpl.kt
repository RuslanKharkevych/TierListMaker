package me.khruslan.tierlistmaker.data.providers.database

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.util.performance.PerformanceService
import me.khruslan.tierlistmaker.util.performance.ProvideDefaultTierListCollectionTrace
import timber.log.Timber
import java.io.IOException
import java.util.UUID
import javax.inject.Inject

/**
 * [DefaultTierListCollectionProvider] implementation.
 *
 * Images for the collection are read from assets. The performance of the operation is traced.
 *
 * @property context Context for reading resources and assets.
 * @property preferencesHelper Persists [collectionProvided] flag.
 * @property performanceService Manages performance traces.
 * @constructor Creates a new provider instance.
 */
class DefaultTierListCollectionProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferencesHelper: PreferencesHelper,
    private val performanceService: PerformanceService
) : DefaultTierListCollectionProvider {

    /**
     * Assets path constants for internal usage.
     */
    private companion object Constants {

        /**
         * A path to the tier list images folder relative to the root assets folder.
         */
        private const val ASSETS_RELATIVE_PATH = "tier_list_images"

        /**
         * A full path to the tier list images folder.
         */
        private const val ASSETS_FULL_PATH = "file:///android_asset/$ASSETS_RELATIVE_PATH"
    }

    /**
     * List of parameters needed for creating default tier list collection.
     */
    private val tierListParamsList
        get() = listOf(
            TierListParams(R.string.tier_list_fruits, "fruits", 6),
            TierListParams(R.string.tier_list_animals, "animals", 5),
            TierListParams(R.string.tier_list_sports, "sports", 5),
            TierListParams(R.string.tier_list_flowers, "flowers", 5),
            TierListParams(R.string.tier_list_fast_food, "fast_food", 4),
            TierListParams(R.string.tier_list_musical_instruments, "musical_instruments", 4),
            TierListParams(R.string.tier_list_vehicles, "vehicles", 4),
            TierListParams(R.string.tier_list_school_subjects, "school_subjects", 3),
            TierListParams(R.string.tier_list_zodiac_signs, "zodiac_signs", 3)
        )

    /**
     * Whether the collection has already been provided.
     */
    override val collectionProvided
        get() = preferencesHelper.defaultTierListCollectionProvided

    /**
     * Provides a default collection and marks it as provided.
     *
     * Attempts to create all tier lists from [tierListParamsList]. If failed to create a list due
     * to an error, skips it. The operation is traced with [ProvideDefaultTierListCollectionTrace].
     *
     * @return List of created tier lists.
     */
    override fun provideCollection(): MutableList<TierList> {
        val trace = performanceService.startTrace(ProvideDefaultTierListCollectionTrace.NAME)
        preferencesHelper.markDefaultTierListCollectionAsProvided()

        val collection =  tierListParamsList.mapNotNull(::buildTierList).toMutableList()
        trace.putMetric(ProvideDefaultTierListCollectionTrace.METRIC_COUNT, collection.size)
        trace.stop()

        return collection
    }

    /**
     * Builds tier list from params.
     *
     * If unable to read tier list images from assets, logs error and returns null.
     *
     * @param params Params needed for creating tier list.
     * @return Created tier list or null in case of error.
     */
    private fun buildTierList(params: TierListParams): TierList? {
        val images = try {
            readImages(params.folderName)
        } catch (e: IOException) {
            Timber.e(e, "Unable to read tier list images from ${params.folderName} folder")
            return null
        }

        return TierList(
            id = UUID.randomUUID().toString(),
            title = context.getString(params.titleResId),
            tiers = buildTiers(params.zoomValue, images),
            zoomValue = params.zoomValue,
            backlogImages = mutableListOf()
        )
    }

    /**
     * Reads all assets from the provided folder.
     *
     * A folder must exists in [ASSETS_RELATIVE_PATH] and must not be empty. Make sure that it
     * contains only images because all files from the folder will be read.
     *
     * @param folderName Name of the folder to read assets from.
     * @return List of image file names.
     * @throws IOException When I/O error happens while listing assets or folder is empty.
     */
    private fun readImages(folderName: String): List<String> {
        val assets = context.assets.list("$ASSETS_RELATIVE_PATH/$folderName")
        if (assets.isNullOrEmpty()) throw AssetsNotFoundException()
        return assets.map { imageName -> "$ASSETS_FULL_PATH/$folderName/$imageName" }
    }

    /**
     * Builds tiers and populates them with images.
     *
     * Images will be randomly shuffled. All tiers will contain a full single row of images. The
     * exception of the last tier, which can contain less images.
     *
     * @param zoomValue Determines the number of tiers.
     * @param images List of image file names.
     * @return Created tiers.
     */
    private fun buildTiers(zoomValue: Int, images: List<String>): MutableList<Tier> {
        return images
            .shuffled()
            .chunked(zoomValue - 1)
            .map(::buildTier)
            .toMutableList()
    }

    /**
     * Builds tier and populates it with images.
     *
     * Note that tier is created with default ID and style.
     *
     * @param images List of image file names.
     * @return Created tier.
     */
    private fun buildTier(images: List<String>): Tier {
        return Tier(images = images.map { StorageImage(it) }.toMutableList())
    }

    /**
     * Parameters for creating a tier list.
     *
     * Note that this class is immutable.
     *
     * @property titleResId String resource of the tier list title.
     * @property folderName Name of the folder with tier images in [ASSETS_RELATIVE_PATH].
     * @property zoomValue Zoom value of the tier list.
     * @constructor Creates new tier list params.
     */
    private data class TierListParams(
        @StringRes val titleResId: Int,
        val folderName: String,
        val zoomValue: Int
    )

    /**
     * Thrown when assets are expected but not found in certain location.
     *
     * @constructor Creates a new exception instance.
     */
    private class AssetsNotFoundException : IOException()
}