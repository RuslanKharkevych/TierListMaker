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

@Module
@InstallIn(SingletonComponent::class)
abstract class FileManagerModule {

    @Binds
    @Singleton
    abstract fun bindImageCompressor(imageCompressorImpl: ImageCompressorImpl): ImageCompressor

    @Binds
    @Singleton
    abstract fun bindFileManager(fileManagerImpl: FileManagerImpl): FileManager
}