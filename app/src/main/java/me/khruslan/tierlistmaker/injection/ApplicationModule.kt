package me.khruslan.tierlistmaker.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProviderImpl
import me.khruslan.tierlistmaker.util.analytics.AnalyticsService
import me.khruslan.tierlistmaker.util.analytics.AnalyticsServiceImpl
import me.khruslan.tierlistmaker.util.performace.PerformanceService
import me.khruslan.tierlistmaker.util.performace.PerformanceServiceImpl
import me.khruslan.tierlistmaker.presentation.utils.theme.ThemeManager
import me.khruslan.tierlistmaker.presentation.utils.theme.ThemeManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(
        dispatcherProviderImpl: DispatcherProviderImpl
    ): DispatcherProvider

    @Binds
    @Singleton
    abstract fun bindAnalyticsService(analyticsServiceImpl: AnalyticsServiceImpl): AnalyticsService

    @Binds
    @Singleton
    abstract fun bindThemeManager(themeManagerImpl: ThemeManagerImpl): ThemeManager

    @Binds
    @Singleton
    abstract fun bindPerformanceService(
        performanceServiceImpl: PerformanceServiceImpl
    ): PerformanceService
}