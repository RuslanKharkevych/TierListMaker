package me.khruslan.tierlistmaker.tests.data.repositories.tierlist

import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.effects.*
import me.khruslan.tierlistmaker.data.models.tierlist.*
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.data.models.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.data.repositories.tierlist.TierListProcessor
import me.khruslan.tierlistmaker.data.repositories.tierlist.TierListProcessorImpl
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TierListProcessorTest {

    private lateinit var actualTierList: TierList
    private lateinit var tierListProcessor: TierListProcessor

    private val initialTierList
        get() = TierList(
            id = "62cacbfa-b8fb-11ec-8422-0242ac120002",
            title = "Favorite dishes",
            zoomValue = 4,
            tiers = mutableListOf(
                Tier(
                    id = "b4b864b8-16dd-47ca-85ae-ee4f96ed44d4",
                    images = mutableListOf(
                        StorageImage(
                            id = "67cae7d3-1d75-495f-8410-34d390cff96a",
                            filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1649976463524.jpeg"
                        )
                    )
                ),
                Tier(
                    id = "ab2a1957-0743-4812-b20c-5816002155a1",
                    images = mutableListOf(
                        StorageImage(
                            id = "eaff63e8-4229-4461-b252-d788404c1a90",
                            filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1928863159510.jpeg"
                        ),
                        StorageImage(
                            id = "401470da-6034-4e48-a53b-37e23834c897",
                            filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/9076065869046.jpeg"
                        )
                    )
                )
            ),
            backlogImages = mutableListOf(
                StorageImage(
                    id = "401470da-6034-4e48-a53b-37e23834c897",
                    filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/9076065869046.jpeg"
                ),
                StorageImage(
                    id = "de638d0d-7dee-4f9b-83d6-795bc6ab60f0",
                    filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/9226934535226.png"
                )
            )
        )

    private fun assertIsTargetImage(image: Image) {
        if (image !is ResourceImage || image.resId != R.drawable.ic_crop_free) {
            fail("$image is not a target image.")
        }
    }

    @Before
    fun init() {
        tierListProcessor = TierListProcessorImpl()
        actualTierList = initialTierList
        tierListProcessor.setTierList(actualTierList)
    }

    @Test
    fun `When effect is HighlightInBacklog inserts target image into backlog at given position`() {
        val effect = HighlightInBacklog(itemPosition = 1)
        val expectedEvent = BacklogItemInserted(itemPosition = 1)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers, actualTierList.tiers)
        assertEquals(initialTierList.backlogImages.size + 1, actualTierList.backlogImages.size)
        assertEquals(initialTierList.backlogImages[0], actualTierList.backlogImages[0])
        assertIsTargetImage(actualTierList.backlogImages[1])
        assertEquals(initialTierList.backlogImages[1], actualTierList.backlogImages[2])
    }

    @Test
    fun `When effect is HighlightInTier inserts target image into the tier at given position`() {
        val effect = HighlightInTier(tierPosition = 1, itemPosition = 1)
        val expectedEvent = TierChanged(tierPosition = 1)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers[0], actualTierList.tiers[0])
        assertEquals(initialTierList.tiers[1].images.size + 1, actualTierList.tiers[1].images.size)
        assertEquals(initialTierList.tiers[1].images[0], actualTierList.tiers[1].images[0])
        assertIsTargetImage(actualTierList.tiers[1].images[1])
        assertEquals(initialTierList.tiers[1].images[1], actualTierList.tiers[1].images[2])
        assertEquals(initialTierList.backlogImages, actualTierList.backlogImages)
    }

    @Test
    fun `When effect is HighlightLastInTier inserts target image into the end of tier`() {
        val effect = HighlightLastInTier(tierPosition = 0)
        val expectedEvent = TierChanged(tierPosition = 0)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers[0].images.size + 1, actualTierList.tiers[0].images.size)
        assertEquals(initialTierList.tiers[0].images[0], actualTierList.tiers[0].images[0])
        assertIsTargetImage(actualTierList.tiers[0].images[1])
        assertEquals(initialTierList.tiers[1], actualTierList.tiers[1])
        assertEquals(initialTierList.backlogImages, actualTierList.backlogImages)
    }

    @Test
    fun `When effect is HighlightLastInBacklog inserts target image into the end of backlog`() {
        val effect = HighlightLastInBacklog
        val expectedEvent = BacklogItemInserted(itemPosition = 2)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers, actualTierList.tiers)
        assertEquals(initialTierList.backlogImages.size + 1, actualTierList.backlogImages.size)
        assertEquals(initialTierList.backlogImages[0], actualTierList.backlogImages[0])
        assertEquals(initialTierList.backlogImages[1], actualTierList.backlogImages[1])
        assertIsTargetImage(actualTierList.backlogImages[2])
    }

    @Test
    fun `When effect is HighlightTrashBin highlights trash bin`() {
        val effect = HighlightTrashBin
        val expectedEvent = TrashBinHighlightUpdated(true)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList, actualTierList)
    }

    @Test
    fun `When effect is InsertToBacklog inserts image into the backlog at given position`() {
        val effect = InsertToBacklog(
            data = ImageDragData(
                image = StorageImage(
                    id = "9f23f150-a091-4d66-98ba-0d2b775bcf4c",
                    filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/4766531011764.jpeg"
                ),
                itemPosition = 0,
                tierPosition = BACKLOG_TIER_POSITION
            )
        )
        val expectedEvent = BacklogItemInserted(itemPosition = 0)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers, actualTierList.tiers)
        assertEquals(initialTierList.backlogImages.size + 1, actualTierList.backlogImages.size)
        assertEquals(effect.data.image, actualTierList.backlogImages[0])
        assertEquals(initialTierList.backlogImages[0], actualTierList.backlogImages[1])
        assertEquals(initialTierList.backlogImages[1], actualTierList.backlogImages[2])
    }

    @Test
    fun `When effect is InsertToTier inserts image into the tier at given position`() {
        val effect = InsertToTier(
            data = ImageDragData(
                image = StorageImage(
                    id = "9f23f150-a091-4d66-98ba-0d2b775bcf4c",
                    filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/4766531011764.jpeg"
                ),
                itemPosition = 0,
                tierPosition = 0
            )
        )
        val expectedEvent = TierChanged(tierPosition = 0)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers[0].images.size + 1, actualTierList.tiers[0].images.size)
        assertEquals(effect.data.image, actualTierList.tiers[0].images[0])
        assertEquals(initialTierList.tiers[0].images[0], actualTierList.tiers[0].images[1])
        assertEquals(initialTierList.tiers[1], actualTierList.tiers[1])
        assertEquals(initialTierList.backlogImages, actualTierList.backlogImages)
    }

    @Test
    fun `When effect is InsertToEndOfBacklog inserts image into the end of backlog`() {
        val effect = InsertToEndOfBacklog(
            image = StorageImage(
                id = "9f23f150-a091-4d66-98ba-0d2b775bcf4c",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/4766531011764.jpeg"
            )
        )
        val expectedEvent = BacklogItemInserted(itemPosition = 2)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers, actualTierList.tiers)
        assertEquals(initialTierList.backlogImages.size + 1, actualTierList.backlogImages.size)
        assertEquals(initialTierList.backlogImages[0], actualTierList.backlogImages[0])
        assertEquals(initialTierList.backlogImages[1], actualTierList.backlogImages[1])
        assertEquals(effect.image, actualTierList.backlogImages[2])
    }

    @Test
    fun `When effect is InsertToEndOfTier inserts image into the end of tier`() {
        val effect = InsertToEndOfTier(
            image = StorageImage(
                id = "9f23f150-a091-4d66-98ba-0d2b775bcf4c",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/4766531011764.jpeg"
            ),
            tierPosition = 1
        )
        val expectedEvent = TierChanged(tierPosition = 1)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers[0], actualTierList.tiers[0])
        assertEquals(initialTierList.tiers[1].images.size + 1, actualTierList.tiers[1].images.size)
        assertEquals(initialTierList.tiers[1].images[0], actualTierList.tiers[1].images[0])
        assertEquals(initialTierList.tiers[1].images[1], actualTierList.tiers[1].images[1])
        assertEquals(effect.image, actualTierList.tiers[1].images[2])
        assertEquals(initialTierList.backlogImages, actualTierList.backlogImages)
    }

    @Test
    fun `When effect is ThrowToTrashBin removes image`() {
        val effect = ThrowToTrashBin
        val expectedEvent = ImageRemoved
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList, actualTierList)
    }

    @Test
    fun `When effect is RemoveFromBacklog removes image at given position from backlog`() {
        val effect = RemoveFromBacklog(itemPosition = 0)
        val expectedEvent = BacklogDataChanged
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers, actualTierList.tiers)
        assertEquals(initialTierList.backlogImages.size - 1, actualTierList.backlogImages.size)
        assertEquals(initialTierList.backlogImages[1], actualTierList.backlogImages[0])
    }

    @Test
    fun `When effect is RemoveFromTier removes image at given position from the tier`() {
        val effect = RemoveFromTier(tierPosition = 1, itemPosition = 0)
        val expectedEvent = TierChanged(tierPosition = 1)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers[0], actualTierList.tiers[0])
        assertEquals(initialTierList.tiers[1].images.size - 1, actualTierList.tiers[1].images.size)
        assertEquals(initialTierList.tiers[1].images[1], actualTierList.tiers[1].images[0])
        assertEquals(initialTierList.backlogImages, actualTierList.backlogImages)
    }

    @Test
    fun `When effect is RemoveLastFromBacklog removes the last image from the backlog`() {
        val effect = RemoveLastFromBacklog
        val expectedEvent = BacklogDataChanged
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers, actualTierList.tiers)
        assertEquals(initialTierList.backlogImages.size - 1, actualTierList.backlogImages.size)
        assertEquals(initialTierList.backlogImages[0], actualTierList.backlogImages[0])
    }

    @Test
    fun `When effect is RemoveLastFromTier removes the last image from tier`() {
        val effect = RemoveLastFromTier(tierPosition = 0)
        val expectedEvent = TierChanged(tierPosition = 0)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertTrue(actualTierList.tiers[0].images.isEmpty())
        assertEquals(initialTierList.tiers[1], actualTierList.tiers[1])
        assertEquals(initialTierList.backlogImages, actualTierList.backlogImages)
    }

    @Test
    fun `When effect is UnhighlightTrashBin removes highlight from the trash bin`() {
        val effect = UnhighlightTrashBin
        val expectedEvent = TrashBinHighlightUpdated(false)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList, actualTierList)
    }

    @Test
    fun `When effect is UpdateInBacklog updates backlog image at given position`() {
        val effect = UpdateInBacklog(
            data = ImageDragData(
                image = StorageImage(
                    id = "9f23f150-a091-4d66-98ba-0d2b775bcf4c",
                    filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/4766531011764.jpeg"
                ),
                itemPosition = 0,
                tierPosition = BACKLOG_TIER_POSITION
            )
        )
        val expectedEvent = BacklogDataChanged
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers, actualTierList.tiers)
        assertEquals(initialTierList.backlogImages.size, actualTierList.backlogImages.size)
        assertEquals(effect.data.image, actualTierList.backlogImages[0])
        assertEquals(initialTierList.backlogImages[1], actualTierList.backlogImages[1])
    }

    @Test
    fun `When effect is UpdateInTier updates tier image at given position`() {
        val effect = UpdateInTier(
            data = ImageDragData(
                image = StorageImage(
                    id = "9f23f150-a091-4d66-98ba-0d2b775bcf4c",
                    filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/4766531011764.jpeg"
                ),
                itemPosition = 0,
                tierPosition = 0
            )
        )
        val expectedEvent = TierChanged(tierPosition = 0)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers[0].images.size, actualTierList.tiers[0].images.size)
        assertEquals(effect.data.image, actualTierList.tiers[0].images[0])
        assertEquals(initialTierList.tiers[1], actualTierList.tiers[1])
        assertEquals(initialTierList.backlogImages, actualTierList.backlogImages)
    }

    @Test
    fun `When effect is UpdateLastInBacklog updates the last image in backlog`() {
        val effect = UpdateLastInBacklog(
            image = StorageImage(
                id = "9f23f150-a091-4d66-98ba-0d2b775bcf4c",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/4766531011764.jpeg"
            )
        )
        val expectedEvent = BacklogDataChanged
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers, actualTierList.tiers)
        assertEquals(initialTierList.backlogImages.size, actualTierList.backlogImages.size)
        assertEquals(initialTierList.backlogImages[0], actualTierList.backlogImages[0])
        assertEquals(effect.image, actualTierList.backlogImages[1])
    }

    @Test
    fun `When effect is UpdateLastInTier updates the last image in tier`() {
        val effect = UpdateLastInTier(
            image = StorageImage(
                id = "9f23f150-a091-4d66-98ba-0d2b775bcf4c",
                filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/4766531011764.jpeg"
            ),
            tierPosition = 1
        )
        val expectedEvent = TierChanged(tierPosition = 1)
        val actualEvent = tierListProcessor.processDragEffect(effect)

        assertEquals(expectedEvent, actualEvent)
        assertEquals(initialTierList.tiers[0], actualTierList.tiers[0])
        assertEquals(initialTierList.tiers[1].images.size, actualTierList.tiers[1].images.size)
        assertEquals(initialTierList.tiers[1].images[0], actualTierList.tiers[1].images[0])
        assertEquals(effect.image, actualTierList.tiers[1].images[1])
        assertEquals(initialTierList.backlogImages, actualTierList.backlogImages)
    }
}