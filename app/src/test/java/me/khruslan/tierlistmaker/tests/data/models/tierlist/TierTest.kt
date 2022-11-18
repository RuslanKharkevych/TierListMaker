package me.khruslan.tierlistmaker.tests.data.models.tierlist

import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierStyle
import org.junit.Assert.assertEquals
import org.junit.Test

class TierTest {

    @Test
    fun `Tier class has correct qualified name`() {
        val expectedQualifiedName = "me.khruslan.tierlistmaker.data.models.tierlist.Tier"
        val actualQualifiedName = Tier::class.qualifiedName

        assertEquals(expectedQualifiedName, actualQualifiedName)
    }

    @Test
    fun `TierStyle class has correct qualified name`() {
        val expectedQualifiedName = "me.khruslan.tierlistmaker.data.models.tierlist.TierStyle"
        val actualQualifiedName = TierStyle::class.qualifiedName

        assertEquals(expectedQualifiedName, actualQualifiedName)
    }
}