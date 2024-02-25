//[app](../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](index.md)

# Package-level declarations

Data providers for persisting data in the local storage.

## Types

| Name | Summary |
|---|---|
| [DatabaseHelper](-database-helper/index.md) | interface [DatabaseHelper](-database-helper/index.md)<br>Local storage provider. |
| [DatabaseHelperImpl](-database-helper-impl/index.md) | class [DatabaseHelperImpl](-database-helper-impl/index.md) @Inject constructor(val dispatcherProvider: [DispatcherProvider](../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), val defaultTierListCollectionProvider: [DefaultTierListCollectionProvider](-default-tier-list-collection-provider/index.md), val performanceService: [PerformanceService](../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)) : [DatabaseHelper](-database-helper/index.md)<br>[DatabaseHelper](-database-helper/index.md) implementation. |
| [DefaultTierListCollectionProvider](-default-tier-list-collection-provider/index.md) | interface [DefaultTierListCollectionProvider](-default-tier-list-collection-provider/index.md)<br>Provider of the default [TierList](../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md) collection that is written to the database when user opens the application for the first time. |
| [DefaultTierListCollectionProviderImpl](-default-tier-list-collection-provider-impl/index.md) | class [DefaultTierListCollectionProviderImpl](-default-tier-list-collection-provider-impl/index.md) @Inject constructor(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val preferencesHelper: [PreferencesHelper](-preferences-helper/index.md), val performanceService: [PerformanceService](../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)) : [DefaultTierListCollectionProvider](-default-tier-list-collection-provider/index.md)<br>[DefaultTierListCollectionProvider](-default-tier-list-collection-provider/index.md) implementation. |
| [PreferencesHelper](-preferences-helper/index.md) | interface [PreferencesHelper](-preferences-helper/index.md)<br>Helper that simplifies persisting user preferences. |
| [PreferencesHelperImpl](-preferences-helper-impl/index.md) | class [PreferencesHelperImpl](-preferences-helper-impl/index.md) @Inject constructor(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [PreferencesHelper](-preferences-helper/index.md)<br>[PreferencesHelper](-preferences-helper/index.md) implementation. |
