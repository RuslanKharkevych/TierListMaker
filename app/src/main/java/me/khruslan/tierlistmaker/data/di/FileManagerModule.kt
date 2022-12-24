package me.khruslan.tierlistmaker.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.repositories.file.FileManager
import me.khruslan.tierlistmaker.data.repositories.file.FileManagerImpl
import me.khruslan.tierlistmaker.data.repositories.file.ImageCompressor
import me.khruslan.tierlistmaker.data.repositories.file.ImageCompressorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FileManagerModule {

    @Binds
    @Singleton
    abstract fun bindImageCompressor(imageCompressorImpl: ImageCompressorImpl): ImageCompressor

    @Binds
    @Singleton
    abstract fun provideFileManager(fileManagerImpl: FileManagerImpl): FileManager
}