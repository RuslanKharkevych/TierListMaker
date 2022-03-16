package me.khruslan.tierlistmaker.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvidable
import me.khruslan.tierlistmaker.repository.file.FileManager
import me.khruslan.tierlistmaker.repository.file.ImageCompressor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FileManagerModule {
    @Provides
    @Singleton
    fun provideImageCompressor(
        @ApplicationContext context: Context,
        dispatcherProvider: DispatcherProvidable
    ) = ImageCompressor(context, dispatcherProvider)

    @Provides
    @Singleton
    fun provideFileManager(
        @ApplicationContext context: Context,
        imageCompressor: ImageCompressor,
        dispatcherProvider: DispatcherProvidable
    ) = FileManager(context, imageCompressor, dispatcherProvider)
}