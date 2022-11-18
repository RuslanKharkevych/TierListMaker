package me.khruslan.tierlistmaker.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.repositories.db.PaperRepository
import me.khruslan.tierlistmaker.data.repositories.db.PaperRepositoryImpl
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providePaperRepository(dispatcherProvider: DispatcherProvider): PaperRepository =
        PaperRepositoryImpl(dispatcherProvider)
}