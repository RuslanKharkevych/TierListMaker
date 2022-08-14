package me.khruslan.tierlistmaker.dataproviders.viewmodels

import me.khruslan.tierlistmaker.dataproviders.DataProvider
import me.khruslan.tierlistmaker.dataproviders.DataProviderParams
import me.khruslan.tierlistmaker.data.tierlist.TierList

@Suppress("ObjectPropertyName")
object DashboardViewModelDataProvider: DataProvider("viewmodels/dashboard") {

    val invalidPositions: Array<Int> by lazy { readJson("invalid-positions") }
    val tierListNames: Array<String> by lazy { readJson("tier-list-names") }
    val validPositions: Array<Int> by lazy { readJson("valid-positions") }

    private val _addedLists: Array<TierList> by lazy { readJson("added-lists") }
    val addedLists get() = _addedLists.map { it.copy() }.toTypedArray()

    private val _lists: Array<TierList> by lazy { readJson("lists") }
    val lists get() = _lists.map { it.copy() }.toTypedArray()

    private val _previews: Array<TierList.Preview> by lazy { readJson("previews") }
    val previews get() = _previews.map { it.copy() }.toTypedArray()

    private val _updatedLists: Array<TierList> by lazy { readJson("updated-lists") }
    val updatedLists get() = _updatedLists.map { it.copy() }.toTypedArray()

    object ValidPositions: DataProviderParams() {
        const val positionParam = 0
        const val tierListParam = 1

        override val data get() = mergedData(validPositions, lists)
    }

    object UpdatedLists: DataProviderParams() {
        const val positionParam = 0
        const val updatedListParam = 1

        override val data get() = mergedData(validPositions, updatedLists)
    }
}