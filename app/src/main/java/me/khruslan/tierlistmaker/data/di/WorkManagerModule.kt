package me.khruslan.tierlistmaker.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.work.SaveTierListArgsProvider
import me.khruslan.tierlistmaker.data.work.SaveTierListArgsProviderImpl
import me.khruslan.tierlistmaker.data.work.UpdateTierListsArgsProviderImpl
import me.khruslan.tierlistmaker.data.work.UpdateTierListsArgsProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WorkManagerModule {

    @Binds
    @Singleton
    abstract fun bindSaveTierListArgsProvider(
        saveTierListArgsProviderImpl: SaveTierListArgsProviderImpl
    ): SaveTierListArgsProvider

    @Binds
    @Singleton
    abstract fun bindUpdateTierListsArgsProvider(
        updateTierListsArgsProvider: UpdateTierListsArgsProviderImpl
    ): UpdateTierListsArgsProvider
}