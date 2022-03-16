package me.khruslan.tierlistmaker.repository.file

import android.content.Context
import android.net.Uri
import android.os.Environment
import kotlinx.coroutines.withContext
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable
import timber.log.Timber
import java.io.File

class FileManager(
    private val context: Context,
    private val imageCompressor: ImageCompressor,
    private val dispatcherProvider: DispatcherProvidable
) {
    companion object {
        const val MIME_TYPE_IMAGE = "image/*"
    }

    suspend fun createFileFromUri(uri: Uri): File? {
        val directory = getPicturesDirectory()
        return if (directory != null) {
            imageCompressor.compress(uri, directory.path)
        } else {
            Timber.e("Unable to create file form $uri: directory is null")
            null
        }
    }

    private suspend fun getPicturesDirectory() = withContext(dispatcherProvider.io) {
        try {
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        } catch (e: Exception) {
            Timber.e(e, "Unable to access pictures directory")
            null
        }
    }
}