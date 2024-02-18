package me.khruslan.tierlistmaker.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.providers.file.FileManager
import me.khruslan.tierlistmaker.data.providers.file.FileManagerImpl
import me.khruslan.tierlistmaker.data.providers.file.ImageCompressor
import me.khruslan.tierlistmaker.data.providers.file.ImageCompressorImpl
import javax.inject.Singleton

/**
 * Hilt module that contains bindings related to file manager.
 *
 * This class must be used only by Hilt codegen.
 *
 * @constructor Default empty constructor.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class FileManagerModule {

    /**
     * Binds image compressor interface to its implementation.
     *
     * @param imageCompressorImpl Image compressor implementation.
     * @return Image compressor interface.
     */
    @Binds
    @Singleton
    abstract fun bindImageCompressor(imageCompressorImpl: ImageCompressorImpl): ImageCompressor

    /**
     * Binds file manager interface to its implementation.
     *
     * @param fileManagerImpl File manager implementation.
     * @return File manager interface.
     */
    @Binds
    @Singleton
    abstract fun bindFileManager(fileManagerImpl: FileManagerImpl): FileManager
}