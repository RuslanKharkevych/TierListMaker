package me.khruslan.tierlistmaker.tests.data.tierlist.image

import me.khruslan.tierlistmaker.data.tierlist.image.Image
import me.khruslan.tierlistmaker.data.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.tierlist.image.StorageImage
import org.junit.Assert.assertEquals
import org.junit.Test

class ImageTest {

    @Test
    fun `Image class has correct qualified name`() {
        val expectedQualifiedName = "me.khruslan.tierlistmaker.data.tierlist.image.Image"
        val actualQualifiedName = Image::class.qualifiedName

        assertEquals(expectedQualifiedName, actualQualifiedName)
    }

    @Test
    fun `Creates ResourceImage from payload`() {
        val id = "67cae7d3-1d75-495f-8410-34d390cff96a"
        val payload = android.R.drawable.ic_menu_gallery.toString()
        val expectedImage = ResourceImage(id = id, resId = android.R.drawable.ic_menu_gallery)

        assertEquals(expectedImage, Image.fromPayload(id, payload))
    }

    @Test
    fun `Creates StorageImage from payload`() {
        val id = "401470da-6034-4e48-a53b-37e23834c897"
        val payload = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1649976463524.jpeg"
        val expectedImage = StorageImage(id = id, filePath = payload)

        assertEquals(expectedImage, Image.fromPayload(id, payload))
    }
}