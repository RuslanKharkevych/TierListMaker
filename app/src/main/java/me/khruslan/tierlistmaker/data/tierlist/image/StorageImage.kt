package me.khruslan.tierlistmaker.data.tierlist.image

import android.os.Parcel
import android.os.Parcelable
import me.khruslan.tierlistmaker.utils.extensions.requireString
import java.io.File
import java.util.*

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
     * @param parcel [Parcel] that contains [StorageImage].
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

    override fun equals(other: Any?) =
        other is StorageImage && id == other.id && filePath == other.filePath

    override fun hashCode() = Objects.hash(id, filePath)

    override fun toString() = "StorageImage(id=$id, filePath=$filePath)"
}