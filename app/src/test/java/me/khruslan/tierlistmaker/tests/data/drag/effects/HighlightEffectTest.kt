package me.khruslan.tierlistmaker.tests.data.drag.effects

import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.effects.HighlightEffect
import me.khruslan.tierlistmaker.dataproviders.data.DragDataProvider.HighlightEffects
import me.khruslan.tierlistmaker.utils.assertSealedEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class HighlightEffectTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = HighlightEffects.data
    }

    @Parameterized.Parameter(HighlightEffects.targetParam)
    lateinit var target: DragData

    @Parameterized.Parameter(HighlightEffects.effectParam)
    lateinit var effect: HighlightEffect

    @Test
    fun `Creates highlight effect based on drag data of the target`() {
        assertSealedEquals(effect, HighlightEffect.create(target))
    }
}