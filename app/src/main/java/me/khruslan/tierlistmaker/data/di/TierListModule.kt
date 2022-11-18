package me.khruslan.tierlistmaker.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.data.repositories.tierlist.TierListProcessor
import me.khruslan.tierlistmaker.data.repositories.tierlist.TierListProcessorImpl
import me.khruslan.tierlistmaker.data.repositories.tierlist.tier.*
import me.khruslan.tierlistmaker.utils.drag.DragPocket

@Module
@InstallIn(ViewModelComponent::class)
object TierListModule {
    @Provides
    @ViewModelScoped
    fun provideDragPocket() = DragPocket()

    @Provides
    @ViewModelScoped
    fun provideTierListProcessor(): TierListProcessor = TierListProcessorImpl()

    @Provides
    @ViewModelScoped
    fun provideTierColorProvider(): TierColorProvider = TierColorProviderImpl()

    @Provides
    @ViewModelScoped
    fun provideTierNameProvider(): TierNameProvider = TierNameProviderImpl()

    @Provides
    @ViewModelScoped
    fun provideTierStyleProvider(
        tierNameProvider: TierNameProvider,
        tierColorProvider: TierColorProvider,
        dispatcherProvider: DispatcherProvider
    ): TierStyleProvider =
        TierStyleProviderImpl(tierColorProvider, tierNameProvider, dispatcherProvider)
}