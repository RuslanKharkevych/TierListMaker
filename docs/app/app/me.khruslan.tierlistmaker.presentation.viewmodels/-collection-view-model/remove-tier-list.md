//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[CollectionViewModel](index.md)/[removeTierList](remove-tier-list.md)

# removeTierList

fun [removeTierList](remove-tier-list.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Asynchronously removes tier list from the local storage.

If the last tier list is being removed, updates [listStateLiveData](list-state-live-data.md) with [ListState.Empty](../../me.khruslan.tierlistmaker.presentation.models/-list-state/-empty/index.md). If database transaction fails, triggers [errorEvent](error-event.md). Note that the tier list will always be removed from [tierLists](tier-lists.md), regardless of the transaction result. Also logs [TierListDeleted](../../me.khruslan.tierlistmaker.util.analytics/-tier-list-deleted/index.md) analytic event.

#### Parameters

| | |
|---|---|
| index | Position of the tier list to remove. |
