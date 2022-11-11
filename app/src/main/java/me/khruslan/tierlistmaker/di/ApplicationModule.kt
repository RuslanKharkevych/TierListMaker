package me.khruslan.tierlistmaker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProviderImpl
import me.khruslan.tierlistmaker.utils.log.AnalyticsService
import me.khruslan.tierlistmaker.utils.log.AnalyticsServiceImpl
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
}