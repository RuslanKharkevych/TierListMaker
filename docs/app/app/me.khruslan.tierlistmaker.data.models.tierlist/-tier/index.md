//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[Tier](index.md)

# Tier

data class [Tier](index.md)(val id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = UUID.randomUUID().toString(), val images: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt; = mutableListOf(), var style: [TierStyle](../-tier-style/index.md) = TierStyle()) : [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)

Tier is the rank of the [TierList](../-tier-list/index.md) that contains header and images.

A new empty tier can be created with default arguments but its style must be updated afterwards. Note that this class is mutable. It can be stored in the database or passed as a navigation argument.

## Constructors

| | |
|---|---|
| [Tier](-tier.md) | constructor(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = UUID.randomUUID().toString(), images: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt; = mutableListOf(), style: [TierStyle](../-tier-style/index.md) = TierStyle())<br>Creates the tier with provided id, images and style. |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Unique identifier of the tier. The default value is random UUID. This property can't be changed. |
| [images](images.md) | val [images](images.md): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;<br>List of tier images. The default value is empty list. |
| [style](style.md) | var [style](style.md): [TierStyle](../-tier-style/index.md)<br>Style of the tier header. The default value is dummy and must be updated. |
