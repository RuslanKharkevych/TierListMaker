//[app](../../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../../index.md)/[TierList](../index.md)/[Preview](index.md)

# Preview

data class [Preview](index.md)(val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val images: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Image](../../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;)

Preview of the tier list contains its title and the list of the highest ranked images.

Previews are used in a collection of tier lists. Each tier list can be mapped to preview using its [preview](../preview.md) property.

## Constructors

| | |
|---|---|
| [Preview](-preview.md) | constructor(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), images: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Image](../../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;)<br>Creates the new preview of the tier list. |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Unique identifier of the tier list. |
| [images](images.md) | val [images](images.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Image](../../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;<br>List of preview images. |
| [title](title.md) | val [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Title of the tier list. |
