package me.khruslan.tierlistmaker.tests.data.drag.effects

import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.drag.effects.*
import me.khruslan.tierlistmaker.data.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION
import org.junit.Assert.assertEquals
import org.junit.Test

class HighlightEffectTest {

    @Test
    fun `When target is tier image creates HighlightInTier effect`() {
        val target = ImageDragData(
            image = StorageImage(
                id = "401470da-6034-4e48-a53b-37e23834c897",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1549127750330.jpeg"
            ),
            itemPosition = 0,
            tierPosition = 4
        )
        val expectedEffect = HighlightInTier(
            itemPosition = 0,
            tierPosition = 4
        )

        assertEquals(expectedEffect, HighlightEffect.create(target))
    }

    @Test
    fun `When target is backlog image creates HighlightInBacklog effect`() {
        val target = ImageDragData(
            image = StorageImage(
                id = "36177a66-bbd1-4593-9cfa-652dc7bb9a95",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/6990388288310.jpeg"
            ),
            itemPosition = 0,
            tierPosition = BACKLOG_TIER_POSITION
        )
        val expectedEffect = HighlightInBacklog(itemPosition = 0)

        assertEquals(expectedEffect, HighlightEffect.create(target))
    }

    @Test
    fun `When target is tier creates HighlightLastInTier effect`() {
        val target = TierDragData(tierPosition = 0)
        val expectedEffect = HighlightLastInTier(tierPosition = 0)

        assertEquals(expectedEffect, HighlightEffect.create(target))
    }

    @Test
    fun `When target is backlog creates HighlightLastInBacklog effect`() {
        val target = TierDragData(tierPosition = BACKLOG_TIER_POSITION)
        val expectedEffect = HighlightLastInBacklog

        assertEquals(expectedEffect, HighlightEffect.create(target))
    }

    @Test
    fun `When target is trash bin creates HighlightTrashBin effect`() {
        val target = TrashBinDragData
        val expectedEffect = HighlightTrashBin

        assertEquals(expectedEffect, HighlightEffect.create(target))
    }
}