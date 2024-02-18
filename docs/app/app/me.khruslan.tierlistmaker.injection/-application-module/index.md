//[app](../../../index.md)/[me.khruslan.tierlistmaker.injection](../index.md)/[ApplicationModule](index.md)

# ApplicationModule

@Module

abstract class [ApplicationModule](index.md)

Hilt module that contains miscellaneous global bindings.

This class must be used only by Hilt codegen.

## Constructors

| | |
|---|---|
| [ApplicationModule](-application-module.md) | constructor()<br>Default empty constructor. |

## Functions

| Name | Summary |
|---|---|
| [bindAnalyticsService](bind-analytics-service.md) | @Binds<br>@Singleton<br>abstract fun [bindAnalyticsService](bind-analytics-service.md)(analyticsServiceImpl: [AnalyticsServiceImpl](../../me.khruslan.tierlistmaker.util.analytics/-analytics-service-impl/index.md)): [AnalyticsService](../../me.khruslan.tierlistmaker.util.analytics/-analytics-service/index.md)<br>Binds analytics service interface to its implementation. |
| [bindDispatcherProvider](bind-dispatcher-provider.md) | @Binds<br>@Singleton<br>abstract fun [bindDispatcherProvider](bind-dispatcher-provider.md)(dispatcherProviderImpl: [DispatcherProviderImpl](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider-impl/index.md)): [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)<br>Binds dispatcher provider interface to its implementation. |
| [bindPerformanceService](bind-performance-service.md) | @Binds<br>@Singleton<br>abstract fun [bindPerformanceService](bind-performance-service.md)(performanceServiceImpl: [PerformanceServiceImpl](../../me.khruslan.tierlistmaker.util.performance/-performance-service-impl/index.md)): [PerformanceService](../../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)<br>Binds performance service interface to its implementation. |
| [bindThemeManager](bind-theme-manager.md) | @Binds<br>@Singleton<br>abstract fun [bindThemeManager](bind-theme-manager.md)(themeManagerImpl: [ThemeManagerImpl](../../me.khruslan.tierlistmaker.presentation.utils.theme/-theme-manager-impl/index.md)): [ThemeManager](../../me.khruslan.tierlistmaker.presentation.utils.theme/-theme-manager/index.md)<br>Binds theme manager interface to its implementation. |
