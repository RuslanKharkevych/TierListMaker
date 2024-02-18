package me.khruslan.tierlistmaker.data.models.tierlist.image

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import me.khruslan.tierlistmaker.util.requireString
import java.util.Objects
import java.util.UUID

/**
 * Image stored as a [Drawable] resource.
 *
 * Used for highlighting drag targets or when image can't be saved to a file due to an error.
 *
 * @param id Unique identifier of the image.
 * @property resId Drawable resource id.
 */
class ResourceImage(
    id: String = UUID.randomUUID().toString(),
    @DrawableRes val resId: Int
) : Image(id, resId.toString()), Parcelable {

    /**
     * Object that generates instances of [ResourceImage] from [Parcel].
     *
     * This object should be used only by Android system.
     */
    companion object CREATOR : Parcelable.Creator<ResourceImage> {

        /**
         * Creates a new instance of the resource image, instantiating it from the given parcel
         * whose data had previously been written by [writeToParcel].
         *
         * This function should be called only by Android system.
         *
         * @param parcel The parcel to read the object's data from.
         * @return A new instance of the resource image.
         */
        override fun createFromParcel(parcel: Parcel) = ResourceImage(parcel)

        /**
         * Creates a new array of resource images.
         *
         * This function should be called only by Android system.
         *
         * @param size Size of the array.
         * @return Returns an array of resource images, with every entry initialized to null.
         */
        override fun newArray(size: Int) = arrayOfNulls<ResourceImage?>(size)
    }

    /**
     * Restores resource image from the parcel.
     *
     * This constructor should be used only by [createFromParcel] function.
     *
     * @param parcel parcel that contains resource image.
     */
    constructor(parcel: Parcel) : this(parcel.requireString(), parcel.readInt())

    /**
     * Flatten this resource image into a parcel.
     *
     * This function should be called only by Android system.
     *
     * @param dest The parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written. May be 0 or
     * [Parcelable.PARCELABLE_WRITE_RETURN_VALUE].
     */
    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(id)
        writeInt(resId)
    }

    /**
     * Describes the kinds of special objects contained in the resource image instance's marshaled
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
     * Two resource images are equal if their [id] and [resId] properties are the same.
     *
     * @param other A nullable object to compare with.
     */
    override fun equals(other: Any?) =
        other is ResourceImage && id == other.id && resId == other.resId

    /**
     * Returns a hash code value for this resource image.
     *
     * The sequence of [id] and [resId] fields is hashed.
     *
     * @return A generated hash code value.
     */
    override fun hashCode() = Objects.hash(id, resId)

    /**
     * Returns a string representation of this resource image.
     *
     * This function mimics a data class format. An example output is
     * "ResourceImage(id=1, resId=2)".
     *
     * @return A string representation of this resource image.
     */
    override fun toString() = "ResourceImage(id=$id, resId=$resId)"
}