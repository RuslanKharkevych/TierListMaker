//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist.tier](../index.md)/[TierColorProviderImpl](index.md)/[getColors](get-colors.md)

# getColors

open override fun [getColors](get-colors.md)(size: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;

Generates the list of colors for the tiers.

Firstly creates colors from the default hue values. If those are not enough, calculates new hue value and creates a color from it. Resulting colors are sorted by hue.

#### Return

List of generated colors.

#### Parameters

| | |
|---|---|
| size | Number of colors to generate. |
