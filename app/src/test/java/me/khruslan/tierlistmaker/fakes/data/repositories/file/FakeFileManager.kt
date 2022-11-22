package me.khruslan.tierlistmaker.fakes.data.repositories.file

import android.net.Uri
import me.khruslan.tierlistmaker.data.repositories.file.FileManager
import java.io.File

class FakeFileManager : FileManager {
    var files = mapOf<Uri, File?>()
    override suspend fun createImageFileFromUri(uri: Uri) = files[uri]
}