package me.khruslan.tierlistmaker.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProviderImpl
import me.khruslan.tierlistmaker.util.analytics.AnalyticsService
import me.khruslan.tierlistmaker.util.analytics.AnalyticsServiceImpl
import me.khruslan.tierlistmaker.util.performance.PerformanceService
import me.khruslan.tierlistmaker.util.performance.PerformanceServiceImpl
import me.khruslan.tierlistmaker.presentation.utils.theme.ThemeManager
import me.khruslan.tierlistmaker.presentation.utils.theme.ThemeManagerImpl
import javax.inject.Singleton

/**
 * Hilt module that contains miscellaneous global bindings.
 *
 * This class must be used only by Hilt codegen.
 *
 * @constructor Default empty constructor.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    /**
     * Binds dispatcher provider interface to its implementation.
     *
     * @param dispatcherProviderImpl Dispatcher provider implementation.
     * @return Dispatcher provider interface.
     */
    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(
        dispatcherProviderImpl: DispatcherProviderImpl
    ): DispatcherProvider

    /**
     * Binds analytics service interface to its implementation.
     *
     * @param analyticsServiceImpl Analytics service implementation.
     * @return Analytics service interface.
     */
    @Binds
    @Singleton
    abstract fun bindAnalyticsService(analyticsServiceImpl: AnalyticsServiceImpl): AnalyticsService

    /**
     * Binds theme manager interface to its implementation.
     *
     * @param themeManagerImpl Theme manager implementation.
     * @return Theme manager interface.
     */
    @Binds
    @Singleton
    abstract fun bindThemeManager(themeManagerImpl: ThemeManagerImpl): ThemeManager

    /**
     * Binds performance service interface to its implementation.
     *
     * @param performanceServiceImpl Performance service implementation.
     * @return Performance service interface.
     */
    @Binds
    @Singleton
    abstract fun bindPerformanceService(
        performanceServiceImpl: PerformanceServiceImpl
    ): PerformanceService
}