package me.khruslan.tierlistmaker.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.khruslan.tierlistmaker.presentation.utils.tierlist.TierListBitmapGenerator
import me.khruslan.tierlistmaker.presentation.utils.tierlist.TierListBitmapGeneratorImpl
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListCreator
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListCreatorImpl
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListProcessor
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListProcessorImpl
import me.khruslan.tierlistmaker.data.providers.tierlist.tier.*
import me.khruslan.tierlistmaker.data.providers.drag.DragPocket
import me.khruslan.tierlistmaker.data.providers.drag.DragPocketImpl

/**
 * Hilt module that contains bindings related to tier lists.
 *
 * This class must be used only by Hilt codegen.
 *
 * @constructor Default empty constructor.
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class TierListModule {

    /**
     * Binds drag pocket interface  to its implementation.
     *
     * @param dragPocketImpl Drag pocket implementation.
     * @return Drag pocket interface.
     */
    @Binds
    @ViewModelScoped
    abstract fun bindDragPocket(dragPocketImpl: DragPocketImpl): DragPocket

    /**
     * Binds tier list processor interface to its implementation.
     *
     * @param tierListProcessorImpl Tier list processor implementation.
     * @return Tier list processor interface.
     */
    @Binds
    @ViewModelScoped
    abstract fun bindTierListProcessor(
        tierListProcessorImpl: TierListProcessorImpl
    ): TierListProcessor

    /**
     * Binds tier color provider interface to its implementation.
     *
     * @param tierColorProviderImpl Tier color provider implementation.
     * @return Tier color provider interface.
     */
    @Binds
    @ViewModelScoped
    abstract fun bindTierColorProvider(
        tierColorProviderImpl: TierColorProviderImpl
    ): TierColorProvider

    /**
     * Binds tier name provider interface to its implementation.
     *
     * @param tierNameProviderImpl Tier name provider implementation.
     * @return Tier name provider interface.
     */
    @Binds
    @ViewModelScoped
    abstract fun bindTierNameProvider(tierNameProviderImpl: TierNameProviderImpl): TierNameProvider

    /**
     * Binds tier style provider interface to its implementation.
     *
     * @param tierStyleProviderImpl Tier style provider implementation.
     * @return Tier style provider interface.
     */
    @Binds
    @ViewModelScoped
    abstract fun bindTierStyleProvider(
        tierStyleProviderImpl: TierStyleProviderImpl
    ): TierStyleProvider

    /**
     * Binds tier list bitmap generator interface to its implementation.
     *
     * @param tierListBitmapGeneratorImpl Tier list bitmap generator implementation.
     * @return Tier list bitmap generator interface.
     */
    @Binds
    @ViewModelScoped
    abstract fun bindTierListBitmapGenerator(
        tierListBitmapGeneratorImpl: TierListBitmapGeneratorImpl
    ): TierListBitmapGenerator

    /**
     * Binds tier list creator interface to its implementation.
     *
     * @param tierListCreatorImpl Tier list processor implementation.
     * @return Tier list processor interface.
     */
    @Binds
    @ViewModelScoped
    abstract fun bindTierListCreator(tierListCreatorImpl: TierListCreatorImpl): TierListCreator
}