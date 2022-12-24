package me.khruslan.tierlistmaker.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.repositories.db.PaperRepository
import me.khruslan.tierlistmaker.data.repositories.db.PaperRepositoryImpl
import me.khruslan.tierlistmaker.data.repositories.db.PreferencesHelper
import me.khruslan.tierlistmaker.data.repositories.db.PreferencesHelperImpl
import me.khruslan.tierlistmaker.data.repositories.dispatchers.DispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePaperRepository(dispatcherProvider: DispatcherProvider): PaperRepository {
        return PaperRepositoryImpl(dispatcherProvider)
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(@ApplicationContext context: Context): PreferencesHelper {
        return PreferencesHelperImpl(context)
    }
}