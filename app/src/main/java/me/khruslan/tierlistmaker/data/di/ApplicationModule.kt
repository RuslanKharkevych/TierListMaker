package me.khruslan.tierlistmaker.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.repositories.db.PreferencesHelper
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProviderImpl
import me.khruslan.tierlistmaker.utils.log.AnalyticsService
import me.khruslan.tierlistmaker.utils.log.AnalyticsServiceImpl
import me.khruslan.tierlistmaker.utils.theme.ThemeManager
import me.khruslan.tierlistmaker.utils.theme.ThemeManagerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = DispatcherProviderImpl()

    @Provides
    @Singleton
    fun provideAnalyticsService(): AnalyticsService = AnalyticsServiceImpl()

    @Provides
    @Singleton
    fun provideThemeManager(
        preferencesHelper: PreferencesHelper,
        dispatcherProvider: DispatcherProvider
    ): ThemeManager = ThemeManagerImpl(preferencesHelper, dispatcherProvider)
}