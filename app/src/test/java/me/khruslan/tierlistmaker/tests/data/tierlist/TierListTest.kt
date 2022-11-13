package me.khruslan.tierlistmaker.tests.data.tierlist

import me.khruslan.tierlistmaker.data.tierlist.Tier
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.data.tierlist.image.StorageImage
import org.junit.Assert.assertEquals
import org.junit.Test

class TierListTest {

    @Test
    fun `TierList class has correct qualified name`() {
        val expectedQualifiedName = "me.khruslan.tierlistmaker.data.tierlist.TierList"
        val actualQualifiedName = TierList::class.qualifiedName

        assertEquals(expectedQualifiedName, actualQualifiedName)
    }

    @Test
    fun `Creates preview of the tier list`() {
        val tierList = TierList(
            id = "62cacbfa-b8fb-11ec-8422-0242ac120002",
            title = "Favorite dishes",
            zoomValue = 4,
            tiers = mutableListOf(
                Tier(
                    images = mutableListOf(
                        StorageImage(
                            id = "67cae7d3-1d75-495f-8410-34d390cff96a",
                            filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1649976463524.jpeg"
                        )
                    )
                ),
                Tier(),
                Tier(
                    images = mutableListOf(
                        StorageImage(
                            id = "eaff63e8-4229-4461-b252-d788404c1a90",
                            filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1928863159510.jpeg"
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
        val expectedPreview = TierList.Preview(
            id = "62cacbfa-b8fb-11ec-8422-0242ac120002",
            title = "Favorite dishes",
            images = listOf(
                StorageImage(
                    id = "67cae7d3-1d75-495f-8410-34d390cff96a",
                    filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1649976463524.jpeg"
                ),
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

        assertEquals(expectedPreview, tierList.preview)
    }
}