//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.adapters](../index.md)/[TierAdapter](index.md)/[onItemSwiped](on-item-swiped.md)

# onItemSwiped

open override fun [onItemSwiped](on-item-swiped.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Removes tier at given position and notifies that data set has changed.

Invokes [onTierRemoved](on-tier-removed.md) callback to allow restoring removed tier.

#### Parameters

| | |
|---|---|
| position | Position of the swiped tier. |
