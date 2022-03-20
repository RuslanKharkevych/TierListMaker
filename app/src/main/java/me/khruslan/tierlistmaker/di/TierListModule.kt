package me.khruslan.tierlistmaker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable
import me.khruslan.tierlistmaker.repository.tierlist.TierListProcessor
import me.khruslan.tierlistmaker.repository.tierlist.tier.TierColorProvider
import me.khruslan.tierlistmaker.repository.tierlist.tier.TierNameProvider
import me.khruslan.tierlistmaker.repository.tierlist.tier.TierStyleProvider
import me.khruslan.tierlistmaker.utils.drag.DragPocket

@Module
@InstallIn(ViewModelComponent::class)
object TierListModule {
    @Provides
    @ViewModelScoped
    fun provideDragPocket() = DragPocket()

    @Provides
    @ViewModelScoped
    fun provideTierListProcessor() = TierListProcessor()

    @Provides
    @ViewModelScoped
    fun provideTierColorProvider() = TierColorProvider()

    @Provides
    @ViewModelScoped
    fun provideTierNameProvider() = TierNameProvider()

    @Provides
    @ViewModelScoped
    fun provideTierStyleProvider(
        tierNameProvider: TierNameProvider,
        tierColorProvider: TierColorProvider,
        dispatcherProvider: DispatcherProvidable
    ) = TierStyleProvider(tierColorProvider, tierNameProvider, dispatcherProvider)
}