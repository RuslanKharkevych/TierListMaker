package me.khruslan.tierlistmaker.data.tierlist

import android.content.res.Resources
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import me.khruslan.tierlistmaker.utils.extensions.requireString
import java.io.File
import java.util.*

/**
 * Base class that represents the image in a tier list.
 *
 * @property id unique identifier of the image.
 * @property payload file path or drawable id.
 * @see StorageImage
 * @see ResourceImage
 */
sealed class Image(val id: String, val payload: String) : Parcelable {

    /**
     * Factory for creating image from payload.
     */
    companion object Factory {

        /**
         * Creates [Image] from payload.
         * Used when [Image] should be restored from [Parcel].
         *
         * @param id unique identifier of the image.
         * @param payload file path or drawable id.
         * @return created [Image].
         */
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

/**
 * [Image] implementation for images stored in device file system.
 *
 * @param id unique identifier of the image.
 * @property filePath full path to the image file.
 */
class StorageImage(id: String, val filePath: String) : Image(id, filePath), Parcelable {

    /**
     * @constructor Creates [StorageImage] with random [id] from [File].
     * @param file image file.
     */
    constructor(file: File) : this(id = UUID.randomUUID().toString(), filePath = file.path)

    /**
     * @constructor Restores [StorageImage] from [Parcel].
     */
    constructor(parcel: Parcel) : this(parcel.requireString(), parcel.requireString())

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(id)
        writeString(filePath)
    }

    override fun describeContents() = 0

    /**
     * Field that generates instances of [StorageImage] from [Parcel].
     */
    companion object CREATOR : Parcelable.Creator<StorageImage> {
        override fun createFromParcel(parcel: Parcel) = StorageImage(parcel)
        override fun newArray(size: Int) = arrayOfNulls<StorageImage?>(size)
    }
}

/**
 * [Image] implementation for images from [Resources].
 *
 * @param id unique identifier of the image.
 * @property resId drawable resource id.
 */
class ResourceImage(
    id: String = UUID.randomUUID().toString(),
    @DrawableRes val resId: Int
) : Image(id, resId.toString()), Parcelable {

    /**
     * @constructor Restores [ResourceImage] from [Parcel].
     */
    constructor(parcel: Parcel) : this(parcel.requireString(), parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(id)
        writeInt(resId)
    }

    override fun describeContents() = 0

    /**
     * Field that generates instances of [ResourceImage] from [Parcel].
     */
    companion object CREATOR : Parcelable.Creator<ResourceImage> {
        override fun createFromParcel(parcel: Parcel) = ResourceImage(parcel)
        override fun newArray(size: Int) = arrayOfNulls<ResourceImage>(size)
    }
}