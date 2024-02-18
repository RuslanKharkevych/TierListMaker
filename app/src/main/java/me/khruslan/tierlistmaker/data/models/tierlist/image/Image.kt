package me.khruslan.tierlistmaker.data.models.tierlist.image

import android.content.ClipData
import android.os.Parcelable
import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Base class that represents the image in the [TierList].
 *
 * Image can be stored as files or drawable resources. This class has immutable contract. Its
 * subclasses must implement [Parcelable] interface. They can be stored in the database or passed as
 * navigation arguments.
 *
 * @property id Unique identifier of the image.
 * @property payload Either file path or drawable id. Used to convert image to the clip data.
 * @constructor Default constructor for use by subclasses.
 */
sealed class Image(val id: String, val payload: String) : Parcelable {

    /**
     * Factory for creating images from payload.
     */
    companion object Factory {

        /**
         * Creates image from the payload.
         *
         * Used when image must be restored from the [ClipData].
         *
         * @param id Unique identifier of the image.
         * @param payload Either file path or drawable id.
         * @return Created image.
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