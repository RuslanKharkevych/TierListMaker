package me.khruslan.tierlistmaker.tests.data.work

import io.mockk.called
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.data.work.UpdateTierListsArgsProvider
import me.khruslan.tierlistmaker.data.work.UpdateTierListsArgsProviderImpl
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import timber.log.Timber

class UpdateTierListsArgsProviderTest {

    private lateinit var updateTierListsArgsProvider: UpdateTierListsArgsProvider

    private val dummyData = listOf(
        listOf(
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
        ),
        listOf(
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
            ),
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
            )
        )
    )

    private fun verifyErrorWasLogged() = verify { Timber.e(any<Throwable>(), any()) }
    private fun verifyErrorWasNotLogged() = verify { Timber wasNot called }

    @Before
    fun init() {
        updateTierListsArgsProvider = UpdateTierListsArgsProviderImpl()
        mockkObject(Timber)
    }

    @After
    fun release() {
        unmockkObject(Timber)
    }

    @Test
    fun `Sets and pops tier lists`() {
        val tierLists = dummyData[0]
        updateTierListsArgsProvider.tierLists = tierLists

        assertEquals(tierLists, updateTierListsArgsProvider.tierLists)
        verifyErrorWasNotLogged()
        assertNull(updateTierListsArgsProvider.tierLists)
    }

    @Test
    fun `Logs error if tier lists are unavailable`() {
        assertNull(updateTierListsArgsProvider.tierLists)
        verifyErrorWasLogged()
    }

    @Test
    fun `Logs error if tier lists are already set`() {
        val firstTierLists = dummyData[0]
        val secondTierLists = dummyData[1]
        updateTierListsArgsProvider.tierLists = firstTierLists
        updateTierListsArgsProvider.tierLists = secondTierLists

        assertEquals(secondTierLists, updateTierListsArgsProvider.tierLists)
        verifyErrorWasLogged()
    }
}