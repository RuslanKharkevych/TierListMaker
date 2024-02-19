package me.khruslan.tierlistmaker.tests.data.providers.file

import android.content.Context
import android.net.Uri
import android.os.Environment
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.data.providers.file.FileManager
import me.khruslan.tierlistmaker.data.providers.file.FileManagerImpl
import me.khruslan.tierlistmaker.fakes.data.providers.dispatchers.FakeDispatcherProvider
import me.khruslan.tierlistmaker.fakes.data.providers.file.FakeImageCompressor
import me.khruslan.tierlistmaker.fakes.utils.performance.NoOpPerformanceService
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
        fakeImageCompressor = FakeImageCompressor()
        fileManager = FileManagerImpl(
            context = mockContext,
            imageCompressor = fakeImageCompressor,
            dispatcherProvider = FakeDispatcherProvider(),
            performanceService = NoOpPerformanceService()
        )
    }

    @Test
    fun `Successfully creates compressed image file in pictures directory`() = runTest {
        val directory: File = mockk()
        val path = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures"
        val expectedFile: File = mockk()
        every { mockContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) } returns directory
        every { directory.path } returns path
        fakeImageCompressor.fileResults[mockUri to path] = Result.success(expectedFile)

        assertEquals(expectedFile, fileManager.createImageFileFromUri(mockUri))
    }

    @Test
    fun `Uses internal storage if pictures directory is unavailable`() = runTest {
        val directory: File = mockk()
        val path = "/data/data/me.khruslan.tierlistmaker/files"
        val expectedFile: File = mockk()
        every { mockContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) } returns null
        every { mockContext.filesDir } returns directory
        every { directory.path } returns path
        fakeImageCompressor.fileResults[mockUri to path] = Result.success(expectedFile)

        assertEquals(expectedFile, fileManager.createImageFileFromUri(mockUri))
    }

    @Test
    fun `Uses internal storage if error occurs during reading pictures directory`() = runTest {
        val directory: File = mockk()
        val path = "/data/data/me.khruslan.tierlistmaker/files"
        val expectedFile: File = mockk()
        val exception = IOException("Unable to access external files directory")
        every { mockContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) } throws exception
        every { mockContext.filesDir } returns directory
        every { directory.path } returns path
        fakeImageCompressor.fileResults[mockUri to path] = Result.success(expectedFile)

        assertEquals(expectedFile, fileManager.createImageFileFromUri(mockUri))
    }

    @Test
    fun `Doesn't create image if compression fails`() = runTest {
        val directory: File = mockk()
        val path = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures"
        val exception = IOException("Failed to compress file")
        every { mockContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) } returns directory
        every { directory.path } returns path
        fakeImageCompressor.fileResults[mockUri to path] = Result.failure(exception)

        assertNull(fileManager.createImageFileFromUri(mockUri))
    }
}