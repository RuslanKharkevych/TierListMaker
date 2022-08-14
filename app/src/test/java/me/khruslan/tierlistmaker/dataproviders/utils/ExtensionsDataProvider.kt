package me.khruslan.tierlistmaker.dataproviders.utils

import androidx.lifecycle.SavedStateHandle
import me.khruslan.tierlistmaker.dataproviders.DataProvider
import me.khruslan.tierlistmaker.dataproviders.DataProviderParams

@Suppress("ObjectPropertyName")
object ExtensionsDataProvider : DataProvider("utils/extensions") {

    val convertedIntegers: Array<Int> by lazy { readJson("converted-integers") }
    val firstSwapIndices: Array<Int> by lazy { readJson("first-swap-indices") }
    val invalidNumberCharSequences: Array<String> by lazy { readJson("invalid-number-char-sequences") }
    val invalidSavedStateHandleKeys: Array<String> by lazy { readJson("invalid-saved-state-handle-keys") }
    val savedStateHandleValues: Array<String> by lazy { readJson("saved-state-handle-values") }
    val secondSwapIndices: Array<Int> by lazy { readJson("second-swap-indices") }
    val updateLastValues: Array<String> by lazy { readJson("update-last-values") }
    val validNumberCharSequences: Array<String> by lazy { readJson("valid-number-char-sequences") }
    val validSavedStateHandleKeys: Array<String> by lazy { readJson("valid-saved-state-handle-keys") }

    private val _listsAfterUpdateLast: Array<List<String>> by lazy { readJson("lists-after-update-last") }
    val listsAfterUpdateLast get() = _listsAfterUpdateLast.map { it.toMutableList() }.toTypedArray()

    private val _listsAfterSwap: Array<List<Int>> by lazy { readJson("lists-after-swap") }
    val listsAfterSwap get() = _listsAfterSwap.map { it.toMutableList() }.toTypedArray()

    private val _listsBeforeUpdateLast: Array<List<String>> by lazy { readJson("lists-before-update-last") }
    val listsBeforeUpdateLast
        get() = _listsBeforeUpdateLast.map { it.toMutableList() }.toTypedArray()

    private val _listsBeforeSwap: Array<List<Int>> by lazy { readJson("lists-before-swap") }
    val listsBeforeSwap get() = _listsBeforeSwap.map { it.toMutableList() }.toTypedArray()

    private val _savedStateHandles: Array<Map<String, String>> by lazy { readJson("saved-state-handles") }
    val savedStateHandles: Array<SavedStateHandle>
        get() = _savedStateHandles.map { map ->
            SavedStateHandle().apply {
                map.forEach { entry ->
                    set(entry.key, entry.value)
                }
            }
        }.toTypedArray()

    object ConvertedIntegers : DataProviderParams() {
        const val charSequenceParam = 0
        const val integerParam = 1

        override val data by lazy {
            mergedData(validNumberCharSequences, convertedIntegers)
        }
    }

    object ValidSavedStateHandleKeys : DataProviderParams() {
        const val savedStateHandleParam = 0
        const val keyParam = 1
        const val valueParam = 2

        override val data
            get() = mergedData(
                savedStateHandles,
                validSavedStateHandleKeys,
                savedStateHandleValues
            )
    }

    object InvalidSavedStateHandleKeys : DataProviderParams() {
        const val savedStateHandleParam = 0
        const val keyParam = 1

        override val data get() = mergedData(savedStateHandles, invalidSavedStateHandleKeys)
    }

    object SwapIndices : DataProviderParams() {
        const val listBeforeSwapParam = 0
        const val listAfterSwapParam = 1
        const val firstSwapIndexParam = 2
        const val secondSwapIndexParam = 3

        override val data
            get() = mergedData(listsBeforeSwap, listsAfterSwap, firstSwapIndices, secondSwapIndices)
    }

    object UpdateLastValues : DataProviderParams() {
        const val listBeforeUpdateParam = 0
        const val listAfterUpdateParam = 1
        const val valueParam = 2

        override val data
            get() = mergedData(listsBeforeUpdateLast, listsAfterUpdateLast, updateLastValues)
    }
}