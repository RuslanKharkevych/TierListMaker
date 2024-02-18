//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[TierStyle](index.md)

# TierStyle

data class [TierStyle](index.md)(val title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html)val color: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = Color.TRANSPARENT) : [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)

Style of the [Tier](../-tier/index.md) includes its title and color.

The style is applied to the tier header only and does not affect images inside the tier. A new style can be created with default parameters, but those are dummies. It's useful when the style is not known in advance and will be updated afterwards. This class is immutable. It can be stored in the database or passed as a navigation argument.

## Constructors

| | |
|---|---|
| [TierStyle](-tier-style.md) | constructor(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;&quot;, @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html)color: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = Color.TRANSPARENT)<br>Creates the tier style with provided title and color. |

## Properties

| Name | Summary |
|---|---|
| [color](color.md) | val [color](color.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Color of the tier header. The default value is transparent. |
| [title](title.md) | val [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Title of the tier. The default value is an empty string. |
