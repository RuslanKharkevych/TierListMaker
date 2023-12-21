package me.khruslan.tierlistmaker.presentation.utils.tierlist

import android.graphics.Bitmap
import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Tool that allows to generate [Bitmap] from [TierList].
 */
interface TierListBitmapGenerator {

    /**
     * Generates [Bitmap] from [TierList]. Note that [TierList.backlogImages] are ignored.
     *
     * @param tierList tier list to process.
     * @return generated [Bitmap].
     */
    suspend fun generate(tierList: TierList): Bitmap
}