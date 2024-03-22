//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.tierlist](../index.md)/[TierListHintFactory](index.md)

# TierListHintFactory

class [TierListHintFactory](index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val binding: FragmentTierListBinding) : [HintFactory](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-factory/index.md)&lt;[TierListHintStep](../-tier-list-hint-step/index.md)&gt; 

Factory that creates hints for [TierListHintStep](../-tier-list-hint-step/index.md) entries.

## Constructors

| | |
|---|---|
| [TierListHintFactory](-tier-list-hint-factory.md) | constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), binding: FragmentTierListBinding)<br>Creates a new hint factory. |

## Properties

| Name | Summary |
|---|---|
| [binding](binding.md) | private val [binding](binding.md): FragmentTierListBinding<br>[TierListFragment](../../me.khruslan.tierlistmaker.presentation.screens.tierlist/-tier-list-fragment/index.md) binding. |
| [context](context.md) | private val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)<br>Activity context. |

## Functions

| Name | Summary |
|---|---|
| [create](create.md) | open override fun [create](create.md)(step: [TierListHintStep](../-tier-list-hint-step/index.md)): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)<br>Creates a tier list hint for the provided step. |
| [createRemoveImageHint](create-remove-image-hint.md) | private fun [createRemoveImageHint](create-remove-image-hint.md)(): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)<br>Creates a hint that shows how to remove an image. |
| [createRemoveTierHint](create-remove-tier-hint.md) | private fun [createRemoveTierHint](create-remove-tier-hint.md)(): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)<br>Creates a hint that shows how to remove a tier. |
| [createReorderTiersHint](create-reorder-tiers-hint.md) | private fun [createReorderTiersHint](create-reorder-tiers-hint.md)(): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)<br>Creates a hint that shows how to reorder tiers. |
| [createTierListHeaderHint](create-tier-list-header-hint.md) | private fun [createTierListHeaderHint](create-tier-list-header-hint.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), overlay: [HintOverlayView](../../me.khruslan.tierlistmaker.presentation.views/-hint-overlay-view/index.md)): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)<br>A helper function that creates a hint with the header of the first tier as an anchor. |
| [findFirstTierHeader](find-first-tier-header.md) | private fun [findFirstTierHeader](find-first-tier-header.md)(): [View](https://developer.android.com/reference/kotlin/android/view/View.html)?<br>Finds a header view of the first tier. |
| [findFirstTierListImage](find-first-tier-list-image.md) | private fun [findFirstTierListImage](find-first-tier-list-image.md)(): [View](https://developer.android.com/reference/kotlin/android/view/View.html)?<br>Finds the first tier list image. |
