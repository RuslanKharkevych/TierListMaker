package me.khruslan.tierlistmaker.data.tierlist

import androidx.annotation.DrawableRes
import java.io.File
import java.util.*

sealed class Image(val id: String, val payload: String) {
    companion object Factory {
        fun fromPayload(id: String, payload: String): Image {
            val resId = payload.toIntOrNull()
            return if (resId != null) {
                ResourceImage(id, resId)
            } else {
                StorageImage(id, payload)
            }
        }
    }
}

class StorageImage(id: String, val filePath: String) : Image(id, filePath) {
    constructor(file: File) : this(id = UUID.randomUUID().toString(), filePath = file.path)
}

class ResourceImage(
    id: String = UUID.randomUUID().toString(),
    @DrawableRes val resId: Int
) : Image(id, resId.toString())