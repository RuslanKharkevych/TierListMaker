package me.khruslan.tierlistmaker.dataproviders.viewmodels

import me.khruslan.tierlistmaker.dataproviders.DataProvider
import me.khruslan.tierlistmaker.dataproviders.DataProviderParams
import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.effects.HighlightEffect
import me.khruslan.tierlistmaker.data.drag.effects.InsertEffect
import me.khruslan.tierlistmaker.data.drag.effects.RemoveEffect
import me.khruslan.tierlistmaker.data.drag.effects.UpdateEffect
import me.khruslan.tierlistmaker.data.state.LoadingProgress
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.data.tierlist.TierListEvent
import me.khruslan.tierlistmaker.data.tierlist.TierStyle

@Suppress("ObjectPropertyName")
object TierListViewModelDataProvider : DataProvider("viewmodels/tierlist") {

    val addNewTierEvents: Array<Array<TierListEvent>> by lazy { readJson("add-new-tier-events") }
    val displayWidths: Array<Int> by lazy { readJson("display-widths") }
    val droppedImages: Array<ImageDragData> by lazy { readJson("dropped-images") }
    val filePaths: Array<List<String?>> by lazy { readJson("file-paths") }
    val highlightTargetEffects: Array<HighlightEffect> by lazy { readJson("highlight-target-effects") }
    val highlightTargetEvents: Array<TierListEvent> by lazy { readJson("highlight-target-events") }
    val insertedTargets: Array<DragData> by lazy { readJson("inserted-targets") }
    val insertImageEffects: Array<InsertEffect> by lazy { readJson("insert-image-effects") }
    val insertImageEvents: Array<TierListEvent> by lazy { readJson("insert-image-events") }
    val insertTargetEffects: Array<InsertEffect> by lazy { readJson("insert-target-effects") }
    val insertTargetEvents: Array<TierListEvent> by lazy { readJson("insert-target-events") }
    val loadingProgressStates: Array<Array<LoadingProgress?>> by lazy { readJson("loading-progress-states") }
    val oldTargets: Array<DragData> by lazy { readJson("old-targets") }
    val newTargets: Array<DragData> by lazy { readJson("new-targets") }
    val newTierStyles: Array<Array<TierStyle>> by lazy { readJson("new-tier-styles") }
    val removedImages: Array<ImageDragData> by lazy { readJson("removed-images") }
    val removeImageEffects: Array<RemoveEffect> by lazy { readJson("remove-image-effects") }
    val removeImageEvents: Array<TierListEvent> by lazy { readJson("remove-image-events") }
    val removeTargetEffects: Array<RemoveEffect> by lazy { readJson("remove-target-effects") }
    val removeTargetEvents: Array<TierListEvent> by lazy { readJson("remove-target-events") }
    val savedImagePayloads: Array<List<String>> by lazy { readJson("saved-image-payloads") }
    val shadows: Array<ImageDragData> by lazy { readJson("shadows") }
    val updatedTargets: Array<DragData> by lazy { readJson("updated-targets") }
    val updateTargetEffects: Array<UpdateEffect> by lazy { readJson("update-target-effects") }
    val updateTargetEvents: Array<TierListEvent> by lazy { readJson("update-target-events") }
    val zoomedInImageSizes: Array<Int> by lazy { readJson("zoomed-in-image-sizes") }
    val zoomedOutImageSizes: Array<Int> by lazy { readJson("zoomed-out-image-sizes") }

    private val _lists: Array<TierList> by lazy { readJson("lists") }
    val lists get() = _lists.map { it.copy() }.toTypedArray()

    object ZoomedInImageSizes : DataProviderParams() {
        const val tierListParam = 0
        const val displayWidthParam = 1
        const val imageSizeParam = 2

        override val data get() = mergedData(lists, displayWidths, zoomedInImageSizes)
    }

    object ZoomedOutImageSizes : DataProviderParams() {
        const val tierListParam = 0
        const val displayWidthParam = 1
        const val imageSizeParam = 2

        override val data get() = mergedData(lists, displayWidths, zoomedOutImageSizes)
    }

    object RemoveImageEvents : DataProviderParams() {
        const val tierListParam = 0
        const val imageDragDataParam = 1
        const val dragEffectParam = 2
        const val tierListEventParam = 3

        override val data
            get() = mergedData(
                lists,
                removedImages,
                removeImageEffects,
                removeImageEvents
            )
    }

    object AddTargetEvents : DataProviderParams() {
        const val tierListParam = 0
        const val newTargetParam = 1
        const val highlightTargetEffectParam = 2
        const val highlightTargetEventParam = 3

        override val data
            get() = mergedData(
                lists,
                newTargets,
                highlightTargetEffects,
                highlightTargetEvents
            )
    }

    object RemoveTargetEvents : DataProviderParams() {
        const val tierListParam = 0
        const val oldTargetParam = 1
        const val removeTargetEffectParam = 2
        const val removeTargetEventParam = 3

        override val data
            get() = mergedData(
                lists,
                oldTargets,
                removeTargetEffects,
                removeTargetEvents
            )
    }

    object UpdateTargetEvents : DataProviderParams() {
        const val tierListParam = 0
        const val oldTargetParam = 1
        const val newTargetParam = 2
        const val removeTargetEffectParam = 3
        const val highlightTargetEffectParam = 4
        const val removeTargetEventParam = 5
        const val highlightTargetEventParam = 6

        override val data
            get() = mergedData(
                lists,
                oldTargets,
                newTargets,
                removeTargetEffects,
                highlightTargetEffects,
                removeTargetEvents,
                highlightTargetEvents
            )
    }

    object DroppedImages : DataProviderParams() {
        const val tierListParam = 0
        const val droppedImageParam = 1

        override val data get() = mergedData(lists, droppedImages)
    }

    object DropInTargetEvents : DataProviderParams() {
        const val tierListParam = 0
        const val droppedImageParam = 1
        const val targetParam = 2
        const val updateTargetEffectParam = 3
        const val updateTargetEventParam = 4

        override val data
            get() = mergedData(
                lists,
                droppedImages,
                updatedTargets,
                updateTargetEffects,
                updateTargetEvents
            )
    }

    object DropInCachedTargetEvents : DataProviderParams() {
        const val tierListParam = 0
        const val droppedImageParam = 1
        const val cachedTargetParam = 2
        const val insertTargetEffectParam = 3
        const val insertTargetEventParam = 4

        override val data
            get() = mergedData(
                lists,
                droppedImages,
                insertedTargets,
                insertTargetEffects,
                insertTargetEvents
            )
    }

    object InsertImageEvents : DataProviderParams() {
        const val tierListParam = 0
        const val shadowParam = 1
        const val insertImageEffectParam = 2
        const val insertImageEventParam = 3

        override val data
            get() = mergedData(
                lists,
                shadows,
                insertImageEffects,
                insertImageEvents
            )
    }

    object SavedImages : DataProviderParams() {
        const val tierListParam = 0
        const val filePathsParam = 1
        const val loadingProgressStatesParam = 2
        const val savedImagePayloadsParam = 3

        override val data
            get() = mergedData(lists, filePaths, loadingProgressStates, savedImagePayloads)
    }

    object AddNewTierEvents : DataProviderParams() {
        const val tierListParam = 0
        const val newTierStylesParam = 1
        const val addNewTierEventsParam = 2

        override val data get() = mergedData(lists, newTierStyles, addNewTierEvents)
    }
}