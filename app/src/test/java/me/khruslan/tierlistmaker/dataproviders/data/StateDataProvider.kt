package me.khruslan.tierlistmaker.dataproviders.data

import me.khruslan.tierlistmaker.dataproviders.DataProvider
import me.khruslan.tierlistmaker.dataproviders.DataProviderParams

object StateDataProvider : DataProvider("data/state") {

    val currentItemIndices: Array<Int> by lazy { readJson("current-item-indices") }
    val loadingProgressPercent: Array<Int> by lazy { readJson("loading-progress-percent") }
    val totalItemsCount: Array<Int> by lazy { readJson("total-items-count") }

    object LoadingProgressPercent : DataProviderParams() {
        const val currentItemIndexParam = 0
        const val totalItemsCountParam = 1
        const val loadingProgressPercentParam = 2

        override val data
            get() = mergedData(currentItemIndices, totalItemsCount, loadingProgressPercent)
    }
}