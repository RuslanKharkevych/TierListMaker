package me.khruslan.tierlistmaker.injection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.providers.database.DatabaseHelper
import me.khruslan.tierlistmaker.data.providers.database.DatabaseHelperImpl
import me.khruslan.tierlistmaker.data.providers.database.DefaultTierListCollectionProvider
import me.khruslan.tierlistmaker.data.providers.database.DefaultTierListCollectionProviderImpl
import me.khruslan.tierlistmaker.data.providers.database.PreferencesHelper
import me.khruslan.tierlistmaker.data.providers.database.PreferencesHelperImpl
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProvider
import me.khruslan.tierlistmaker.data.providers.dispatchers.DispatcherProviderImpl
import me.khruslan.tierlistmaker.presentation.utils.theme.ThemeManager
import me.khruslan.tierlistmaker.presentation.utils.theme.ThemeManagerImpl
import me.khruslan.tierlistmaker.util.analytics.AnalyticsService
import me.khruslan.tierlistmaker.util.analytics.AnalyticsServiceImpl
import me.khruslan.tierlistmaker.util.performance.PerformanceService
import me.khruslan.tierlistmaker.util.performance.PerformanceServiceImpl
import javax.inject.Singleton

/**
 * Hilt module for miscellaneous global bindings.
 *
 * A container for reusable global dependencies, which lifecycle matches application's lifecycle.
 *
 * @constructor Default empty constructor.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    /**
     * Binds dispatcher provider interface to its implementation.
     *
     * @param dispatcherProviderImpl Dispatcher provider implementation.
     * @return Dispatcher provider interface.
     */
    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(
        dispatcherProviderImpl: DispatcherProviderImpl
    ): DispatcherProvider

    /**
     * Binds analytics service interface to its implementation.
     *
     * @param analyticsServiceImpl Analytics service implementation.
     * @return Analytics service interface.
     */
    @Binds
    @Singleton
    abstract fun bindAnalyticsService(analyticsServiceImpl: AnalyticsServiceImpl): AnalyticsService

    /**
     * Binds theme manager interface to its implementation.
     *
     * @param themeManagerImpl Theme manager implementation.
     * @return Theme manager interface.
     */
    @Binds
    @Singleton
    abstract fun bindThemeManager(themeManagerImpl: ThemeManagerImpl): ThemeManager

    /**
     * Binds performance service interface to its implementation.
     *
     * @param performanceServiceImpl Performance service implementation.
     * @return Performance service interface.
     */
    @Binds
    @Singleton
    abstract fun bindPerformanceService(
        performanceServiceImpl: PerformanceServiceImpl
    ): PerformanceService

    /**
     * Binds default tier list collection provider interface to its implementation.
     *
     * @param defaultTierListCollectionProviderImpl Default tier list collection provider
     * implementation.
     * @return Default tier list collection provider interface.
     */
    @Binds
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