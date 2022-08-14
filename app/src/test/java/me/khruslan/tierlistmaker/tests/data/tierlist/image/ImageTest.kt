package me.khruslan.tierlistmaker.tests.data.tierlist.image

import me.khruslan.tierlistmaker.data.tierlist.image.Image
import me.khruslan.tierlistmaker.dataproviders.data.TierListDataProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Suite
import kotlin.test.assertEquals

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ImageTest.StandardTest::class,
    ImageTest.ParameterizedImagePayloadsTest::class
)
class ImageTest {

    class StandardTest {
        @Test
        fun `Image class has correct qualified name`() {
            val expectedQualifiedName = "me.khruslan.tierlistmaker.data.tierlist.image.Image"
            val actualQualifiedName = Image::class.qualifiedName

            assertEquals(expectedQualifiedName, actualQualifiedName)
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedImagePayloadsTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = TierListDataProvider.ImagePayloads.data
        }

        @Parameterized.Parameter(TierListDataProvider.ImagePayloads.idParam)
        lateinit var id: String

        @Parameterized.Parameter(TierListDataProvider.ImagePayloads.payloadParam)
        lateinit var payload: String

        @Parameterized.Parameter(TierListDataProvider.ImagePayloads.imageParam)
        lateinit var image: Image

        @Test
        fun `Creates image from payload`() {
            assertEquals(image, Image.fromPayload(id, payload))
        }
    }
}