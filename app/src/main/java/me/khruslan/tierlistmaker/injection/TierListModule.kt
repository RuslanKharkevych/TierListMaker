package me.khruslan.tierlistmaker.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.khruslan.tierlistmaker.data.providers.drag.DragPocket
import me.khruslan.tierlistmaker.data.providers.drag.DragPocketImpl
import me.khruslan.tierlistmaker.data.providers.file.FileManager
import me.khruslan.tierlistmaker.data.providers.file.FileManagerImpl
import me.khruslan.tierlistmaker.data.providers.file.ImageCompressor
import me.khruslan.tierlistmaker.data.providers.file.ImageCompressorImpl
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListCreator
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListCreatorImpl
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListProcessor
import me.khruslan.tierlistmaker.data.providers.tierlist.TierListProcessorImpl
import me.khruslan.tierlistmaker.data.providers.tierlist.tier.*
import me.khruslan.tierlistmaker.presentation.utils.tierlist.TierListBitmapGenerator
import me.khruslan.tierlistmaker.presentation.utils.tierlist.TierListBitmapGeneratorImpl

/**
 * Hilt module for tier list bindings.
 *
 * A container for non-reusable tier list dependencies, which lifecycle matches the lifecycle of a
 * view model.
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
    abstract fun bindDragPocket(dragPocketImpl: DragPocketImpl): DragPocket

    /**
     * Binds tier list processor interface to its implementation.
     *
     * @param tierListProcessorImpl Tier list processor implementation.
     * @return Tier list processor interface.
     */
    @Binds
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
    abstract fun bindTierNameProvider(tierNameProviderImpl: TierNameProviderImpl): TierNameProvider

    /**
     * Binds tier style provider interface to its implementation.
     *
     * @param tierStyleProviderImpl Tier style provider implementation.
     * @return Tier style provider interface.
     */
    @Binds
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
    abstract fun bindTierListCreator(tierListCreatorImpl: TierListCreatorImpl): TierListCreator

    /**
     * Binds image compressor interface to its implementation.
     *
     * @param imageCompressorImpl Image compressor implementation.
     * @return Image compressor interface.
     */
    @Binds
    abstract fun bindImageCompressor(imageCompressorImpl: ImageCompressorImpl): ImageCompressor

    /**
     * Binds file manager interface to its implementation.
     *
     * @param fileManagerImpl File manager implementation.
     * @return File manager interface.
     */
    @Binds
    abstract fun bindFileManager(fileManagerImpl: FileManagerImpl): FileManager
}