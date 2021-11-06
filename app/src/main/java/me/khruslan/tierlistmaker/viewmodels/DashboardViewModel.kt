package me.khruslan.tierlistmaker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.khruslan.tierlistmaker.data.TierListPreview

class DashboardViewModel: ViewModel() {
    val tierListPreviewsLiveData = liveData {
        emit(dummyTierListPreviews)
    }

    private val dummyTierListPreviews
        get() = List(10) {
            TierListPreview(
                title = "Best Friends",
                imageUrls = List(3) {
                    "https://cdn.pixabay.com/photo/2015/07/20/13/01/fred-852770_1280.jpg"
                }
            )
        }
}