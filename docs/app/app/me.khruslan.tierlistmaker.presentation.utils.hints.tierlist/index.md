//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.tierlist](index.md)

# Package-level declarations

UI utilities for showing tier list hints.

## Types

| Name | Summary |
|---|---|
| [TierListHintFactory](-tier-list-hint-factory/index.md) | class [TierListHintFactory](-tier-list-hint-factory/index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val binding: FragmentTierListBinding) : [HintFactory](../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-factory/index.md)&lt;[TierListHintStep](-tier-list-hint-step/index.md)&gt; <br>Factory that creates hints for [TierListHintStep](-tier-list-hint-step/index.md) entries. |
| [TierListHintGroup](-tier-list-hint-group/index.md) | class [TierListHintGroup](-tier-list-hint-group/index.md)(val activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html), val binding: FragmentTierListBinding) : [HintGroup](../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-group/index.md)&lt;[TierListHintStep](-tier-list-hint-step/index.md)&gt; <br>A group of hints for [TierListFragment](../me.khruslan.tierlistmaker.presentation.screens.tierlist/-tier-list-fragment/index.md). |
| [TierListHintStep](-tier-list-hint-step/index.md) | enum [TierListHintStep](-tier-list-hint-step/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[TierListHintStep](-tier-list-hint-step/index.md)&gt; , [HintStep](../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-step/index.md)<br>Hint steps of the [TierListHintGroup](-tier-list-hint-group/index.md). |
