//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[TrashBinHighlightUpdated](index.md)

# TrashBinHighlightUpdated

data class [TrashBinHighlightUpdated](index.md)(val highlighted: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) : [TierListEvent](../-tier-list-event/index.md)

Tier list that indicates that the highlight of the trash bin was updated.

This event is sent when the trash bin was highlighted or unhighlighted as a result of a drag effect.

## Constructors

| | |
|---|---|
| [TrashBinHighlightUpdated](-trash-bin-highlight-updated.md) | constructor(highlighted: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Creates the event from the highlighted flag. |

## Properties

| Name | Summary |
|---|---|
| [highlighted](highlighted.md) | val [highlighted](highlighted.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether the trash bin becomes highlighted. |
