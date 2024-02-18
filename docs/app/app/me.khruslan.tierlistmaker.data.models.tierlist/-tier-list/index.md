//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[TierList](index.md)

# TierList

data class [TierList](index.md)(val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), var title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), var zoomValue: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), var tiers: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Tier](../-tier/index.md)&gt;, val backlogImages: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;) : [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)

Tier list is a table that contains images grouped into tiers based on their rankings.

Note that this class is mutable. It can be stored in the database or passed as a navigation argument.

## Constructors

| | |
|---|---|
| [TierList](-tier-list.md) | constructor(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), zoomValue: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), tiers: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Tier](../-tier/index.md)&gt;, backlogImages: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;)<br>Creates a new tier list. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Tier list constants for internal use. |
| [Preview](-preview/index.md) | data class [Preview](-preview/index.md)(val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val images: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;)<br>Preview of the tier list contains its title and the list of the highest ranked images. |

## Properties

| Name | Summary |
|---|---|
| [backlogImages](backlog-images.md) | val [backlogImages](backlog-images.md): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;<br>List of backlog images. Backlog is a special row that contains images, which haven't been ranked yet. |
| [id](id.md) | val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Unique identifier of the tier list. This property can't be changed. |
| [preview](preview.md) | val [preview](preview.md): [TierList.Preview](-preview/index.md)<br>Preview of this tier list. |
| [previewImages](preview-images.md) | private val [previewImages](preview-images.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;<br>List of preview images for this tier list. |
| [tiers](tiers.md) | var [tiers](tiers.md): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Tier](../-tier/index.md)&gt;<br>List of tiers. |
| [title](title.md) | var [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Title of the tier list. |
| [zoomValue](zoom-value.md) | var [zoomValue](zoom-value.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Maximum number of items (images plus tier header) that can fit in a row. For example, if zoomValue is 5, that means that each tier can contain maximum of 4 images in a row. If there are more images, they will be moved to the new row within the same tier. |
