//[app](../../../../index.md)/[me.khruslan.tierlistmaker.data.models.tierlist.image](../../index.md)/[Image](../index.md)/[Factory](index.md)/[fromPayload](from-payload.md)

# fromPayload

fun [fromPayload](from-payload.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), payload: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Image](../index.md)

Creates image from the payload.

Used when image must be restored from the [ClipData](https://developer.android.com/reference/kotlin/android/content/ClipData.html).

#### Return

Created image.

#### Parameters

| | |
|---|---|
| id | Unique identifier of the image. |
| payload | Either file path or drawable id. |
