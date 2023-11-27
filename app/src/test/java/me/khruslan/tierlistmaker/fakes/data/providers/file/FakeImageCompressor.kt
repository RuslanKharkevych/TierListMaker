package me.khruslan.tierlistmaker.fakes.data.providers.file

import android.net.Uri
import me.khruslan.tierlistmaker.data.providers.file.ImageCompressor
import java.io.File

class FakeImageCompressor : ImageCompressor {

    val fileResults = mutableMapOf<Pair<Uri, String>, Result<File>>()

    override suspend fun compress(uri: Uri, targetDir: String): File {
        return checkNotNull(fileResults[uri to targetDir]).getOrThrow()
    }
}