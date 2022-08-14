package me.khruslan.tierlistmaker.tests.data.tierlist.image

import android.os.Parcel
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verifyOrder
import me.khruslan.tierlistmaker.data.tierlist.image.Image
import me.khruslan.tierlistmaker.data.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.dataproviders.data.TierListDataProvider
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Suite
import java.io.File
import kotlin.test.assertEquals

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
@RunWith(Suite::class)
@Suite.SuiteClasses(
    ResourceImageTest.StandardTest::class,
    ResourceImageTest.ParameterizedResourceImageTest::class,
    ResourceImageTest.ParameterizedFilePathsTest::class,
    ResourceImageTest.ParameterizedArraySizesTest::class,
    ResourceImageTest.ParameterizedHashCodesTest::class,
    ResourceImageTest.ParameterizedStringsTest::class,
    ResourceImageTest.ParameterizedEqualityResultsTest::class
)
class ResourceImageTest {

    class StandardTest {

        @Test
        fun `ResourceImage class has correct qualified name`() {
            val expectedQualifiedName = "me.khruslan.tierlistmaker.data.tierlist.image.ResourceImage"
            val actualQualifiedName = ResourceImage::class.qualifiedName

            assertEquals(expectedQualifiedName, actualQualifiedName)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedResourceImageTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = TierListDataProvider.resourceImages
        }

        @Parameterized.Parameter
        lateinit var resourceImage: ResourceImage

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
        fun `describeContents always returns 0`() {
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
    }

    @RunWith(Parameterized::class)
    class ParameterizedFilePathsTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = TierListDataProvider.filePaths
        }

        @Parameterized.Parameter
        lateinit var filePath: String

        @Test
        fun `Creates StorageImage from file`() {
            val file: File = mockk()
            every { file.path } returns filePath

            assertEquals(filePath, StorageImage(file).filePath)
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
            val expectedArray = arrayOfNulls<ResourceImage>(arraySize.toInt())
            val actualArray = ResourceImage.newArray(arraySize.toInt())

            assertArrayEquals(expectedArray, actualArray)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedHashCodesTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = TierListDataProvider.ResourceImageHashCodes.data
        }

        @Parameterized.Parameter(TierListDataProvider.ResourceImageHashCodes.imageParam)
        lateinit var image: ResourceImage

        @Parameterized.Parameter(TierListDataProvider.ResourceImageHashCodes.hashCodeParam)
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
            fun data() = TierListDataProvider.ResourceImageStrings.data
        }

        @Parameterized.Parameter(TierListDataProvider.ResourceImageStrings.imageParam)
        lateinit var image: ResourceImage

        @Parameterized.Parameter(TierListDataProvider.ResourceImageStrings.stringParam)
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
            fun data() = TierListDataProvider.ResourceImageEqualityResults.data
        }

        @Parameterized.Parameter(TierListDataProvider.ResourceImageEqualityResults.imageParam)
        lateinit var image: ResourceImage

        @Parameterized.Parameter(TierListDataProvider.ResourceImageEqualityResults.otherParam)
        lateinit var other: Image

        @Parameterized.Parameter(TierListDataProvider.ResourceImageEqualityResults.equalityResult)
        lateinit var equalityResult: java.lang.Boolean

        @Test
        fun `Compares ResourceImage with other object for equality`() {
            assertEquals(equalityResult as Boolean, image == other)
        }
    }
}