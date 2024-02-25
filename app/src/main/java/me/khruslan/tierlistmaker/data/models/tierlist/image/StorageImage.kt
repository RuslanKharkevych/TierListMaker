package me.khruslan.tierlistmaker.data.models.tierlist.image

import android.os.Parcel
import android.os.Parcelable
import me.khruslan.tierlistmaker.util.requireString
import java.io.File
import java.util.*

/**
 * Image stored as a file.
 *
 * Used for regular tier list images. The image file can be stored either in device file system or
 * in assets.
 *
 * @param id Unique identifier of the image.
 * @property filePath Full path to the image file.
 * @constructor Creates storage image with ID and file path.
 */
class StorageImage(id: String, val filePath: String) : Image(id, filePath), Parcelable {

    /**
     * Object that generates instances of [StorageImage] from [Parcel].
     *
     * This object should be used only by Android system.
     */
    companion object CREATOR : Parcelable.Creator<StorageImage> {

        /**
         * Creates a new instance of the storage image, instantiating it from the given parcel
         * whose data had previously been written by [writeToParcel].
         *
         * This function should be called only by Android system.
         *
         * @param parcel The parcel to read the object's data from.
         * @return A new instance of the storage image.
         */
        override fun createFromParcel(parcel: Parcel) = StorageImage(parcel)

        /**
         * Creates a new array of storage images.
         *
         * This function should be called only by Android system.
         *
         * @param size Size of the array.
         * @return Returns an array of storage images, with every entry initialized to null.
         */
        override fun newArray(size: Int) = arrayOfNulls<StorageImage?>(size)
    }

    /**
     * Creates storage image from the file path with a random ID.
     *
     * @param filePath Image file path.
     */
    constructor(filePath: String) : this(id = UUID.randomUUID().toString(), filePath = filePath)

    /**
     * Creates storage image from the file with a random ID.
     *
     * @param file Image file.
     */
    constructor(file: File) : this(filePath = file.path)

    /**
     * Restores storage image from parcel.
     *
     * This constructor should be used only by [createFromParcel] function.
     *
     * @param parcel Parcel that contains storage image.
     */
    constructor(parcel: Parcel) : this(parcel.requireString(), parcel.requireString())

    /**
     * Flatten this storage image into a parcel.
     *
     * This function should be called only by Android system.
     *
     * @param dest The parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written. May be 0 or
     * [Parcelable.PARCELABLE_WRITE_RETURN_VALUE].
     */
    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeString(filePath)
    }

    /**
     * Describes the kinds of special objects contained in the storage image instance's marshaled
     * representation.
     *
     * This function should be called only by Android system.
     *
     * @return A bitmask indicating the set of special object types marshaled.
     */
    override fun describeContents() = 0

    /**
     * Indicates whether some other object is equal to this one.
     *
     * Two storage images are equal if their [id] and [filePath] properties are the same.
     *
     * @param other A nullable object to compare with.
     */
    override fun equals(other: Any?) =
        other is StorageImage && id == other.id && filePath == other.filePath

    /**
     * Returns a hash code value for this storage image.
     *
     * The sequence of [id] and [filePath] fields is hashed.
     *
     * @return A generated hash code value.
     */
    override fun hashCode() = Objects.hash(id, filePath)

    /**
     * Returns a string representation of this storage image.
     *
     * This function mimics a data class format. An example output is
     * "StorageImage(id=1, filePath=images/file.jpg)".
     *
     * @return A string representation of this storage image.
     */
    override fun toString() = "StorageImage(id=$id, filePath=$filePath)"
}