package me.khruslan.tierlistmaker.data.providers.database

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.VisibleForTesting
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.util.analytics.AnalyticsService
import me.khruslan.tierlistmaker.util.analytics.PreferenceChanged
import timber.log.Timber
import javax.inject.Inject

/**
 * [PreferencesHelper] implementation.
 *
 * Implemented with [SharedPreferences]. To avoid memory leaks, this class should be injected as a
 * singleton.
 *
 * @property context Application context.
 * @property analyticsService Logs analytic events.
 * @constructor Creates a new preferences helper instance.
 */
class PreferencesHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val analyticsService: AnalyticsService
) : PreferencesHelper {

    /**
     * Preference keys.
     *
     * Not that some keys are not listed here and instead are declared as string resources. That is
     * needed if they are also accessed from XML.
     */
    @VisibleForTesting
    internal companion object Keys {

        /**
         * Key for [nightModeEnabled].
         */
        @VisibleForTesting
        internal const val KEY_NIGHT_MODE_ENABLED = "night_mode_enabled"

        /**
         * Key for [defaultTierListCollectionProvided].
         */
        private const val KEY_DEFAULT_TIER_LIST_COLLECTION_PROVIDED =
            "default_tier_list_collection_provided"
    }

    /**
     * Singleton instance of [SharedPreferences].
     *
     * Note that the default shared preferences file is used.
     */
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    /**
     * Shared preference change listener that logs all updates.
     *
     * Also logs [PreferenceChanged] analytic event. It must be a class field (see the documentation
     * of [SharedPreferences.registerOnSharedPreferenceChangeListener]).
     */
    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { preferences, key ->
        Timber.i("$key preference has changed. Updated preferences: ${preferences.all}")
        analyticsService.logEvent(PreferenceChanged(key, preferences.all[key]))
    }

    init {
        registerOnSharedPreferenceChangeListener()
    }

    /**
     * Whether dark theme is preferred by user. If not - user opted for light theme.
     *
     * By default returns the system preference.
     */
    override var nightModeEnabled
        get() = preferences.getBoolean(
            KEY_NIGHT_MODE_ENABLED,
            context.resources.getBoolean(R.bool.night_mode_enabled)
        )
        set(value) = preferences.edit {
            putBoolean(KEY_NIGHT_MODE_ENABLED, value)
        }

    /**
     * How many tiers will be added when a new tier list is created.
     *
     * By default returns the fallback value from resource.
     */
    override val tiersCount
        get() = preferences.getInt(
            context.getString(R.string.pref_tiers_count_key),
            context.resources.getInteger(R.integer.pref_default_tiers_count)
        )

    /**
     * How many images will fit in a row inside a tier in a new tier list.
     *
     * By default returns the fallback value from resources.
     */
    override val scale
        get() = preferences.getInt(
            context.getString(R.string.pref_scale_key),
            context.resources.getInteger(R.integer.pref_default_scale)
        )

    /**
     * The quality in percents of the new tier list images.
     *
     * By default returns the fallback value from resources.
     */
    override val imageQuality
        get() = preferences.getInt(
            context.getString(R.string.pref_image_quality_key),
            context.resources.getInteger(R.integer.pref_default_image_quality)
        )

    /**
     * Whether the default tier list collection has already been provided.
     *
     * By default returns false.
     *
     * @see markDefaultTierListCollectionAsProvided
     */
    override val defaultTierListCollectionProvided
        get() = preferences.getBoolean(
            KEY_DEFAULT_TIER_LIST_COLLECTION_PROVIDED,
            false
        )

    /**
     * Marks default tier list collection as provided.
     *
     * @see defaultTierListCollectionProvided
     */
    override fun markDefaultTierListCollectionAsProvided() {
        preferences.edit {
            putBoolean(KEY_DEFAULT_TIER_LIST_COLLECTION_PROVIDED, true)
        }
    }

    /**
     * Registers [listener] for logging.
     *
     * This function should be called once [preferences] instance is initialized. Unregistering is
     * not required because the instance is a singleton.
     */
    private fun registerOnSharedPreferenceChangeListener() {
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }
}