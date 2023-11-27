package me.khruslan.tierlistmaker.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.providers.db.DefaultTierListCollectionProvider
import me.khruslan.tierlistmaker.data.providers.db.DefaultTierListCollectionProviderImpl
import me.khruslan.tierlistmaker.data.providers.db.DatabaseHelper
import me.khruslan.tierlistmaker.data.providers.db.DatabaseHelperImpl
import me.khruslan.tierlistmaker.data.providers.db.PreferencesHelper
import me.khruslan.tierlistmaker.data.providers.db.PreferencesHelperImpl
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
    abstract fun bindDatabaseHelper(paperRepositoryImpl: DatabaseHelperImpl): DatabaseHelper

    @Binds
    @Singleton
    abstract fun bindPreferencesHelper(
        preferencesHelperImpl: PreferencesHelperImpl
    ): PreferencesHelper
}