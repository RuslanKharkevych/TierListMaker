//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.recyclerview](../index.md)/[FlexLayoutManager](index.md)

# FlexLayoutManager

class [FlexLayoutManager](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : FlexboxLayoutManager

FlexboxLayoutManager implementation that generates the missing override of [RecyclerView.LayoutManager.generateLayoutParams](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.LayoutManager.html#generatelayoutparams) overloaded function.

It's a workaround to avoid a crash that happens when a recycler view, which is configured with FlexboxLayoutManager, contains nested recycler view.

#### Parameters

| | |
|---|---|
| context | Activity context. |

## Constructors

| | |
|---|---|
| [FlexLayoutManager](-flex-layout-manager.md) | constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Creates horizontal flexbox layout manager. |

## Functions

| Name | Summary |
|---|---|
| [generateLayoutParams](generate-layout-params.md) | open override fun [generateLayoutParams](generate-layout-params.md)(lp: [ViewGroup.LayoutParams](https://developer.android.com/reference/kotlin/android/view/ViewGroup.LayoutParams.html)): FlexboxLayoutManager.LayoutParams<br>Creates layout params object for this layout manager, copying relevant values from the supplied layout params. |
