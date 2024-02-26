//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.collection](../index.md)/[CollectionHintStep](index.md)

# CollectionHintStep

enum [CollectionHintStep](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[CollectionHintStep](index.md)&gt; , [Indexable](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-indexable/index.md)

Hint steps of the [CollectionHintGroup](../-collection-hint-group/index.md).

## Constructors

| | |
|---|---|
| [CollectionHintStep](-collection-hint-step.md) | private constructor()<br>Default empty constructor. |

## Entries

| | |
|---|---|
| [ReorderTierLists](-reorder-tier-lists/index.md) | [ReorderTierLists](-reorder-tier-lists/index.md)<br>How to reorder tier lists. |
| [AddNewTierList](-add-new-tier-list/index.md) | [AddNewTierList](-add-new-tier-list/index.md)<br>How to add a new tier list. |
| [RemoveTierList](-remove-tier-list/index.md) | [RemoveTierList](-remove-tier-list/index.md)<br>How to remove a tier list. |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.enums/-enum-entries/index.html)&lt;[CollectionHintStep](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |
| [index](--index--.md) | open override val [index](--index--.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Hint step position determined by [Enum.ordinal](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/ordinal.html). |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [CollectionHintStep](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md) | fun [values](values.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[CollectionHintStep](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |
