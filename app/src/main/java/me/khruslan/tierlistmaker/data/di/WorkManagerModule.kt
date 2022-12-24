package me.khruslan.tierlistmaker.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.work.SaveTierListArgsProvider
import me.khruslan.tierlistmaker.data.work.SaveTierListArgsProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WorkManagerModule {

    @Binds
    @Singleton
    abstract fun bindSaveTierListArgsProvider(
        saveTierListArgsProviderImpl: SaveTierListArgsProviderImpl
    ): SaveTierListArgsProvider
}