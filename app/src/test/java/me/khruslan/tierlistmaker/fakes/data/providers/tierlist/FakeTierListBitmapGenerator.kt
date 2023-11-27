package me.khruslan.tierlistmaker.fakes.data.providers.tierlist

import android.graphics.Bitmap
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListBitmapGenerator

class FakeTierListBitmapGenerator : TierListBitmapGenerator {
    var bitmaps = mapOf<TierList, Bitmap>()
    override suspend fun generate(tierList: TierList) = checkNotNull(bitmaps[tierList])
}