//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](index.md)/[getMaterialColor](get-material-color.md)

# getMaterialColor

@[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html) 

fun [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).[getMaterialColor](get-material-color.md)(@[AttrRes](https://developer.android.com/reference/kotlin/androidx/annotation/AttrRes.html) colorAttrRes: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Returns the color int for the provided theme color attribute.

This extension is a shorthand of the MaterialColors.getColor function.

#### Receiver

Activity context.

#### Return

Resolved color.

#### Parameters

| | |
|---|---|
| colorAttrRes | Color theme attribute. |

#### Throws

| | |
|---|---|
| [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html) | If the attribute is not set in the current theme. |
