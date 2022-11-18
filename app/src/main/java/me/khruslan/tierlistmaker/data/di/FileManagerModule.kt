package me.khruslan.tierlistmaker.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.data.repositories.file.FileManager
import me.khruslan.tierlistmaker.data.repositories.file.FileManagerImpl
import me.khruslan.tierlistmaker.data.repositories.file.ImageCompressor
import me.khruslan.tierlistmaker.data.repositories.file.ImageCompressorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileManagerModule {
    @Provides
    @Singleton
    fun provideImageCompressor(
        @ApplicationContext context: Context,
        dispatcherProvider: DispatcherProvider
    ): ImageCompressor = ImageCompressorImpl(context, dispatcherProvider)

    @Provides
    @Singleton
    fun provideFileManager(
        @ApplicationContext context: Context,
        imageCompressor: ImageCompressor,
        dispatcherProvider: DispatcherProvider
    ): FileManager = FileManagerImpl(context, imageCompressor, dispatcherProvider)
}