package me.khruslan.tierlistmaker.fakes

import android.net.Uri
import me.khruslan.tierlistmaker.data.repositories.file.ImageCompressor
import java.io.File

class FakeImageCompressor : ImageCompressor {

    var compressedFileResult = Result.failure<File>(UninitializedPropertyAccessException())

    var processedUri: Uri? = null
        private set

    var processedTargetDir: String? = null
        private set

    override suspend fun compress(uri: Uri, targetDir: String): File {
        processedUri = uri
        processedTargetDir = targetDir
        return compressedFileResult.getOrThrow()
    }
}