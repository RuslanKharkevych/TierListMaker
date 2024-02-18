package me.khruslan.tierlistmaker.data.providers.file

import android.net.Uri
import java.io.File

/**
 * Compressor of image files.
 *
 * The compressing is asynchronous. The subclasses must ensure that it can be safely called from the
 * main thread.
 */
interface ImageCompressor {

    /**
     * Compresses the image from Uri and saves the resulting file into the target directory.
     *
     * The quality of the image depends on user preference. Throws exception on error.
     *
     * @param uri URI of the image to compress.
     * @param targetDir Target directory path.
     * @return Compressed file.
     */
    suspend fun compress(uri: Uri, targetDir: String): File
}