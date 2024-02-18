//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.models.drag](../index.md)/[DragState](index.md)

# DragState

enum [DragState](index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[DragState](index.md)&gt; 

UI state of dragging.

Indicates whether the dragging is happening at a given point in time.

## Constructors

| | |
|---|---|
| [DragState](-drag-state.md) | private constructor()<br>Default empty constructor. |

## Entries

| | |
|---|---|
| [Idle](-idle/index.md) | [Idle](-idle/index.md)<br>Dragging is finished or hasn't been started yet. New drag can be started. |
| [Dragging](-dragging/index.md) | [Dragging](-dragging/index.md)<br>Dragging is in progress. |
| [Finishing](-finishing/index.md) | [Finishing](-finishing/index.md)<br>Dragging is about to finish. New drag can't be started yet. |

## Properties

| Name | Summary |
|---|---|
| [entries](entries.md) | val [entries](entries.md): [EnumEntries](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.enums/-enum-entries/index.html)&lt;[DragState](index.md)&gt;<br>Returns a representation of an immutable list of all enum entries, in the order they're declared. |

## Functions

| Name | Summary |
|---|---|
| [valueOf](value-of.md) | fun [valueOf](value-of.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [DragState](index.md)<br>Returns the enum constant of this type with the specified name. The string must match exactly an identifier used to declare an enum constant in this type. (Extraneous whitespace characters are not permitted.) |
| [values](values.md) | fun [values](values.md)(): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[DragState](index.md)&gt;<br>Returns an array containing the constants of this enum type, in the order they're declared. |
