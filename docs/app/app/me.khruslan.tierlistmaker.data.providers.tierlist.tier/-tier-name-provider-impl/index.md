//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.tierlist.tier](../index.md)/[TierNameProviderImpl](index.md)

# TierNameProviderImpl

class [TierNameProviderImpl](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor : [TierNameProvider](../-tier-name-provider/index.md)

[TierNameProvider](../-tier-name-provider/index.md) implementation.

Letters of the English alphabet are used as tier names.

## Constructors

| | |
|---|---|
| [TierNameProviderImpl](-tier-name-provider-impl.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor()<br>Creates a new tier name provider instance. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Characters for building tier names. |

## Functions

| Name | Summary |
|---|---|
| [getNameByIndex](get-name-by-index.md) | open override fun [getNameByIndex](get-name-by-index.md)(tierIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Generates tier name based on its position. |
