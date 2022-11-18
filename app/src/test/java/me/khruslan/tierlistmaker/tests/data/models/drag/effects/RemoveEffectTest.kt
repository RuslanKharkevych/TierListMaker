package me.khruslan.tierlistmaker.tests.data.models.drag.effects

import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.models.drag.effects.*
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoveEffectTest {

    @Test
    fun `When target is tier image creates RemoveFromTier effect`() {
        val target = ImageDragData(
            image = StorageImage(
                id = "401470da-6034-4e48-a53b-37e23834c897",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1549127750330.jpeg"
            ),
            itemPosition = 0,
            tierPosition = 4
        )
        val expectedEffect = RemoveFromTier(
            itemPosition = 0,
            tierPosition = 4
        )

        assertEquals(expectedEffect, RemoveEffect.create(target))
    }

    @Test
    fun `When target is backlog image creates RemoveFromBacklog effect`() {
        val target = ImageDragData(
            image = StorageImage(
                id = "36177a66-bbd1-4593-9cfa-652dc7bb9a95",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/6990388288310.jpeg"
            ),
            itemPosition = 0,
            tierPosition = BACKLOG_TIER_POSITION
        )
        val expectedEffect = RemoveFromBacklog(itemPosition = 0)

        assertEquals(expectedEffect, RemoveEffect.create(target))
    }

    @Test
    fun `When target is tier creates RemoveLastFromTier effect`() {
        val target = TierDragData(tierPosition = 0)
        val expectedEffect = RemoveLastFromTier(tierPosition = 0)

        assertEquals(expectedEffect, RemoveEffect.create(target))
    }

    @Test
    fun `When target is backlog creates RemoveLastFromBacklog effect`() {
        val target = TierDragData(tierPosition = BACKLOG_TIER_POSITION)
        val expectedEffect = RemoveLastFromBacklog

        assertEquals(expectedEffect, RemoveEffect.create(target))
    }

    @Test
    fun `When target is trash bin creates UnhighlightTrashBin effect`() {
        val target = TrashBinDragData
        val expectedEffect = UnhighlightTrashBin

        assertEquals(expectedEffect, RemoveEffect.create(target))
    }
}