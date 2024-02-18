//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[CollectionViewModel](index.md)/[loadTierLists](load-tier-lists.md)

# loadTierLists

private suspend fun [loadTierLists](load-tier-lists.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;

Asynchronously loads tier lists from database and returns the result.

Updates [listStateLiveData](list-state-live-data.md) after loading is complete.

#### Return

Loaded tier lists or empty list in case of error.
