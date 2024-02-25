//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.tierlist](index.md)

# Package-level declarations

UI utilities for tier list.

## Types

| Name | Summary |
|---|---|
| [TierListBitmapGenerator](-tier-list-bitmap-generator/index.md) | interface [TierListBitmapGenerator](-tier-list-bitmap-generator/index.md)<br>Tool that allows to generate [Bitmap](https://developer.android.com/reference/kotlin/android/graphics/Bitmap.html) from [TierList](../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md). |
| [TierListBitmapGeneratorImpl](-tier-list-bitmap-generator-impl/index.md) | class [TierListBitmapGeneratorImpl](-tier-list-bitmap-generator-impl/index.md) @Inject constructor(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val dispatcherProvider: [DispatcherProvider](../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), val preferencesHelper: [PreferencesHelper](../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md), val performanceService: [PerformanceService](../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)) : [TierListBitmapGenerator](-tier-list-bitmap-generator/index.md)<br>[TierListBitmapGenerator](-tier-list-bitmap-generator/index.md) implementation. |
| [TierListBottomBarBinder](-tier-list-bottom-bar-binder/index.md) | class [TierListBottomBarBinder](-tier-list-bottom-bar-binder/index.md)(val binding: GroupTierListBottomBarBinding, val tierList: [TierList](../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md))<br>Binder that enables / disables buttons in the bottom bar depending on the tier list state. |
