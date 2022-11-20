package me.khruslan.tierlistmaker.fakes

import android.net.Uri
import me.khruslan.tierlistmaker.data.repositories.file.FileManager
import java.io.File

class FakeFileManager : FileManager {
    val fakeFiles = ArrayDeque<File?>()
    val processedUris = ArrayDeque<Uri>()

    override suspend fun createImageFileFromUri(uri: Uri): File? {
        processedUris.addLast(uri)
        return fakeFiles.removeFirst()
    }
}