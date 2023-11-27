package me.khruslan.tierlistmaker.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListBitmapGenerator
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListBitmapGeneratorImpl
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListCreator
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListCreatorImpl
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListProcessor
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListProcessorImpl
import me.khruslan.tierlistmaker.data.providers.tierlist.tier.*
import me.khruslan.tierlistmaker.utils.drag.DragPocket
import me.khruslan.tierlistmaker.utils.drag.DragPocketImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class TierListModule {

    @Binds
    @ViewModelScoped
    abstract fun bindDragPocket(dragPocketImpl: DragPocketImpl): DragPocket

    @Binds
    @ViewModelScoped
    abstract fun bindTierListProcessor(
        tierListProcessorImpl: TierListProcessorImpl
    ): TierListProcessor

    @Binds
    @ViewModelScoped
    abstract fun bindTierColorProvider(
        tierColorProviderImpl: TierColorProviderImpl
    ): TierColorProvider

    @Binds
    @ViewModelScoped
    abstract fun bindTierNameProvider(tierNameProviderImpl: TierNameProviderImpl): TierNameProvider

    @Binds
    @ViewModelScoped
    abstract fun bindTierStyleProvider(
        tierStyleProviderImpl: TierStyleProviderImpl
    ): TierStyleProvider

    @Binds
    @ViewModelScoped
    abstract fun bindTierListBitmapGenerator(
        tierListBitmapGeneratorImpl: TierListBitmapGeneratorImpl
    ): TierListBitmapGenerator

    @Binds
    @ViewModelScoped
    abstract fun bindTierListCreator(tierListCreatorImpl: TierListCreatorImpl): TierListCreator
}