package me.khruslan.tierlistmaker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.work.SaveTierListArgsProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkManagerModule {
    @Provides
    @Singleton
    fun provideSaveTierListArgsProvider() = SaveTierListArgsProvider()
}