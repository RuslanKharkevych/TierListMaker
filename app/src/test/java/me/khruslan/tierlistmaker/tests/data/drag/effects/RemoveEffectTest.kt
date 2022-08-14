package me.khruslan.tierlistmaker.tests.data.drag.effects

import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.effects.RemoveEffect
import me.khruslan.tierlistmaker.dataproviders.data.DragDataProvider.RemoveEffects
import me.khruslan.tierlistmaker.utils.assertSealedEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class RemoveEffectTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = RemoveEffects.data
    }

    @Parameterized.Parameter(RemoveEffects.targetParam)
    lateinit var target: DragData

    @Parameterized.Parameter(RemoveEffects.effectParam)
    lateinit var effect: RemoveEffect

    @Test
    fun `Creates remove effect based on drag data`() {
        assertSealedEquals(effect, RemoveEffect.create(target))
    }
}