package me.khruslan.tierlistmaker.tests.data.tierlist

import me.khruslan.tierlistmaker.data.tierlist.Tier
import me.khruslan.tierlistmaker.data.tierlist.TierStyle
import org.junit.Test
import kotlin.test.assertEquals

class TierTest {

    @Test
    fun `Tier class has correct qualified name`() {
        val expectedQualifiedName = "me.khruslan.tierlistmaker.data.tierlist.Tier"
        val actualQualifiedName = Tier::class.qualifiedName

        assertEquals(expectedQualifiedName, actualQualifiedName)
    }

    @Test
    fun `TierStyle class has correct qualified name`() {
        val expectedQualifiedName = "me.khruslan.tierlistmaker.data.tierlist.TierStyle"
        val actualQualifiedName = TierStyle::class.qualifiedName

        assertEquals(expectedQualifiedName, actualQualifiedName)
    }
}