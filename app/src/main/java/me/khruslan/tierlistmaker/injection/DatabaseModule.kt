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

/**
 * Hilt module that contains database-related bindings.
 *
 * This class must be used only by Hilt codegen.
 *
 * @constructor Default empty constructor.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    /**
     * Binds default tier list collection provider interface to its implementation.
     *
     * @param defaultTierListCollectionProviderImpl Default tier list collection provider
     * implementation.
     * @return Default tier list collection provider interface.
     */
    @Binds
    @Singleton
    abstract fun bindDefaultTierListCollectionProvider(
        defaultTierListCollectionProviderImpl: DefaultTierListCollectionProviderImpl
    ): DefaultTierListCollectionProvider

    /**
     * Binds database helper interface to its implementation.
     *
     * @param databaseHelperImpl Database helper implementation.
     * @return Database helper interface.
     */
    @Binds
    @Singleton
    abstract fun bindDatabaseHelper(databaseHelperImpl: DatabaseHelperImpl): DatabaseHelper

    /**
     * Binds preferences helper interface to its implementation.
     *
     * @param preferencesHelperImpl Preferences helper implementation.
     * @return Preferences helper interface.
     */
    @Binds
    @Singleton
    abstract fun bindPreferencesHelper(
        preferencesHelperImpl: PreferencesHelperImpl
    ): PreferencesHelper
}