package me.khruslan.tierlistmaker.data.models.tierlist.image

import android.os.Parcel
import android.os.Parcelable

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