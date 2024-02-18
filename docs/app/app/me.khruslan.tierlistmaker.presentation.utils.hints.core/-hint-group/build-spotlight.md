//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core](../index.md)/[HintGroup](index.md)/[buildSpotlight](build-spotlight.md)

# buildSpotlight

private fun [buildSpotlight](build-spotlight.md)(hints: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Hint](../-hint/index.md)&gt;): Spotlight

Builds a spotlight instance with the provided hints.

Additionally registers [SpotlightListener](-spotlight-listener/index.md) for logging spotlight events.

#### Return

Spotlight instance that is ready to be shown.

#### Parameters

| | |
|---|---|
| hints | Hints that will be set as targets for this spotlight. |
