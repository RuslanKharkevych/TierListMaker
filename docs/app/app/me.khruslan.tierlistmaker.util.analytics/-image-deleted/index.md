//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.analytics](../index.md)/[ImageDeleted](index.md)

# ImageDeleted

class [ImageDeleted](index.md)(imagePayload: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), tierListTitle: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Event](../-event/index.md)

Logged when user deletes an image from a tier list.

This event helps to identify which images users remove the most often.

#### Parameters

| | |
|---|---|
| imagePayload | Payload of the deleted image. |
| tierListTitle | Title of the tier list. |

## Constructors

| | |
|---|---|
| [ImageDeleted](-image-deleted.md) | constructor(imagePayload: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), tierListTitle: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Creates event with image payload and tier list title parameters. |
