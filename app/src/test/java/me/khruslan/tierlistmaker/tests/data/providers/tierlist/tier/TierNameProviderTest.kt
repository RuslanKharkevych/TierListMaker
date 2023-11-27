package me.khruslan.tierlistmaker.tests.data.providers.tierlist.tier

import me.khruslan.tierlistmaker.data.providers.tierlist.tier.TierNameProvider
import me.khruslan.tierlistmaker.data.providers.tierlist.tier.TierNameProviderImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class TierNameProviderTest {

    private lateinit var tierNameProvider: TierNameProvider

    @Before
    fun init() {
        tierNameProvider = TierNameProviderImpl()
    }

    @Test
    fun `Returns 'S' for the first tier`() {
        assertEquals("S", tierNameProvider.getNameByIndex(0))
    }

    @Test
    fun `Returns 'N' for the fifteenth tier`() {
        assertEquals("N", tierNameProvider.getNameByIndex(14))
    }

    @Test
    fun `Returns 'Z' for the twenty-seventh tier`() {
        assertEquals("Z", tierNameProvider.getNameByIndex(26))
    }

    @Test
    fun `Throws IllegalArgumentException for the twenty-eighth tier`() {
        assertThrows(IllegalArgumentException::class.java) {
            tierNameProvider.getNameByIndex(27)
        }
    }
}