package me.khruslan.tierlistmaker.tests.data.drag.effects

import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.effects.InsertEffect
import me.khruslan.tierlistmaker.dataproviders.data.DragDataProvider.InsertImageEffects
import me.khruslan.tierlistmaker.dataproviders.data.DragDataProvider.InsertTargetEffects
import me.khruslan.tierlistmaker.utils.assertSealedEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    InsertEffectTest.ParameterizedInsertImageEffectsTest::class,
    InsertEffectTest.ParameterizedInsertTargetEffectsTest::class
)
class InsertEffectTest {

    @RunWith(Parameterized::class)
    class ParameterizedInsertImageEffectsTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = InsertImageEffects.data
        }

        @Parameterized.Parameter(InsertImageEffects.shadowParam)
        lateinit var shadow: ImageDragData

        @Parameterized.Parameter(InsertImageEffects.effectParam)
        lateinit var effect: InsertEffect

        @Test
        fun `Creates insert effect based on image drag data`() {
            assertSealedEquals(effect, InsertEffect.create(shadow))
        }
    }

    @RunWith(Parameterized::class)
    class ParameterizedInsertTargetEffectsTest {

        companion object {
            @JvmStatic
            @Parameterized.Parameters
            fun data() = InsertTargetEffects.data
        }

        @Parameterized.Parameter(InsertTargetEffects.shadowParam)
        lateinit var shadow: ImageDragData

        @Parameterized.Parameter(InsertTargetEffects.targetParam)
        lateinit var target: DragData

        @Parameterized.Parameter(InsertTargetEffects.effectParam)
        lateinit var effect: InsertEffect

        @Test
        fun `Creates insert effect based on image data and target`() {
            assertSealedEquals(effect, InsertEffect.create(shadow, target))
        }
    }
}