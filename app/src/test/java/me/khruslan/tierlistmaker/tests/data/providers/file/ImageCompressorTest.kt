package me.khruslan.tierlistmaker.tests.data.providers.file

import android.content.Context
import android.net.Uri
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import me.khruslan.tierlistmaker.data.providers.file.ImageCompressor
import me.khruslan.tierlistmaker.data.providers.file.ImageCompressorImpl
import me.khruslan.tierlistmaker.fakes.data.providers.db.FakePreferencesHelper
import me.khruslan.tierlistmaker.fakes.data.providers.dispatchers.FakeDispatcherProvider
import me.khruslan.tierlistmaker.rules.CoroutineTestRule
import me.khruslan.tierlistmaker.utils.displayWidthPixels
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
        private const val DISPLAY_WIDTH_FRACTION = 0.33f
        private const val EXTENSIONS_PACKAGE = "me.khruslan.tierlistmaker.utils.ExtensionsKt"
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

    private lateinit var fakePreferencesHelper: FakePreferencesHelper
    private lateinit var imageCompressor: ImageCompressor

    private val dummyTargetDir = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures"
    private val displayWidthPixels = 1080

    @Before
    fun init() {
        fakePreferencesHelper = FakePreferencesHelper()

        MockKAnnotations.init(this)
        mockkObject(Compress.Companion)
        mockkStatic(EXTENSIONS_PACKAGE)
        mockkConstructor(ConcreteBuilder::class)
        initMocks()

        imageCompressor = ImageCompressorImpl(
            context = mockContext,
            dispatcherProvider = FakeDispatcherProvider(),
            preferencesHelper = fakePreferencesHelper
        )
    }

    @After
    fun release() {
        unmockkObject(Compress.Companion)
        unmockkStatic(EXTENSIONS_PACKAGE)
        unmockkConstructor(ConcreteBuilder::class)
    }

    @Test
    fun `Throws exception when compression fails`() = runTest {
        val exception = IOException("Unable to compress bitmap")
        coEvery { mockCompressor.get(any()) } throws exception

        assertThrows(Throwable::class.java) {
            runTest {
                imageCompressor.compress(uri = mockUri, targetDir = dummyTargetDir)
            }
        }
    }

    @Test
    fun `Returns file when compression succeeds`() = runTest {
        val expectedFile: File = mockk()
        coEvery { mockCompressor.get(any()) } returns expectedFile
        val actualFile = imageCompressor.compress(uri = mockUri, targetDir = dummyTargetDir)

        verifyMocks()
        assertEquals(expectedFile, actualFile)
    }

    private fun initMocks() {
        every { Compress.with(mockContext, mockUri) } returns mockCompress
        every { mockCompress.setQuality(fakePreferencesHelper.imageQuality) } returns mockCompress
        every { mockCompress.setTargetDir(dummyTargetDir) } returns mockCompress
        every { mockCompress.strategy(mockCompressor) } returns mockCompressor
        every { anyConstructed<ConcreteBuilder>().build() } returns mockCompressor
        every { mockContext.displayWidthPixels } returns displayWidthPixels
    }

    private fun verifyMocks() {
        val expectedImageSize = displayWidthPixels * DISPLAY_WIDTH_FRACTION

        verifyAll {
            mockCompress.run {
                setQuality(fakePreferencesHelper.imageQuality)
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