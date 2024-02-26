//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist.tier](../index.md)/[TierColorProviderImpl](index.md)

# TierColorProviderImpl

class [TierColorProviderImpl](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor : [TierColorProvider](../-tier-color-provider/index.md)

[TierColorProvider](../-tier-color-provider/index.md) implementation.

Uses HSV color model for calculations.

## Constructors

| | |
|---|---|
| [TierColorProviderImpl](-tier-color-provider-impl.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor()<br>Creates a new tier color provider instance. |

## Types

| Name | Summary |
|---|---|
| [HSVDefaults](-h-s-v-defaults/index.md) | private object [HSVDefaults](-h-s-v-defaults/index.md)<br>Constant HSV attributes for internal usage. |

## Properties

| Name | Summary |
|---|---|
| [defaultHueValues](default-hue-values.md) | private val [defaultHueValues](default-hue-values.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)&gt;<br>Pre-defined hue values. |

## Functions

| Name | Summary |
|---|---|
| [calculateNewHue](calculate-new-hue.md) | private fun [calculateNewHue](calculate-new-hue.md)(hueValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)&gt;): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)<br>Calculates the hue value for the new color based on the list of hue values. |
| [colorFromHue](color-from-hue.md) | private fun [colorFromHue](color-from-hue.md)(hue: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Creates color from its hue. |
| [getColors](get-colors.md) | open override fun [getColors](get-colors.md)(size: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;<br>Generates the list of colors for the tiers. |
