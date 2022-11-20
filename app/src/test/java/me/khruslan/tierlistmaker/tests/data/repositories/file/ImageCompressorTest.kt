package me.khruslan.tierlistmaker.tests.data.repositories.file

import android.content.Context
import android.net.Uri
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.data.repositories.file.ImageCompressor
import me.khruslan.tierlistmaker.data.repositories.file.ImageCompressorImpl
import me.khruslan.tierlistmaker.fakes.FakeDispatcherProvider
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import me.khruslan.tierlistmaker.utils.extensions.displayWidthPixels
import me.shouheng.compress.Compress
import me.shouheng.compress.ConcreteBuilder
import me.shouheng.compress.strategy.compress.Compressor
import me.shouheng.compress.strategy.config.ScaleMode
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.io.IOException

@ExperimentalCoroutinesApi
class ImageCompressorTest {

    companion object {
        private const val IMAGE_QUALITY_PERCENT = 90
        private const val DISPLAY_WIDTH_FRACTION = 0.33f
        private const val CONTEXT_EXTENSION_PACKAGE = "me.khruslan.tierlistmaker.utils.extensions.ContextExtensionKt"
    }

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @MockK
    private lateinit var mockContext: Context

    @MockK
    private lateinit var mockUri: Uri

    @MockK
    private lateinit var mockCompress: Compress

    @MockK
    private lateinit var mockCompressor: Compressor

    private lateinit var imageCompressor: ImageCompressor

    private val dummyTargetDir = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures"
    private val displayWidthPixels = 1080

    @Before
    fun init() {
        MockKAnnotations.init(this)
        mockkObject(Compress.Companion)
        mockkStatic(CONTEXT_EXTENSION_PACKAGE)
        mockkConstructor(ConcreteBuilder::class)
        initMocks()

        imageCompressor = ImageCompressorImpl(
            context = mockContext,
            dispatcherProvider = FakeDispatcherProvider()
        )
    }

    @After
    fun release() {
        verifyMocks()
        unmockkObject(Compress.Companion)
        unmockkStatic(CONTEXT_EXTENSION_PACKAGE)
        unmockkConstructor(ConcreteBuilder::class)
    }

    @Test
    fun `Throws exception when compression fails`() = runTest {
        val exception = IOException("Unable to compress bitmap")
        coEvery { mockCompressor.get(any()) } throws exception

        assertThrows(Throwable::class.java) {
            runBlocking {
                imageCompressor.compress(uri = mockUri, targetDir = dummyTargetDir)
            }
        }
    }

    @Test
    fun `Returns file when compression succeeds`() = runTest {
        val expectedFile: File = mockk()
        coEvery { mockCompressor.get(any()) } returns expectedFile
        val actualFile = imageCompressor.compress(uri = mockUri, targetDir = dummyTargetDir)

        assertEquals(expectedFile, actualFile)
    }

    private fun initMocks() {
        every { Compress.with(mockContext, mockUri) } returns mockCompress
        every { mockCompress.setQuality(IMAGE_QUALITY_PERCENT) } returns mockCompress
        every { mockCompress.setTargetDir(dummyTargetDir) } returns mockCompress
        every { mockCompress.strategy(mockCompressor) } returns mockCompressor
        every { anyConstructed<ConcreteBuilder>().build() } returns mockCompressor
        every { mockContext.displayWidthPixels } returns displayWidthPixels
    }

    private fun verifyMocks() {
        val expectedImageSize = displayWidthPixels * DISPLAY_WIDTH_FRACTION

        verifyAll {
            mockCompress.run {
                setQuality(IMAGE_QUALITY_PERCENT)
                setTargetDir(dummyTargetDir)
                strategy(mockCompressor)
            }

            anyConstructed<ConcreteBuilder>().run {
                withMaxWidth(expectedImageSize)
                withMaxHeight(expectedImageSize)
                withScaleMode(ScaleMode.SCALE_SMALLER)
                withIgnoreIfSmaller(true)
                build()
            }
        }
    }
}