package me.khruslan.tierlistmaker.fakes.data.providers.file

import android.graphics.Bitmap
import android.net.Uri
import me.khruslan.tierlistmaker.data.providers.file.FileManager
import java.io.File

class FakeFileManager : FileManager {
    var files = mapOf<Uri, File?>()
    var contentUris = mapOf<Pair<Bitmap, String>, Uri?>()

    override suspend fun createImageFileFromUri(uri: Uri) = files[uri]

    override suspend fun provideContentUriFromBitmap(bitmap: Bitmap, fileName: String): Uri? {
        return contentUris[bitmap to fileName]
    }
}