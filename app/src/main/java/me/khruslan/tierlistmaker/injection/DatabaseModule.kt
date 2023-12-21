package me.khruslan.tierlistmaker.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.providers.database.DefaultTierListCollectionProvider
import me.khruslan.tierlistmaker.data.providers.database.DefaultTierListCollectionProviderImpl
import me.khruslan.tierlistmaker.data.providers.database.DatabaseHelper
import me.khruslan.tierlistmaker.data.providers.database.DatabaseHelperImpl
import me.khruslan.tierlistmaker.data.providers.database.PreferencesHelper
import me.khruslan.tierlistmaker.data.providers.database.PreferencesHelperImpl
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