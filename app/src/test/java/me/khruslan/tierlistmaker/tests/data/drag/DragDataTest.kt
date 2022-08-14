package me.khruslan.tierlistmaker.tests.data.drag

import android.content.ClipData
import io.mockk.*
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData.Companion.LABEL
import me.khruslan.tierlistmaker.dataproviders.data.DragDataProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals
import kotlin.test.assertSame

@RunWith(Parameterized::class)
class DragDataTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = DragDataProvider.dragImages
    }

    @Parameterized.Parameter
    lateinit var imageDragData: ImageDragData

    @Test
    fun `Maps image drag data to clip data`() {
        mockkStatic(ClipData::class)
        mockkConstructor(ClipData.Item::class)

        val clipData: ClipData = mockk()
        every { ClipData.newPlainText(LABEL, imageDragData.image.id) } returns clipData
        justRun { clipData.addItem(any()) }

        assertSame(clipData, imageDragData.toClipData())
        verify(exactly = 3) {
            clipData.addItem(any())
        }

        unmockkStatic(ClipData::class)
        unmockkConstructor(ClipData.Item::class)
    }

    @Test
    fun `Creates image drag data from clip data`() {
        val clipData: ClipData = mockk()
        val imageIdItem: ClipData.Item = mockk()
        val imagePayloadItem: ClipData.Item = mockk()
        val itemPositionItem: ClipData.Item = mockk()
        val tierPositionItem: ClipData.Item = mockk()

        every { clipData.getItemAt(0) } returns imageIdItem
        every { clipData.getItemAt(1) } returns imagePayloadItem
        every { clipData.getItemAt(2) } returns itemPositionItem
        every { clipData.getItemAt(3) } returns tierPositionItem
        every { imageIdItem.text } returns imageDragData.image.id
        every { imagePayloadItem.text } returns imageDragData.image.payload
        every { itemPositionItem.text } returns imageDragData.itemPosition.toString()
        every { tierPositionItem.text } returns imageDragData.tierPosition.toString()

        assertEquals(imageDragData, ImageDragData.fromClipData(clipData))
    }
}