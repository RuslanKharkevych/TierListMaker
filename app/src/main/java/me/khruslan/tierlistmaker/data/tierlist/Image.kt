package me.khruslan.tierlistmaker.data.tierlist

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import me.khruslan.tierlistmaker.utils.extensions.requireString
import java.io.File
import java.util.*

sealed class Image(val id: String, val payload: String) : Parcelable {
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

class StorageImage(id: String, val filePath: String) : Image(id, filePath), Parcelable {
    constructor(file: File) : this(id = UUID.randomUUID().toString(), filePath = file.path)
    constructor(parcel: Parcel) : this(parcel.requireString(), parcel.requireString())

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(id)
        writeString(filePath)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<StorageImage> {
        override fun createFromParcel(parcel: Parcel) = StorageImage(parcel)
        override fun newArray(size: Int) = arrayOfNulls<StorageImage?>(size)
    }
}

class ResourceImage(
    id: String = UUID.randomUUID().toString(),
    @DrawableRes val resId: Int
) : Image(id, resId.toString()), Parcelable {
    constructor(parcel: Parcel) : this(parcel.requireString(), parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(id)
        writeInt(resId)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ResourceImage> {
        override fun createFromParcel(parcel: Parcel) = ResourceImage(parcel)
        override fun newArray(size: Int) = arrayOfNulls<ResourceImage>(size)
    }
}