//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.collection](index.md)

# Package-level declarations

UI utilities for showing collection hints.

## Types

| Name | Summary |
|---|---|
| [CollectionHintFactory](-collection-hint-factory/index.md) | class [CollectionHintFactory](-collection-hint-factory/index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val binding: FragmentCollectionBinding) : [HintFactory](../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-factory/index.md)&lt;[CollectionHintStep](-collection-hint-step/index.md)&gt; <br>Factory that creates hints for [CollectionHintStep](-collection-hint-step/index.md) entries. |
| [CollectionHintGroup](-collection-hint-group/index.md) | class [CollectionHintGroup](-collection-hint-group/index.md)(val activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html), val binding: FragmentCollectionBinding) : [HintGroup](../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-group/index.md)&lt;[CollectionHintStep](-collection-hint-step/index.md)&gt; <br>A group of hints for [CollectionFragment](../me.khruslan.tierlistmaker.presentation.screens.home/-collection-fragment/index.md). |
| [CollectionHintStep](-collection-hint-step/index.md) | enum [CollectionHintStep](-collection-hint-step/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[CollectionHintStep](-collection-hint-step/index.md)&gt; , [HintStep](../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-step/index.md)<br>Hint steps of the [CollectionHintGroup](-collection-hint-group/index.md). |
