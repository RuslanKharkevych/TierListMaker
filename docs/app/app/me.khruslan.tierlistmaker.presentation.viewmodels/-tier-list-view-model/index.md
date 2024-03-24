//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)

# TierListViewModel

class [TierListViewModel](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor(application: [Application](https://developer.android.com/reference/kotlin/android/app/Application.html), val savedStateHandle: [SavedStateHandle](https://developer.android.com/reference/kotlin/androidx/lifecycle/SavedStateHandle.html), val dragPocket: [DragPocket](../../me.khruslan.tierlistmaker.data.providers.drag/-drag-pocket/index.md), val fileManager: [FileManager](../../me.khruslan.tierlistmaker.data.providers.file/-file-manager/index.md), val tierListProcessor: [TierListProcessor](../../me.khruslan.tierlistmaker.data.providers.tierlist/-tier-list-processor/index.md), val tierStyleProvider: [TierStyleProvider](../../me.khruslan.tierlistmaker.data.providers.tierlist.tier/-tier-style-provider/index.md), val tierListBitmapGenerator: [TierListBitmapGenerator](../../me.khruslan.tierlistmaker.presentation.utils.tierlist/-tier-list-bitmap-generator/index.md), val performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md), val analyticsService: [AnalyticsService](../../me.khruslan.tierlistmaker.util.analytics/-analytics-service/index.md)) : [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)

View model for [TierListFragment](../../me.khruslan.tierlistmaker.presentation.screens.tierlist/-tier-list-fragment/index.md).

The view model operates on a tier list object received from the saved state handle. Handles drag effects and sends tier list events that allow observers to reflect UI changes.

#### Parameters

| | |
|---|---|
| application | Provides access to global resources. |

## Constructors

| | |
|---|---|
| [TierListViewModel](-tier-list-view-model.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor(application: [Application](https://developer.android.com/reference/kotlin/android/app/Application.html), savedStateHandle: [SavedStateHandle](https://developer.android.com/reference/kotlin/androidx/lifecycle/SavedStateHandle.html), dragPocket: [DragPocket](../../me.khruslan.tierlistmaker.data.providers.drag/-drag-pocket/index.md), fileManager: [FileManager](../../me.khruslan.tierlistmaker.data.providers.file/-file-manager/index.md), tierListProcessor: [TierListProcessor](../../me.khruslan.tierlistmaker.data.providers.tierlist/-tier-list-processor/index.md), tierStyleProvider: [TierStyleProvider](../../me.khruslan.tierlistmaker.data.providers.tierlist.tier/-tier-style-provider/index.md), tierListBitmapGenerator: [TierListBitmapGenerator](../../me.khruslan.tierlistmaker.presentation.utils.tierlist/-tier-list-bitmap-generator/index.md), performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md), analyticsService: [AnalyticsService](../../me.khruslan.tierlistmaker.util.analytics/-analytics-service/index.md))<br>Creates view model with all dependencies. |

## Types

| Name | Summary |
|---|---|
| [NavArgKeys](-nav-arg-keys/index.md) | private object [NavArgKeys](-nav-arg-keys/index.md)<br>Navigation argument keys. |

## Properties

| Name | Summary |
|---|---|
| [_hintEvent](_hint-event.md) | private val [_hintEvent](_hint-event.md): LiveEvent&lt;[TierListHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.tierlist/-tier-list-hint-step/index.md)&gt;<br>A mutable reference to [hintEvent](hint-event.md). |
| [_loadingProgressLiveData](_loading-progress-live-data.md) | private val [_loadingProgressLiveData](_loading-progress-live-data.md): [MutableLiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)&lt;[LoadingProgress](../../me.khruslan.tierlistmaker.presentation.models/-loading-progress/index.md)?&gt;<br>A mutable reference to [loadingProgressLiveData](loading-progress-live-data.md). |
| [_tierListEvent](_tier-list-event.md) | private val [_tierListEvent](_tier-list-event.md): LiveEvent&lt;[TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)&gt;<br>A mutable reference to [tierListEvent](tier-list-event.md). |
| [analyticsService](analytics-service.md) | private val [analyticsService](analytics-service.md): [AnalyticsService](../../me.khruslan.tierlistmaker.util.analytics/-analytics-service/index.md)<br>Logs analytic events. |
| [displayWidth](display-width.md) | private val [displayWidth](display-width.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Width of the device screen in pixels. |
| [dragPocket](drag-pocket.md) | private val [dragPocket](drag-pocket.md): [DragPocket](../../me.khruslan.tierlistmaker.data.providers.drag/-drag-pocket/index.md)<br>Keeps drag data in memory. |
| [fileManager](file-manager.md) | private val [fileManager](file-manager.md): [FileManager](../../me.khruslan.tierlistmaker.data.providers.file/-file-manager/index.md)<br>Saves image files. |
| [hintEvent](hint-event.md) | val [hintEvent](hint-event.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[TierListHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.tierlist/-tier-list-hint-step/index.md)&gt;<br>Live data that notifies observers about the tier list hint step to show. |
| [imageSize](image-size.md) | val [imageSize](image-size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Size of the tier list image. |
| [loadingProgressLiveData](loading-progress-live-data.md) | val [loadingProgressLiveData](loading-progress-live-data.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[LoadingProgress](../../me.khruslan.tierlistmaker.presentation.models/-loading-progress/index.md)?&gt;<br>Live data that notifies observers about the progress of loading image files or creating an image file of the tier list. |
| [performanceService](performance-service.md) | private val [performanceService](performance-service.md): [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)<br>Tracks performance traces. |
| [savedStateHandle](saved-state-handle.md) | private val [savedStateHandle](saved-state-handle.md): [SavedStateHandle](https://developer.android.com/reference/kotlin/androidx/lifecycle/SavedStateHandle.html)<br>Provides navigation arguments. |
| [tierList](tier-list.md) | val [tierList](tier-list.md): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)<br>Initial tier list instance obtained from navigation arguments. |
| [tierListBitmapGenerator](tier-list-bitmap-generator.md) | private val [tierListBitmapGenerator](tier-list-bitmap-generator.md): [TierListBitmapGenerator](../../me.khruslan.tierlistmaker.presentation.utils.tierlist/-tier-list-bitmap-generator/index.md) |
| [tierListEvent](tier-list-event.md) | val [tierListEvent](tier-list-event.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)&gt;<br>Live data that notifies observers about the tier list events. |
| [tierListProcessor](tier-list-processor.md) | private val [tierListProcessor](tier-list-processor.md): [TierListProcessor](../../me.khruslan.tierlistmaker.data.providers.tierlist/-tier-list-processor/index.md)<br>Processes drag effects. |
| [tierStyleProvider](tier-style-provider.md) | private val [tierStyleProvider](tier-style-provider.md): [TierStyleProvider](../../me.khruslan.tierlistmaker.data.providers.tierlist.tier/-tier-style-provider/index.md)<br>Provides tier styles. |
| [updateTierListStylesJob](update-tier-list-styles-job.md) | private var [updateTierListStylesJob](update-tier-list-styles-job.md): [Job](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html)?<br>Updates styles of all tiers in the tier list. |

## Functions

| Name | Summary |
|---|---|
| [addNewTier](add-new-tier.md) | fun [addNewTier](add-new-tier.md)()<br>Inserts a new empty tier at the end. |
| [createImage](create-image.md) | private fun [createImage](create-image.md)(file: [File](https://developer.android.com/reference/kotlin/java/io/File.html)?): [Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)<br>Creates image from file. |
| [dropImage](drop-image.md) | fun [dropImage](drop-image.md)(dragData: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md))<br>Drops the image into a target. |
| [handleHint](handle-hint.md) | private fun [handleHint](handle-hint.md)()<br>Produces [hintEvent](hint-event.md) if [KEY_HINT_STEP_NAME](-nav-arg-keys/-k-e-y_-h-i-n-t_-s-t-e-p_-n-a-m-e.md) is found in navigation arguments. |
| [initTierList](init-tier-list.md) | private fun [initTierList](init-tier-list.md)()<br>Runs initial configurations of the tier list. |
| [insertImagesToBacklog](insert-images-to-backlog.md) | fun [insertImagesToBacklog](insert-images-to-backlog.md)(images: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;)<br>Inserts images at the start of the backlog. |
| [launchUpdateTierStylesJob](launch-update-tier-styles-job.md) | private fun [launchUpdateTierStylesJob](launch-update-tier-styles-job.md)(): [Job](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-job/index.html)<br>Creates and starts a job that updates styles of all tiers. |
| [onCleared](on-cleared.md) | protected open override fun [onCleared](on-cleared.md)()<br>Logs the onCleared lifecycle event. |
| [processDragEffect](process-drag-effect.md) | private fun [processDragEffect](process-drag-effect.md)(effect: [DragEffect](../../me.khruslan.tierlistmaker.data.models.drag.effects/-drag-effect/index.md))<br>Converts drag effect to the tier list event. |
| [restoreReleasedImage](restore-released-image.md) | fun [restoreReleasedImage](restore-released-image.md)()<br>Restores released image from the &quot;pocket&quot;. |
| [saveImages](save-images.md) | fun [saveImages](save-images.md)(imageUris: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)&gt;)<br>Saves images to the local storage. |
| [saveTierListToFile](save-tier-list-to-file.md) | private suspend fun [saveTierListToFile](save-tier-list-to-file.md)(): [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html)?<br>Generates bitmap from tier list and saves it to a file. |
| [shareTierList](share-tier-list.md) | fun [shareTierList](share-tier-list.md)()<br>Handles share tier list action. |
| [startDrag](start-drag.md) | fun [startDrag](start-drag.md)(dragData: [ImageDragData](../../me.khruslan.tierlistmaker.data.models.drag/-image-drag-data/index.md))<br>Starts drag. |
| [updateDragTarget](update-drag-target.md) | fun [updateDragTarget](update-drag-target.md)(newTarget: [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md)?)<br>Updates the drag target. |
| [updateTierListTitle](update-tier-list-title.md) | fun [updateTierListTitle](update-tier-list-title.md)(updatedTitle: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Updates tier list title. |
| [updateTierStyles](update-tier-styles.md) | fun [updateTierStyles](update-tier-styles.md)()<br>Update styles of all tiers. |
| [viewTierList](view-tier-list.md) | fun [viewTierList](view-tier-list.md)()<br>Handles view tier list action. |
| [zoomIn](zoom-in.md) | fun [zoomIn](zoom-in.md)()<br>Decrements [TierList.zoomValue](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/zoom-value.md). |
| [zoomOut](zoom-out.md) | fun [zoomOut](zoom-out.md)()<br>Increments [TierList.zoomValue](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/zoom-value.md). |
