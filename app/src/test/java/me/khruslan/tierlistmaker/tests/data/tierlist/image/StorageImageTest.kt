package me.khruslan.tierlistmaker.tests.data.tierlist.image

import android.os.Parcel
import io.mockk.*
import me.khruslan.tierlistmaker.data.tierlist.image.Image
import me.khruslan.tierlistmaker.data.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.dataproviders.data.TierListDataProvider
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Suite
import kotlin.test.assertEquals

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
@RunWith(Suite::class)
@Suite.SuiteClasses(
    StorageImageTest.StandardTest::class,
    StorageImageTest.ParameterizedStorageImageTest::class,
    StorageImageTest.ParameterizedArraySizesTest::class,
    StorageImageTest.ParameterizedHashCodesTest::class,
    StorageImageTest.ParameterizedStringsTest::class,
    StorageImageTest.ParameterizedEqualityResultsTest::class
)
class StorageImageTest {

    class StandardTest {

        @Test
        fun `StorageImage class has correct qualified name`() {
            val expectedQualifiedName = "me.khruslan.tierlistmaker.data.tierlist.image.StorageImage"
            val actualQualifiedName = StorageImage::class.qualifiedName

            assertEquals(expectedQualifiedName, actualQualifiedName)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedStorageImageTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = TierListDataProvider.storageImages
        }

        @Parameterized.Parameter
        lateinit var storageImage: StorageImage

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
    }

    @RunWith(Parameterized::class)
    class ParameterizedArraySizesTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = TierListDataProvider.arraySizes
        }

        @Parameterized.Parameter
        lateinit var arraySize: Integer

        @Test
        fun `Creates new array of nulls with given size`() {
            val expectedArray = arrayOfNulls<StorageImage>(arraySize.toInt())
            val actualArray = StorageImage.newArray(arraySize.toInt())

            assertArrayEquals(expectedArray, actualArray)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedHashCodesTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = TierListDataProvider.StorageImageHashCodes.data
        }

        @Parameterized.Parameter(TierListDataProvider.StorageImageHashCodes.imageParam)
        lateinit var image: StorageImage

        @Parameterized.Parameter(TierListDataProvider.StorageImageHashCodes.hashCodeParam)
        lateinit var hashCode: Integer

        @Test
        fun `Calculates hash code for ResourceImage`() {
            assertEquals(hashCode.toInt(), image.hashCode())
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedStringsTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = TierListDataProvider.StorageImageStrings.data
        }

        @Parameterized.Parameter(TierListDataProvider.StorageImageStrings.imageParam)
        lateinit var image: StorageImage

        @Parameterized.Parameter(TierListDataProvider.StorageImageStrings.stringParam)
        lateinit var string: String

        @Test
        fun `Converts ResourceImage to String`() {
            assertEquals(string, image.toString())
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedEqualityResultsTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = TierListDataProvider.StorageImageEqualityResults.data
        }

        @Parameterized.Parameter(TierListDataProvider.StorageImageEqualityResults.imageParam)
        lateinit var image: StorageImage

        @Parameterized.Parameter(TierListDataProvider.StorageImageEqualityResults.otherParam)
        lateinit var other: Image

        @Parameterized.Parameter(TierListDataProvider.StorageImageEqualityResults.equalityResult)
        lateinit var equalityResult: java.lang.Boolean

        @Test
        fun `Compares ResourceImage with other object for equality`() {
            assertEquals(equalityResult as Boolean, image == other)
        }
    }
}