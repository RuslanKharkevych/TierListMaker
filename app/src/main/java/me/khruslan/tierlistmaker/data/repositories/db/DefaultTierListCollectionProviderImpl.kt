package me.khruslan.tierlistmaker.data.repositories.db

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import timber.log.Timber
import java.io.IOException
import java.util.UUID
import javax.inject.Inject

/**
 * Implementation of [DefaultTierListCollectionProvider].
 *
 * @property context context for reading resources and assets.
 * @property preferencesHelper helper for managing [collectionProvided] flag.
 */
class DefaultTierListCollectionProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val preferencesHelper: PreferencesHelper
) : DefaultTierListCollectionProvider {

    /**
     * Companion object of [DefaultTierListCollectionProviderImpl] used for storing assets path.
     */
    private companion object {
        private const val ASSETS_RELATIVE_PATH = "tier_list_images"
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
            TierListParams(R.string.tier_list_fast_food, "fast_food", 4)
        )

    override val collectionProvided
        get() = preferencesHelper.defaultTierListCollectionProvided

    override fun provideCollection(): MutableList<TierList> {
        preferencesHelper.markDefaultTierListCollectionAsProvided()
        return tierListParamsList.mapNotNull(::buildTierList).toMutableList()
    }

    /**
     * Builds [TierList] from [TierListParams]. Returns **null** on error.
     *
     * @param params params needed for creating tier list.
     * @return created [TierList] or **null** in case of error.
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
     * @param folderName name of the folder to read assets from.
     * @return list of image file names.
     * @throws IOException when I/O error happens while listing assets.
     */
    private fun readImages(folderName: String): List<String> {
        val assets = context.assets.list("$ASSETS_RELATIVE_PATH/$folderName")
        if (assets.isNullOrEmpty()) throw AssetsNotFoundException()
        return assets.map { imageName -> "$ASSETS_FULL_PATH/$folderName/$imageName" }
    }

    /**
     * Builds tiers and populates them with [images]. Number of tiers depends on the [zoomValue].
     *
     * @param zoomValue determines number of tiers. Each tier will contain at most **[zoomValue] -
     * 1** images.
     * @param images list of image file names.
     * @return created tiers.
     */
    private fun buildTiers(zoomValue: Int, images: List<String>): MutableList<Tier> {
        return images
            .shuffled()
            .chunked(zoomValue - 1)
            .map(::buildTier)
            .toMutableList()
    }

    /**
     * Builds tier and populates it with [images].
     *
     * @param images list of image file names.
     * @return created tier.
     */
    private fun buildTier(images: List<String>): Tier {
        return Tier(images = images.map { StorageImage(it) }.toMutableList())
    }

    /**
     * Parameters used for creating a tier list.
     *
     * @property titleResId string resource of the tier list title.
     * @property folderName name of the folder with tier images.
     * @property zoomValue zoom value of the tier list.
     */
    private data class TierListParams(
        @StringRes val titleResId: Int,
        val folderName: String,
        val zoomValue: Int
    )

    /**
     * Thrown when assets are expected but not found in certain location.
     */
    private class AssetsNotFoundException : IOException()
}