package me.khruslan.tierlistmaker.presentation.utils.tierlist

import android.graphics.Bitmap
import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Tool that allows to generate [Bitmap] from [TierList].
 *
 * The generation is asynchronous. The subclasses must ensure that it can be safely called from the
 * main thread.
 */
interface TierListBitmapGenerator {

    /**
     * Generates bitmap from tier list.
     *
     * Note that [TierList.backlogImages] are ignored.
     *
     * @param tierList Tier list to process.
     * @return Generated bitmap.
     */
    suspend fun generate(tierList: TierList): Bitmap
}