//[app](../../../index.md)/[me.khruslan.tierlistmaker.injection](../index.md)/[ApplicationModule](index.md)

# ApplicationModule

@Module

abstract class [ApplicationModule](index.md)

Hilt module for miscellaneous global bindings.

A container for reusable global dependencies, which lifecycle matches application's lifecycle.

## Constructors

| | |
|---|---|
| [ApplicationModule](-application-module.md) | constructor()<br>Default empty constructor. |

## Functions

| Name | Summary |
|---|---|
| [bindAnalyticsService](bind-analytics-service.md) | @Binds<br>@[Singleton](https://javax-inject.github.io/javax-inject/api/javax/inject/Singleton.html)<br>abstract fun [bindAnalyticsService](bind-analytics-service.md)(analyticsServiceImpl: [AnalyticsServiceImpl](../../me.khruslan.tierlistmaker.util.analytics/-analytics-service-impl/index.md)): [AnalyticsService](../../me.khruslan.tierlistmaker.util.analytics/-analytics-service/index.md)<br>Binds analytics service interface to its implementation. |
| [bindDatabaseHelper](bind-database-helper.md) | @Binds<br>@[Singleton](https://javax-inject.github.io/javax-inject/api/javax/inject/Singleton.html)<br>abstract fun [bindDatabaseHelper](bind-database-helper.md)(databaseHelperImpl: [DatabaseHelperImpl](../../me.khruslan.tierlistmaker.data.providers.database/-database-helper-impl/index.md)): [DatabaseHelper](../../me.khruslan.tierlistmaker.data.providers.database/-database-helper/index.md)<br>Binds database helper interface to its implementation. |
| [bindDefaultTierListCollectionProvider](bind-default-tier-list-collection-provider.md) | @Binds<br>abstract fun [bindDefaultTierListCollectionProvider](bind-default-tier-list-collection-provider.md)(defaultTierListCollectionProviderImpl: [DefaultTierListCollectionProviderImpl](../../me.khruslan.tierlistmaker.data.providers.database/-default-tier-list-collection-provider-impl/index.md)): [DefaultTierListCollectionProvider](../../me.khruslan.tierlistmaker.data.providers.database/-default-tier-list-collection-provider/index.md)<br>Binds default tier list collection provider interface to its implementation. |
| [bindDispatcherProvider](bind-dispatcher-provider.md) | @Binds<br>@[Singleton](https://javax-inject.github.io/javax-inject/api/javax/inject/Singleton.html)<br>abstract fun [bindDispatcherProvider](bind-dispatcher-provider.md)(dispatcherProviderImpl: [DispatcherProviderImpl](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider-impl/index.md)): [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)<br>Binds dispatcher provider interface to its implementation. |
| [bindPerformanceService](bind-performance-service.md) | @Binds<br>@[Singleton](https://javax-inject.github.io/javax-inject/api/javax/inject/Singleton.html)<br>abstract fun [bindPerformanceService](bind-performance-service.md)(performanceServiceImpl: [PerformanceServiceImpl](../../me.khruslan.tierlistmaker.util.performance/-performance-service-impl/index.md)): [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)<br>Binds performance service interface to its implementation. |
| [bindPreferencesHelper](bind-preferences-helper.md) | @Binds<br>@[Singleton](https://javax-inject.github.io/javax-inject/api/javax/inject/Singleton.html)<br>abstract fun [bindPreferencesHelper](bind-preferences-helper.md)(preferencesHelperImpl: [PreferencesHelperImpl](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper-impl/index.md)): [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md)<br>Binds preferences helper interface to its implementation. |
| [bindThemeManager](bind-theme-manager.md) | @Binds<br>@[Singleton](https://javax-inject.github.io/javax-inject/api/javax/inject/Singleton.html)<br>abstract fun [bindThemeManager](bind-theme-manager.md)(themeManagerImpl: [ThemeManagerImpl](../../me.khruslan.tierlistmaker.presentation.utils.theme/-theme-manager-impl/index.md)): [ThemeManager](../../me.khruslan.tierlistmaker.presentation.utils.theme/-theme-manager/index.md)<br>Binds theme manager interface to its implementation. |
