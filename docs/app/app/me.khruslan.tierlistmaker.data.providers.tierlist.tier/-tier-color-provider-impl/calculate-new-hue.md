//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist.tier](../index.md)/[TierColorProviderImpl](index.md)/[calculateNewHue](calculate-new-hue.md)

# calculateNewHue

private fun [calculateNewHue](calculate-new-hue.md)(hueValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)&gt;): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)

Calculates the hue value for the new color based on the list of hue values.

The algorithm seeks for the biggest gap between hue values. If there are several equal gaps, the first one will be chosen. After that the value from the middle of the gap is picked.

#### Return

Hue value of the new color.

#### Parameters

| | |
|---|---|
| hueValues | List of hue values of already generated colors. |
