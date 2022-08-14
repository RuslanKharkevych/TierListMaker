package me.khruslan.tierlistmaker.dataproviders.data

import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.effects.HighlightEffect
import me.khruslan.tierlistmaker.data.drag.effects.InsertEffect
import me.khruslan.tierlistmaker.data.drag.effects.RemoveEffect
import me.khruslan.tierlistmaker.data.drag.effects.UpdateEffect
import me.khruslan.tierlistmaker.dataproviders.DataProvider
import me.khruslan.tierlistmaker.dataproviders.DataProviderParams

object DragDataProvider : DataProvider("data/drag") {

    val dragImages: Array<ImageDragData> by lazy { readJson("drag-images") }
    val insertImageEffects: Array<InsertEffect> by lazy { readJson("insert-image-effects") }
    val insertTargetEffects: Array<InsertEffect> by lazy { readJson("insert-target-effects") }
    val insertTargets: Array<DragData> by lazy { readJson("insert-targets") }
    val highlightEffects: Array<HighlightEffect> by lazy { readJson("highlight-effects") }
    val highlightTargets: Array<DragData> by lazy { readJson("highlight-targets") }
    val removeEffects: Array<RemoveEffect> by lazy { readJson("remove-effects") }
    val removeTargets: Array<DragData> by lazy { readJson("remove-targets") }
    val shadows: Array<ImageDragData> by lazy { readJson("shadows") }
    val updateEffects: Array<UpdateEffect> by lazy { readJson("update-effects") }
    val updateTargets: Array<DragData> by lazy { readJson("update-targets") }

    object HighlightEffects : DataProviderParams() {
        const val targetParam = 0
        const val effectParam = 1

        override val data get() = mergedData(highlightTargets, highlightEffects)
    }

    object InsertImageEffects : DataProviderParams() {
        const val shadowParam = 0
        const val effectParam = 1

        override val data get() = mergedData(shadows, insertImageEffects)
    }

    object InsertTargetEffects : DataProviderParams() {
        const val shadowParam = 0
        const val targetParam = 1
        const val effectParam = 2

        override val data get() = mergedData(shadows, insertTargets, insertTargetEffects)
    }

    object RemoveEffects : DataProviderParams() {
        const val targetParam = 0
        const val effectParam = 1

        override val data get() = mergedData(removeTargets, removeEffects)
    }

    object UpdateEffects : DataProviderParams() {
        const val shadowParam = 0
        const val targetParam = 1
        const val effectParam = 2

        override val data get() = mergedData(shadows, updateTargets, updateEffects)
    }
}