//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.collection](../index.md)/[CollectionHintFactory](index.md)

# CollectionHintFactory

class [CollectionHintFactory](index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val binding: FragmentCollectionBinding) : [HintFactory](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-factory/index.md)&lt;[CollectionHintStep](../-collection-hint-step/index.md)&gt; 

Factory that creates hints for [CollectionHintStep](../-collection-hint-step/index.md) entries.

## Constructors

| | |
|---|---|
| [CollectionHintFactory](-collection-hint-factory.md) | constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), binding: FragmentCollectionBinding)<br>Creates a new hint factory. |

## Properties

| Name | Summary |
|---|---|
| [binding](binding.md) | private val [binding](binding.md): FragmentCollectionBinding<br>[CollectionFragment](../../me.khruslan.tierlistmaker.presentation.screens.home/-collection-fragment/index.md) binding. |
| [context](context.md) | private val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)<br>Activity context. |

## Functions

| Name | Summary |
|---|---|
| [create](create.md) | open override fun [create](create.md)(step: [CollectionHintStep](../-collection-hint-step/index.md)): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)<br>Creates a collection hint for the provided step. |
| [createAddNewTierListHint](create-add-new-tier-list-hint.md) | private fun [createAddNewTierListHint](create-add-new-tier-list-hint.md)(): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)<br>Creates a hint that shows how to create a new tier list. |
| [createRemoveTierListHint](create-remove-tier-list-hint.md) | private fun [createRemoveTierListHint](create-remove-tier-list-hint.md)(): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)<br>Creates a hint that shows how to remove a tier list. |
| [createReorderTierListsHint](create-reorder-tier-lists-hint.md) | private fun [createReorderTierListsHint](create-reorder-tier-lists-hint.md)(): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)<br>Creates a hint that shows how to reorder tier lists. |
| [createTierListPreviewHint](create-tier-list-preview-hint.md) | private fun [createTierListPreviewHint](create-tier-list-preview-hint.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), overlay: [HintOverlayView](../../me.khruslan.tierlistmaker.presentation.views/-hint-overlay-view/index.md)): [Hint](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint/index.md)<br>A helper function that creates a hint with tier list card as an anchor. |
| [findFirstTierListPreview](find-first-tier-list-preview.md) | private fun [findFirstTierListPreview](find-first-tier-list-preview.md)(): MaterialCardView?<br>Finds the first tier list preview. |
