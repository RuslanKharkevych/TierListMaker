//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[TierListExportError](index.md)

# TierListExportError

data class [TierListExportError](index.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)val errorMessageResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [TierListEvent](../-tier-list-event/index.md)

Tier list event that indicates that an error occurred during tier list image file export.

This event is sent when the tier list wasn't saved to the file due to an error. It can happen when user tries to share or view the tier list.

## Constructors

| | |
|---|---|
| [TierListExportError](-tier-list-export-error.md) | constructor(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)errorMessageResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Creates the event from error message string resource. |

## Properties

| Name | Summary |
|---|---|
| [errorMessageResId](error-message-res-id.md) | val [errorMessageResId](error-message-res-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>String resource of the user-friendly error message. |
