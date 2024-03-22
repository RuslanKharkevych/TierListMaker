//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.tierlist](../index.md)/[TierListHintStep](index.md)

# TierListHintStep

enum [TierListHintStep](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[TierListHintStep](index.md)&gt; , [HintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-step/index.md)

Hint steps of the [TierListHintGroup](../-tier-list-hint-group/index.md).

## Constructors

| | |
|---|---|
| [TierListHintStep](-tier-list-hint-step.md) | private constructor()<br>Default empty constructor. |

## Entries

| | |
|---|---|
| [ReorderTiers](-reorder-tiers/index.md) | [ReorderTiers](-reorder-tiers/index.md)<br>How to reorder tiers. |
| [RemoveImage](-remove-image/index.md) | [RemoveImage](-remove-image/index.md)<br>How to remove image. |
| [RemoveTier](-remove-tier/index.md) | [RemoveTier](-remove-tier/index.md)<br>How to remove tier. |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.enums/-enum-entries/index.html)&lt;[TierListHintStep](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |
| [index](--index--.md) | open override val [index](--index--.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Hint step position determined by [Enum.ordinal](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/ordinal.html). |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [TierListHintStep](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md) | fun [values](values.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[TierListHintStep](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |
