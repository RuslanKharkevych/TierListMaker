//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.holders](../index.md)/[TierListImageHolder](index.md)

# TierListImageHolder

class [TierListImageHolder](index.md)(val imageView: [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html), dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html)) : [RecyclerView.ViewHolder](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ViewHolder.html), [View.OnTouchListener](https://developer.android.com/reference/kotlin/android/view/View.OnTouchListener.html)

Holder of the tier list image.

Allows to start a drag.

#### Parameters

| | |
|---|---|
| dragListener | Listener of tier list drag events. |

## Constructors

| | |
|---|---|
| [TierListImageHolder](-tier-list-image-holder.md) | constructor(imageView: [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html), dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html))<br>Creates a new holder instance. |

## Types

| Name | Summary |
|---|---|
| [TierListImageException](-tier-list-image-exception/index.md) | private class [TierListImageException](-tier-list-image-exception/index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)<br>Thrown when tier list image can't be dragged successfully. |

## Properties

| Name | Summary |
|---|---|
| [imageView](image-view.md) | private val [imageView](image-view.md): [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html)<br>View of the image. |

## Functions

| Name | Summary |
|---|---|
| [bind](bind.md) | fun [bind](bind.md)(image: [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md), imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tag: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md))<br>Binds image to the item view. |
| [initListeners](init-listeners.md) | private fun [initListeners](init-listeners.md)(dragListener: [View.OnDragListener](https://developer.android.com/reference/kotlin/android/view/View.OnDragListener.html))<br>Initializes image view listeners. |
| [onTouch](on-touch.md) | open override fun [onTouch](on-touch.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?, event: [MotionEvent](https://developer.android.com/reference/kotlin/android/view/MotionEvent.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>If event action is [MotionEvent.ACTION_DOWN](https://developer.android.com/reference/kotlin/android/view/MotionEvent.html#action_down), attempts to start a drag. |
| [startDrag](start-drag.md) | private fun [startDrag](start-drag.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html)?)<br>private fun [startDrag](start-drag.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html), data: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md))<br>Attempts to start a drag. |
| [startDragCompat](start-drag-compat.md) | private fun [startDragCompat](start-drag-compat.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html), data: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>A helper function to start drag that supports all versions. |
