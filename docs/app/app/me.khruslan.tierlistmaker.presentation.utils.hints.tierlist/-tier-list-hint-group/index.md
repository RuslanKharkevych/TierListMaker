//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.tierlist](../index.md)/[TierListHintGroup](index.md)

# TierListHintGroup

class [TierListHintGroup](index.md)(val activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html), val binding: FragmentTierListBinding) : [HintGroup](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-group/index.md)&lt;[TierListHintStep](../-tier-list-hint-step/index.md)&gt; 

A group of hints for [TierListFragment](../../me.khruslan.tierlistmaker.presentation.screens.tierlist/-tier-list-fragment/index.md).

## Constructors

| | |
|---|---|
| [TierListHintGroup](-tier-list-hint-group.md) | constructor(activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html), binding: FragmentTierListBinding)<br>Creates a new hint group. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Constants for internal use. |

## Properties

| Name | Summary |
|---|---|
| [activity](activity.md) | private val [activity](activity.md): [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html)<br>An activity that hosts the fragment. |
| [binding](binding.md) | private val [binding](binding.md): FragmentTierListBinding<br>Binding of the fragment. |

## Functions

| Name | Summary |
|---|---|
| [hintFactory](hint-factory.md) | protected open override fun [hintFactory](hint-factory.md)(): [HintFactory](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-factory/index.md)&lt;[TierListHintStep](../-tier-list-hint-step/index.md)&gt;<br>Provides tier list hint factory. |
| [show](show.md) | open override fun [show](show.md)(step: [TierListHintStep](../-tier-list-hint-step/index.md))<br>Shows the hint group starting with the given step. |
