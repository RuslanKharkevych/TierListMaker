package me.khruslan.tierlistmaker.fakes

import android.net.Uri
import me.khruslan.tierlistmaker.repository.file.FileManager
import java.io.File

class FakeFileManager : FileManager {
    var fakeFiles = ArrayDeque<File?>()
    var processedUris = ArrayDeque<Uri>()

    override suspend fun createImageFileFromUri(uri: Uri): File? {
        processedUris.addLast(uri)
        return fakeFiles.removeFirst()
    }
}