package me.khruslan.tierlistmaker.tests.data.drag.effects

import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.drag.effects.*
import me.khruslan.tierlistmaker.data.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION
import org.junit.Assert.assertEquals
import org.junit.Test

class InsertEffectTest {

    private val shadow = ImageDragData(
        image = StorageImage(
            id = "36177a66-bbd1-4593-9cfa-652dc7bb9a95",
            filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/6990388288310.jpeg"
        ),
        itemPosition = 0,
        tierPosition = 1
    )

    @Test
    fun `When target is tier image creates InsertToTier effect`() {
        val target = ImageDragData(
            image = StorageImage(
                id = "401470da-6034-4e48-a53b-37e23834c897",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1549127750330.jpeg"
            ),
            itemPosition = 0,
            tierPosition = 4
        )
        val expectedEffect = InsertToTier(
            data = ImageDragData(
                image = shadow.image,
                itemPosition = 0,
                tierPosition = 4
            )
        )

        assertEquals(expectedEffect, InsertEffect.create(shadow, target))
    }

    @Test
    fun `When target is backlog image creates InsertToBacklog effect`() {
        val target = ImageDragData(
            image = StorageImage(
                id = "401470da-6034-4e48-a53b-37e23834c897",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1549127750330.jpeg"
            ),
            itemPosition = 0,
            tierPosition = BACKLOG_TIER_POSITION
        )
        val expectedEffect = InsertToBacklog(
            data = ImageDragData(
                image = shadow.image,
                itemPosition = 0,
                tierPosition = BACKLOG_TIER_POSITION
            )
        )

        assertEquals(expectedEffect, InsertEffect.create(shadow, target))
    }

    @Test
    fun `When target is tier creates InsertToEndOfTier effect`() {
        val target = TierDragData(tierPosition = 0)
        val expectedEffect = InsertToEndOfTier(tierPosition = 0, image = shadow.image)

        assertEquals(expectedEffect, InsertEffect.create(shadow, target))
    }

    @Test
    fun `When target is backlog creates InsertToEndOfBacklog effect`() {
        val target = TierDragData(tierPosition = BACKLOG_TIER_POSITION)
        val expectedEffect = InsertToEndOfBacklog(image = shadow.image)

        assertEquals(expectedEffect, InsertEffect.create(shadow, target))
    }

    @Test
    fun `When target is trash bin creates InsertToTrashBin effect`() {
        val target = TrashBinDragData
        val expectedEffect = InsertToTrashBin

        assertEquals(expectedEffect, InsertEffect.create(shadow, target))
    }
}