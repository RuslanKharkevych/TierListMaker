package me.khruslan.tierlistmaker.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProviderImpl
import me.khruslan.tierlistmaker.utils.log.analytics.AnalyticsService
import me.khruslan.tierlistmaker.utils.log.analytics.AnalyticsServiceImpl
import me.khruslan.tierlistmaker.utils.theme.ThemeManager
import me.khruslan.tierlistmaker.utils.theme.ThemeManagerImpl
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
}