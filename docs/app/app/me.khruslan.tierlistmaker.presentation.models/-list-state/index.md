//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.models](../index.md)/[ListState](index.md)

# ListState

enum [ListState](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[ListState](index.md)&gt; 

UI state of the list with asynchronously loaded data.

Represents the status of data loading at a given point in time.

## Constructors

| | |
|---|---|
| [ListState](-list-state.md) | private constructor()<br>Default empty constructor. |

## Entries

| | |
|---|---|
| [Loading](-loading/index.md) | [Loading](-loading/index.md)<br>Loading state. |
| [Empty](-empty/index.md) | [Empty](-empty/index.md)<br>State of the empty list. |
| [Filled](-filled/index.md) | [Filled](-filled/index.md)<br>State of the list that is not empty. |
| [Failed](-failed/index.md) | [Failed](-failed/index.md)<br>State of the list that failed to load. |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.enums/-enum-entries/index.html)&lt;[ListState](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ListState](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md) | fun [values](values.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[ListState](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |
