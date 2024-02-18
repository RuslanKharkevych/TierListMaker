//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist](../index.md)/[TierListReadyToView](index.md)

# TierListReadyToView

data class [TierListReadyToView](index.md)(val uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)) : [TierListEvent](../-tier-list-event/index.md)

Tier list event that indicates that tier list image file is ready to be viewed.

This event is sent when the tier list was successfully saved to the file as a result of a view action triggered by user.

## Constructors

| | |
|---|---|
| [TierListReadyToView](-tier-list-ready-to-view.md) | constructor(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html))<br>Creates the event from URI. |

## Properties

| Name | Summary |
|---|---|
| [uri](uri.md) | val [uri](uri.md): [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)<br>URI reference that points to the file. |
