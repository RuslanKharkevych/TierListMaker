package me.khruslan.tierlistmaker.fakes.presentation.utils.tierlist

import android.graphics.Bitmap
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.presentation.utils.tierlist.TierListBitmapGenerator

class FakeTierListBitmapGenerator : TierListBitmapGenerator {
    var bitmaps = mapOf<TierList, Bitmap>()
    override suspend fun generate(tierList: TierList) = checkNotNull(bitmaps[tierList])
}