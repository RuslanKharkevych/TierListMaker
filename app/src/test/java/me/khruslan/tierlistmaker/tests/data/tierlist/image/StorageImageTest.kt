package me.khruslan.tierlistmaker.tests.data.tierlist.image

import android.os.Parcel
import io.mockk.*
import me.khruslan.tierlistmaker.data.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.tierlist.image.StorageImage
import org.junit.Assert.*
import org.junit.Test

class StorageImageTest {

    private val storageImage = StorageImage(
        id = "67cae7d3-1d75-495f-8410-34d390cff96a",
        filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1649976463524.jpeg"
    )

    @Test
    fun `StorageImage class has correct qualified name`() {
        val expectedQualifiedName = "me.khruslan.tierlistmaker.data.tierlist.image.StorageImage"
        val actualQualifiedName = StorageImage::class.qualifiedName

        assertEquals(expectedQualifiedName, actualQualifiedName)
    }

    @Test
    fun `Writes StorageImage to Parcel`() {
        val parcel: Parcel = mockk()
        justRun { parcel.writeString(storageImage.id) }
        justRun { parcel.writeString(storageImage.filePath) }

        storageImage.writeToParcel(parcel, 0)

        verifyOrder {
            parcel.writeString(storageImage.id)
            parcel.writeString(storageImage.filePath)
        }
    }

    @Test
    fun `describeContents always returns 0`() {
        assertEquals(0, storageImage.describeContents())
    }

    @Test
    fun `Creates StorageImage from Parcel`() {
        val parcel: Parcel = mockk()
        every { parcel.readString() }.returnsMany(storageImage.id, storageImage.filePath)

        assertEquals(storageImage, StorageImage.createFromParcel(parcel))
        verify(exactly = 2) { parcel.readString() }
    }

    @Test
    fun `When first readString call returns null should throw IllegalStateException`() {
        val parcel: Parcel = mockk()
        every { parcel.readString() } returns null

        assertThrows(IllegalStateException::class.java) {
            StorageImage.createFromParcel(parcel)
        }
    }

    @Test
    fun `When second readString call returns null should throw IllegalStateException`() {
        val parcel: Parcel = mockk()
        every { parcel.readString() }.returnsMany(storageImage.id, null)

        assertThrows(IllegalStateException::class.java) {
            StorageImage.createFromParcel(parcel)
        }
    }

    @Test
    fun `Creates new array of nulls with given size`() {
        val arraySize = 3
        val expectedArray = arrayOfNulls<StorageImage>(arraySize)
        val actualArray = StorageImage.newArray(arraySize)

        assertArrayEquals(expectedArray, actualArray)
    }

    @Test
    fun `Calculates hash code for StorageImage`() {
        val expectedHashCode = -128680437
        val actualHashCode = storageImage.hashCode()

        assertEquals(expectedHashCode, actualHashCode)
    }

    @Test
    fun `Converts StorageImage to String`() {
        val expectedString =  "StorageImage(id=67cae7d3-1d75-495f-8410-34d390cff96a, filePath=/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1649976463524.jpeg)"
        val actualString = storageImage.toString()

        assertEquals(expectedString, actualString)
    }

    @Test
    fun `When object to compare has different type equals returns false`() {
        val objectToCompare = ResourceImage(
            id = "eaff63e8-4229-4461-b252-d788404c1a90",
            resId = android.R.drawable.ic_menu_gallery
        )

        assertNotEquals(storageImage, objectToCompare)
    }

    @Test
    fun `When object to compare has different id equals returns false`() {
        val objectToCompare = StorageImage(
            id = "eaff63e8-4229-4461-b252-d788404c1a90",
            filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1649976463524.jpeg"
        )

        assertNotEquals(storageImage, objectToCompare)
    }

    @Test
    fun `When object to compare has different resId equals returns false`() {
        val objectToCompare = StorageImage(
            id = "67cae7d3-1d75-495f-8410-34d390cff96a",
            filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1928863159510.jpeg"
        )

        assertNotEquals(storageImage, objectToCompare)
    }

    @Test
    fun `When object to compare has same id and resId equals returns true`() {
        val objectToCompare = StorageImage(
            id = "67cae7d3-1d75-495f-8410-34d390cff96a",
            filePath = "/storage/emulated/0/Android/data/me.khruslan.tierlistmaker/files/Pictures/1649976463524.jpeg"
        )

        assertEquals(storageImage, objectToCompare)
    }
}