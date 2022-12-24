package me.khruslan.tierlistmaker.tests.data.work

import io.mockk.called
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.data.work.SaveTierListArgsProvider
import me.khruslan.tierlistmaker.data.work.SaveTierListArgsProviderImpl
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class SaveTierListArgsProviderTest {

    private lateinit var saveTierListArgsProvider: SaveTierListArgsProvider

    private val dummyData = listOf(
        TierList(
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
        ),
        TierList(
            id = "eaff63e8-4229-4461-b252-d788404c1a90",
            title = "Best movies",
            zoomValue = 5,
            tiers = mutableListOf(
                Tier(
                    id = "de638d0d-7dee-4f9b-83d6-795bc6ab60f0",
                    images = mutableListOf()
                )
            ),
            backlogImages = mutableListOf(
                StorageImage(
                    id = "401470da-6034-4e48-a53b-37e23834c897",
                    filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/9076065869046.jpeg"
                )
            )
        )
    )

    private fun verifyErrorWasLogged() = verify { Timber.e(any<Throwable>(), any()) }
    private fun verifyErrorWasNotLogged() = verify { Timber wasNot called }

    @Before
    fun init() {
        saveTierListArgsProvider = SaveTierListArgsProviderImpl()
        mockkObject(Timber)
    }

    @After
    fun release() {
        unmockkObject(Timber)
    }

    @Test
    fun `Sets and pops tier list`() {
        val tierList = dummyData[0]
        saveTierListArgsProvider.tierList = tierList

        assertEquals(tierList, saveTierListArgsProvider.tierList)
        verifyErrorWasNotLogged()
        assertNull(saveTierListArgsProvider.tierList)
    }

    @Test
    fun `Logs error if tier list is unavailable`() {
        assertNull(saveTierListArgsProvider.tierList)
        verifyErrorWasLogged()
    }

    @Test
    fun `Logs error if tier list is already set`() {
        val firstTierList = dummyData[0]
        val secondTierList = dummyData[1]
        saveTierListArgsProvider.tierList = firstTierList
        saveTierListArgsProvider.tierList = secondTierList

        assertEquals(secondTierList, saveTierListArgsProvider.tierList)
        verifyErrorWasLogged()
    }
}