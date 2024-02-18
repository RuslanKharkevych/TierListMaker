//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.tierlist](../index.md)/[TierListFragment](index.md)/[initTiersAdapter](init-tiers-adapter.md)

# initTiersAdapter

private fun [initTiersAdapter](init-tiers-adapter.md)(tiers: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md)&gt;, imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Initializes [tiersAdapter](tiers-adapter.md) and attaches it to the recycler view.

Configures recycler view to disable animations, enable auto-scrolling and reordering. Registers [tiersDataObserver](tiers-data-observer.md).

#### Parameters

| | |
|---|---|
| tiers | List of the tiers. |
| imageSize | Size of the image. |
