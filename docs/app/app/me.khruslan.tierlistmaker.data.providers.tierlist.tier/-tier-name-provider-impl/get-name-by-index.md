//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist.tier](../index.md)/[TierNameProviderImpl](index.md)/[getNameByIndex](get-name-by-index.md)

# getNameByIndex

open override fun [getNameByIndex](get-name-by-index.md)(tierIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Generates tier name based on its position.

The first tier's name is &quot;S&quot;. For next tiers names are &quot;A-Z&quot;.

#### Return

Name of the tier.

#### Parameters

| | |
|---|---|
| tierIndex | Position of the tier in a tier list. |

#### Throws

| | |
|---|---|
| [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html) | If tier index is negative or bigger than the maximum allowed value (27). |
