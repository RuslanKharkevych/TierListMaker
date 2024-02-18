//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DefaultTierListCollectionProviderImpl](index.md)/[buildTiers](build-tiers.md)

# buildTiers

private fun [buildTiers](build-tiers.md)(zoomValue: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), images: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md)&gt;

Builds tiers and populates them with images.

Images will be randomly shuffled. All tiers will contain a full single row of images. The exception of the last tier, which can contain less images.

#### Return

Created tiers.

#### Parameters

| | |
|---|---|
| zoomValue | Determines the number of tiers. |
| images | List of image file names. |
