//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.home](../index.md)/[CollectionFragment](index.md)/[showRemoveTierListConfirmationAlert](show-remove-tier-list-confirmation-alert.md)

# showRemoveTierListConfirmationAlert

private fun [showRemoveTierListConfirmationAlert](show-remove-tier-list-confirmation-alert.md)(tierListIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Shows confirmation alert for removing tier list when preview is swiped.

On positive button click removes tier list and the corresponding preview. On negative button click or when alert is canceled restores swiped preview.

#### Parameters

| | |
|---|---|
| tierListIndex | Position of the tier list to remove. |
