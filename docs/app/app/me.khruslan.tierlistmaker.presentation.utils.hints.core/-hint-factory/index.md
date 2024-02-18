//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.core](../index.md)/[HintFactory](index.md)

# HintFactory

abstract class [HintFactory](index.md)&lt;[T](index.md) : [Indexable](../-indexable/index.md)&gt;

Abstract factory that allows to create hints for various hint step types.

#### Parameters

| | |
|---|---|
| T | Type of the hint step. |

#### Inheritors

| |
|---|
| [CollectionHintFactory](../../me.khruslan.tierlistmaker.presentation.utils.hints.collection/-collection-hint-factory/index.md) |

## Constructors

| | |
|---|---|
| [HintFactory](-hint-factory.md) | constructor()<br>Default no-arg constructor. |

## Functions

| Name | Summary |
|---|---|
| [create](create.md) | abstract fun [create](create.md)(step: [T](index.md)): [Hint](../-hint/index.md)<br>Creates a hint for the provided step. |
