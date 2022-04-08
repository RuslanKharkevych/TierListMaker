package me.khruslan.tierlistmaker.repository.file

import android.net.Uri
import java.io.File

/**
 * Manager for working with files. Used for persisting tier list images.
 */
interface FileManager {

    /**
     * Saves [File] from [Uri] into the external pictures directory.
     *
     * @param uri [Uri] of the image file.
     * @return Created [File] or **null** in case of error.
     */
    suspend fun createImageFileFromUri(uri: Uri): File?
}