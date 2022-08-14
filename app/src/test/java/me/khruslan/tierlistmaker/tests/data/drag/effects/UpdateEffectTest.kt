package me.khruslan.tierlistmaker.tests.data.drag.effects

import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.effects.UpdateEffect
import me.khruslan.tierlistmaker.dataproviders.data.DragDataProvider.UpdateEffects
import me.khruslan.tierlistmaker.utils.assertSealedEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class UpdateEffectTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = UpdateEffects.data
    }

    @Parameterized.Parameter(UpdateEffects.shadowParam)
    lateinit var shadow: ImageDragData

    @Parameterized.Parameter(UpdateEffects.targetParam)
    lateinit var target: DragData

    @Parameterized.Parameter(UpdateEffects.effectParam)
    lateinit var effect: UpdateEffect

    @Test
    fun `Creates update effect based on shadow and target`() {
        assertSealedEquals(effect, UpdateEffect.create(shadow, target))
    }
}