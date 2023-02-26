package me.khruslan.tierlistmaker.data.repositories.file

import android.graphics.Bitmap
import android.net.Uri
import java.io.File

/**
 * Manager for working with files. Used for persisting tier list images.
 */
interface FileManager {

    /**
     * [FileManager] companion object. Used for storing constants.
     */
    companion object {
        const val MIME_TYPE_IMAGE = "image/*"
        const val MIME_TYPE_IMAGE_JPEG = "image/jpeg"
        const val FILENAME_EXTENSION_JPEG = ".jpeg"
    }

    /**
     * Saves [File] from [Uri] into the external pictures directory.
     *
     * @param uri [Uri] of the image file.
     * @return Created [File] or **null** in case of error.
     */
    suspend fun createImageFileFromUri(uri: Uri): File?

    /**
     * Returns a content [Uri] for a [File], created from [bitmap].
     *
     * @param bitmap [Bitmap] that should be written to file.
     * @param fileName the name of the file to be created.
     * @return content [Uri] of the created file or **null** in case of error.
     */
    suspend fun provideContentUriFromBitmap(bitmap: Bitmap, fileName: String): Uri?
}