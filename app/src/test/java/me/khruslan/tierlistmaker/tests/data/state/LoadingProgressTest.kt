package me.khruslan.tierlistmaker.tests.data.state

import me.khruslan.tierlistmaker.data.state.LoadingProgress
import me.khruslan.tierlistmaker.dataproviders.data.StateDataProvider.LoadingProgressPercent
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
@RunWith(Parameterized::class)
class LoadingProgressTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = LoadingProgressPercent.data
    }

    @Parameterized.Parameter(LoadingProgressPercent.currentItemIndexParam)
    lateinit var currentItem: Integer

    @Parameterized.Parameter(LoadingProgressPercent.totalItemsCountParam)
    lateinit var totalItems: Integer

    @Parameterized.Parameter(LoadingProgressPercent.loadingProgressPercentParam)
    lateinit var percent: Integer

    @Test
    fun `Calculates loading percent`() {
        val loadingProgress = LoadingProgress(currentItem.toInt(), totalItems.toInt())
        assertEquals(percent, loadingProgress.percent)
    }
}