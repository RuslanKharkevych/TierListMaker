package me.khruslan.tierlistmaker.repository.file

import android.net.Uri
import java.io.File

/**
 * Compressor of image files.
 */
interface ImageCompressor {

    /**
     * Compresses the image from [Uri] and saves the resulting file into the target directory.
     *
     * @param uri [Uri] of the image to compress.
     * @param targetDir target directory path.
     * @return Compressed [File] or **null** in case of error.
     */
    suspend fun compress(uri: Uri, targetDir: String): File?
}