package me.khruslan.tierlistmaker.tests.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.fakes.data.repositories.db.FakePaperRepository
import me.khruslan.tierlistmaker.ui.viewmodels.TierListActivityViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class TierListActivityViewModelTest {

    private companion object {
        private const val EXTRA_TIER_LIST = "me.khruslan.tierlistmaker.TIER_LIST"
    }

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var fakePaperRepository: FakePaperRepository
    private lateinit var viewModel: TierListActivityViewModel

    private val tierList = TierList(
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

    @Before
    fun init() {
        savedStateHandle = SavedStateHandle()
        fakePaperRepository = FakePaperRepository()
    }

    private fun initViewModel() {
        viewModel = TierListActivityViewModel(savedStateHandle, fakePaperRepository)
    }

    private fun initViewModelWithTierList() {
        savedStateHandle[EXTRA_TIER_LIST] = tierList
        initViewModel()
    }

    @Test
    fun `Throws error if SavedStateHandle doesn't contain tier list`() {
        assertThrows(IllegalStateException::class.java) {
            initViewModel()
        }
    }

    @Test
    fun `Saves tier list to the database`() {
        initViewModelWithTierList()
        viewModel.saveTierList()

        val expectedTierLists = listOf(tierList)
        val actualTierLists = fakePaperRepository.tierLists
        assertEquals(expectedTierLists, actualTierLists)
    }
}