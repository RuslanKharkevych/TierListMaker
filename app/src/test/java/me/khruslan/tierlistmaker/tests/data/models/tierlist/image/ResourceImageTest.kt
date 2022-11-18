package me.khruslan.tierlistmaker.tests.data.models.tierlist.image

import android.os.Parcel
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verifyOrder
import me.khruslan.tierlistmaker.data.models.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage
import org.junit.Assert.*
import org.junit.Test

class ResourceImageTest {

    private val resourceImage = ResourceImage(
        id = "67cae7d3-1d75-495f-8410-34d390cff96a",
        resId = android.R.drawable.ic_menu_gallery
    )

    @Test
    fun `ResourceImage class has correct qualified name`() {
        val expectedQualifiedName = "me.khruslan.tierlistmaker.data.models.tierlist.image.ResourceImage"
        val actualQualifiedName = ResourceImage::class.qualifiedName

        assertEquals(expectedQualifiedName, actualQualifiedName)
    }

    @Test
    fun `Writes ResourceImage to Parcel`() {
        val parcel: Parcel = mockk()
        justRun { parcel.writeString(resourceImage.id) }
        justRun { parcel.writeInt(resourceImage.resId) }

        resourceImage.writeToParcel(parcel, 0)

        verifyOrder {
            parcel.writeString(resourceImage.id)
            parcel.writeInt(resourceImage.resId)
        }
    }

    @Test
    fun `describeContents returns 0`() {
        assertEquals(0, resourceImage.describeContents())
    }

    @Test
    fun `Creates ResourceImage from Parcel`() {
        val parcel: Parcel = mockk()
        every { parcel.readString() } returns resourceImage.id
        every { parcel.readInt() } returns resourceImage.resId

        assertEquals(resourceImage, ResourceImage.createFromParcel(parcel))
        verifyOrder {
            parcel.readString()
            parcel.readInt()
        }
    }

    @Test
    fun `When readString returns null should throw IllegalStateException`() {
        val parcel: Parcel = mockk()
        every { parcel.readString() } returns null

        assertThrows(IllegalStateException::class.java) {
            ResourceImage.createFromParcel(parcel)
        }
    }

    @Test
    fun `Creates new array of nulls with given size`() {
        val arraySize = 5
        val expectedArray = arrayOfNulls<ResourceImage>(arraySize)
        val actualArray = ResourceImage.newArray(arraySize)

        assertArrayEquals(expectedArray, actualArray)
    }

    @Test
    fun `Calculates hash code for ResourceImage`() {
        val expectedHashCode = -785202258
        val actualHashCode = resourceImage.hashCode()

        assertEquals(expectedHashCode, actualHashCode)
    }

    @Test
    fun `Converts ResourceImage to String`() {
        val expectedString = "ResourceImage(id=67cae7d3-1d75-495f-8410-34d390cff96a, resId=${android.R.drawable.ic_menu_gallery})"
        val actualString = resourceImage.toString()

        assertEquals(expectedString, actualString)
    }

    @Test
    fun `When object to compare has different type equals returns false`() {
        val objectToCompare = StorageImage(
            id = "de638d0d-7dee-4f9b-83d6-795bc6ab60f0",
            filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/9226934535226.png"
        )

        assertNotEquals(resourceImage, objectToCompare)
    }

    @Test
    fun `When object to compare has different id equals returns false`() {
        val objectToCompare = ResourceImage(
            id = "de638d0d-7dee-4f9b-83d6-795bc6ab60f0",
            resId = android.R.drawable.ic_menu_gallery
        )

        assertNotEquals(resourceImage, objectToCompare)
    }

    @Test
    fun `When object to compare has different resId equals returns false`() {
        val objectToCompare = ResourceImage(
            id = "67cae7d3-1d75-495f-8410-34d390cff96a",
            resId = android.R.drawable.ic_menu_camera
        )

        assertNotEquals(resourceImage, objectToCompare)
    }

    @Test
    fun `When object to compare has same id and resId equals returns true`() {
        val objectToCompare = ResourceImage(
            id = "67cae7d3-1d75-495f-8410-34d390cff96a",
            resId = android.R.drawable.ic_menu_gallery
        )

        assertEquals(resourceImage, objectToCompare)
    }
}