package me.khruslan.tierlistmaker.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.repositories.db.DefaultTierListCollectionProvider
import me.khruslan.tierlistmaker.data.repositories.db.DefaultTierListCollectionProviderImpl
import me.khruslan.tierlistmaker.data.repositories.db.PaperRepository
import me.khruslan.tierlistmaker.data.repositories.db.PaperRepositoryImpl
import me.khruslan.tierlistmaker.data.repositories.db.PreferencesHelper
import me.khruslan.tierlistmaker.data.repositories.db.PreferencesHelperImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    @Singleton
    abstract fun bindDefaultTierListCollectionProvider(
        defaultTierListCollectionProviderImpl: DefaultTierListCollectionProviderImpl
    ): DefaultTierListCollectionProvider

    @Binds
    @Singleton
    abstract fun bindPaperRepository(paperRepositoryImpl: PaperRepositoryImpl): PaperRepository

    @Binds
    @Singleton
    abstract fun bindPreferencesHelper(
        preferencesHelperImpl: PreferencesHelperImpl
    ): PreferencesHelper
}