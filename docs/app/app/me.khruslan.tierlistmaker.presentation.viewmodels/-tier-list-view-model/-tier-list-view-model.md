//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListViewModel](index.md)/[TierListViewModel](-tier-list-view-model.md)

# TierListViewModel

@[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) 

constructor(application: [Application](https://developer.android.com/reference/kotlin/android/app/Application.html), savedStateHandle: [SavedStateHandle](https://developer.android.com/reference/kotlin/androidx/lifecycle/SavedStateHandle.html), dragPocket: [DragPocket](../../me.khruslan.tierlistmaker.data.providers.drag/-drag-pocket/index.md), fileManager: [FileManager](../../me.khruslan.tierlistmaker.data.providers.file/-file-manager/index.md), tierListProcessor: [TierListProcessor](../../me.khruslan.tierlistmaker.data.providers.tierlist/-tier-list-processor/index.md), tierStyleProvider: [TierStyleProvider](../../me.khruslan.tierlistmaker.data.providers.tierlist.tier/-tier-style-provider/index.md), tierListBitmapGenerator: [TierListBitmapGenerator](../../me.khruslan.tierlistmaker.presentation.utils.tierlist/-tier-list-bitmap-generator/index.md), performanceService: [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md), analyticsService: [AnalyticsService](../../me.khruslan.tierlistmaker.util.analytics/-analytics-service/index.md))

Creates view model with all dependencies.

#### Parameters

| | |
|---|---|
| application | Provides access to global resources. |
