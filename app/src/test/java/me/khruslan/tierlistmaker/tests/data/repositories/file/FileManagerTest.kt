package me.khruslan.tierlistmaker.tests.data.repositories.file

import android.content.Context
import android.net.Uri
import android.os.Environment
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.data.repositories.file.FileManager
import me.khruslan.tierlistmaker.data.repositories.file.FileManagerImpl
import me.khruslan.tierlistmaker.fakes.FakeDispatcherProvider
import me.khruslan.tierlistmaker.fakes.FakeImageCompressor
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.io.IOException

@ExperimentalCoroutinesApi
class FileManagerTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var mockContext: Context

    @MockK
    private lateinit var mockUri: Uri

    private lateinit var fakeImageCompressor: FakeImageCompressor
    private lateinit var fileManager: FileManager

    @Before
    fun init() {
        MockKAnnotations.init(this)
        val fakeDispatcherProvider = FakeDispatcherProvider()
        fakeImageCompressor = FakeImageCompressor()
        fileManager = FileManagerImpl(mockContext, fakeImageCompressor, fakeDispatcherProvider)
    }

    @Test
    fun `Doesn't create image if pictures directory is unavailable`() = runTest {
        every { mockContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) } returns null
        assertNull(fileManager.createImageFileFromUri(mockUri))
    }

    @Test
    fun `Doesn't create image if error occurs during reading pictures directory`() = runTest {
        val exception = IOException("Unable to access external files directory")
        every { mockContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) } throws exception

        assertNull(fileManager.createImageFileFromUri(mockUri))
    }

    @Test
    fun `Doesn't create image if compression fails`() = runTest {
        val directory: File = mockk()
        val path = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures"
        val exception = IOException("Failed to compress file")
        every { mockContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) } returns directory
        every { directory.path } returns path
        fakeImageCompressor.compressedFileResult = Result.failure(exception)

        assertNull(fileManager.createImageFileFromUri(mockUri))
        assertEquals(mockUri, fakeImageCompressor.processedUri)
        assertEquals(path, fakeImageCompressor.processedTargetDir)
    }

    @Test
    fun `Successfully creates compressed image file`() = runTest {
        val directory: File = mockk()
        val path = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures"
        val expectedFile: File = mockk()
        every { mockContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) } returns directory
        every { directory.path } returns path
        fakeImageCompressor.compressedFileResult = Result.success(expectedFile)

        assertEquals(expectedFile, fileManager.createImageFileFromUri(mockUri))
        assertEquals(mockUri, fakeImageCompressor.processedUri)
        assertEquals(path, fakeImageCompressor.processedTargetDir)
    }
}