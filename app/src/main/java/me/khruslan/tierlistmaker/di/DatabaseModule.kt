package me.khruslan.tierlistmaker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.repository.db.PaperRepository
import me.khruslan.tierlistmaker.repository.db.PaperRepositoryImpl
import me.khruslan.tierlistmaker.repository.dispatchers.DispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providePaperRepository(dispatcherProvider: DispatcherProvider): PaperRepository =
        PaperRepositoryImpl(dispatcherProvider)
}