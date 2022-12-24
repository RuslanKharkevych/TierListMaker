package me.khruslan.tierlistmaker.data.models.tierlist.image

import android.content.res.Resources
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import me.khruslan.tierlistmaker.utils.requireString
import java.util.*

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
     * @param parcel [Parcel] that contains [ResourceImage].
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
        override fun newArray(size: Int) = arrayOfNulls<ResourceImage?>(size)
    }

    override fun equals(other: Any?) =
        other is ResourceImage && id == other.id && resId == other.resId

    override fun hashCode() = Objects.hash(id, resId)

    override fun toString() = "ResourceImage(id=$id, resId=$resId)"
}