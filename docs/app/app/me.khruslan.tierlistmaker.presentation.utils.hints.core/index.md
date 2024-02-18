//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core](index.md)

# Package-level declarations

UI utilities for core hint functionality.

## Types

| Name | Summary |
|---|---|
| [Hint](-hint/index.md) | class [Hint](-hint/index.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), overlay: [HintOverlayView](../me.khruslan.tierlistmaker.presentation.views/-hint-overlay-view/index.md), anchor: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, shape: Shape?, effect: Effect?)<br>Hint is a wrapper on Target. |
| [HintFactory](-hint-factory/index.md) | abstract class [HintFactory](-hint-factory/index.md)&lt;[T](-hint-factory/index.md) : [Indexable](-indexable/index.md)&gt;<br>Abstract factory that allows to create hints for various hint step types. |
| [HintGroup](-hint-group/index.md) | abstract class [HintGroup](-hint-group/index.md)&lt;[T](-hint-group/index.md) : [Indexable](-indexable/index.md)&gt;(val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html), val steps: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](-hint-group/index.md)&gt;)<br>An ordered group of hints. |
| [Indexable](-indexable/index.md) | interface [Indexable](-indexable/index.md)<br>Classes that implement this interface can be ordered together in a group. |
| [OnHintListener](-on-hint-listener/index.md) | interface [OnHintListener](-on-hint-listener/index.md)<br>Listener of the hint events that must be handled on a hint group level. |
