package me.khruslan.tierlistmaker.data.providers.file

import android.graphics.Bitmap
import android.net.Uri
import java.io.File

/**
 * Manager for working with files.
 *
 * All functions are asynchronous. The subclasses must ensure that all functions can be safely
 * called from the main thread. All exceptions must be handled internally. The result of the
 * operation is returned as a nullable type.
 */
interface FileManager {

    /**
     * MIME types and file extensions.
     */
    companion object Constants {

        /**
         * MIME type of all image files.
         */
        const val MIME_TYPE_IMAGE = "image/*"

        /**
         * MIME type of the JPEG image file.
         */
        const val MIME_TYPE_IMAGE_JPEG = "image/jpeg"

        /**
         * Extension of the JPEG image file.
         */
        const val FILENAME_EXTENSION_JPEG = ".jpeg"
    }

    /**
     * Creates image file from URI and saves it in the file system.
     *
     * @param uri URI of the image file.
     * @return Created file or null in case of error.
     */
    suspend fun createImageFileFromUri(uri: Uri): File?

    /**
     * Returns a content URI for a file, created from bitmap.
     *
     * @param bitmap Bitmap that should be written to file.
     * @param fileName The name of the file to be created.
     * @return Content URI of the created file or null in case of error.
     */
    suspend fun provideContentUriFromBitmap(bitmap: Bitmap, fileName: String): Uri?
}