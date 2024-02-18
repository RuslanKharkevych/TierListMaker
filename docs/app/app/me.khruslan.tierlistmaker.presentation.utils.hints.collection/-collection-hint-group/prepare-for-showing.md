//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.collection](../index.md)/[CollectionHintGroup](index.md)/[prepareForShowing](prepare-for-showing.md)

# prepareForShowing

private fun [prepareForShowing](prepare-for-showing.md)(onReady: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Prepares the UI for showing hint.

1. Waits until the view tree is about to be drawn.
2. Scrolls to the first tier list.

#### Parameters

| | |
|---|---|
| onReady | Invoked when hint is ready to be shown. |
