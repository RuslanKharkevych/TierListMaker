//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core](../index.md)/[HintGroup](index.md)

# HintGroup

abstract class [HintGroup](index.md)&lt;[T](index.md) : [HintStep](../-hint-step/index.md)&gt;(val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html), val steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](index.md)&gt;)

An ordered group of hints.

Implemented with [Spotlight](https://github.com/TakuSemba/Spotlight). User can navigate back and forwards through hints.

#### Parameters

| | |
|---|---|
| T | Type of the hint step. |

#### Inheritors

| |
|---|
| [CollectionHintGroup](../../me.khruslan.tierlistmaker.presentation.utils.hints.collection/-collection-hint-group/index.md) |
| [TierListHintGroup](../../me.khruslan.tierlistmaker.presentation.utils.hints.tierlist/-tier-list-hint-group/index.md) |

## Constructors

| | |
|---|---|
| [HintGroup](-hint-group.md) | constructor(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html), steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](index.md)&gt;)<br>Creates a new hint group. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Constants for internal use. |
| [HintListener](-hint-listener/index.md) | private class [HintListener](-hint-listener/index.md)(val spotlight: Spotlight) : [OnHintListener](../-on-hint-listener/index.md)<br>Listener that uses spotlight instance to handle hint events. |
| [SpotlightListener](-spotlight-listener/index.md) | private class [SpotlightListener](-spotlight-listener/index.md)(val hintGroupName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : OnSpotlightListener<br>Listener that logs spotlight started / ended events. |

## Properties

| Name | Summary |
|---|---|
| [activity](activity.md) | private val [activity](activity.md): [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html)<br>Hosting activity. |
| [name](name.md) | private val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the hint group. |
| [steps](steps.md) | private val [steps](steps.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](index.md)&gt;<br>The list of hint steps. |

## Functions

| Name | Summary |
|---|---|
| [buildSpotlight](build-spotlight.md) | private fun [buildSpotlight](build-spotlight.md)(hints: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Hint](../-hint/index.md)&gt;): Spotlight<br>Builds a spotlight instance with the provided hints. |
| [createHints](create-hints.md) | private fun [createHints](create-hints.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Hint](../-hint/index.md)&gt;<br>Creates a hint for each step using [HintFactory](../-hint-factory/index.md). |
| [hintFactory](hint-factory.md) | protected abstract fun [hintFactory](hint-factory.md)(): [HintFactory](../-hint-factory/index.md)&lt;[T](index.md)&gt;<br>Provides a factory for creating hints. |
| [mapTargets](map-targets.md) | private fun [mapTargets](map-targets.md)(hints: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Hint](../-hint/index.md)&gt;): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;Target&gt;<br>Maps hints to targets for use by Spotlight. |
| [setHintListeners](set-hint-listeners.md) | private fun [setHintListeners](set-hint-listeners.md)(hints: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Hint](../-hint/index.md)&gt;, spotlight: Spotlight)<br>Registers [HintListener](-hint-listener/index.md) for each hint. |
| [show](show.md) | open fun [show](show.md)(step: [T](index.md))<br>Shows the hint group starting with the given step. |
