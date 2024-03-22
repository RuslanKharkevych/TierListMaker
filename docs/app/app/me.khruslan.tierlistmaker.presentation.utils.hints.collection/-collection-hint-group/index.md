//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.collection](../index.md)/[CollectionHintGroup](index.md)

# CollectionHintGroup

class [CollectionHintGroup](index.md)(val activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html), val binding: FragmentCollectionBinding) : [HintGroup](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-group/index.md)&lt;[CollectionHintStep](../-collection-hint-step/index.md)&gt; 

A group of hints for [CollectionFragment](../../me.khruslan.tierlistmaker.presentation.screens.home/-collection-fragment/index.md).

## Constructors

| | |
|---|---|
| [CollectionHintGroup](-collection-hint-group.md) | constructor(activity: [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html), binding: FragmentCollectionBinding)<br>Creates a new hint group. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Constants for internal use. |

## Properties

| Name | Summary |
|---|---|
| [activity](activity.md) | private val [activity](activity.md): [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html)<br>An activity that hosts the fragment. |
| [binding](binding.md) | private val [binding](binding.md): FragmentCollectionBinding<br>Binding of the fragment. |

## Functions

| Name | Summary |
|---|---|
| [hintFactory](hint-factory.md) | protected open override fun [hintFactory](hint-factory.md)(): [HintFactory](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-factory/index.md)&lt;[CollectionHintStep](../-collection-hint-step/index.md)&gt;<br>Provides collection hint factory. |
| [prepareForShowing](prepare-for-showing.md) | private fun [prepareForShowing](prepare-for-showing.md)(onReady: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Prepares the UI for showing hint. |
| [show](show.md) | open override fun [show](show.md)(step: [CollectionHintStep](../-collection-hint-step/index.md))<br>Shows the hint group starting with the given step. |
| [showAddNewListButton](show-add-new-list-button.md) | private fun [showAddNewListButton](show-add-new-list-button.md)()<br>Shows &quot;Add new list&quot; floating action button. |
