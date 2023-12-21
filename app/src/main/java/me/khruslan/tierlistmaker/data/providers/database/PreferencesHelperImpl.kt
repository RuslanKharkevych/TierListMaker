package me.khruslan.tierlistmaker.data.providers.database

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import me.khruslan.tierlistmaker.R
import timber.log.Timber
import javax.inject.Inject

/**
 * Implementation of [PreferencesHelper].
 *
 * @property context application context.
 */
class PreferencesHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferencesHelper {

    /**
     * Companion object of [PreferencesHelperImpl] that stores preferences file name and keys.
     */
    private companion object {
        private const val KEY_NIGHT_MODE_ENABLED = "dark_mode_enabled"
        private const val KEY_DEFAULT_TIER_LIST_COLLECTION_PROVIDED =
            "default_tier_list_collection_provided"
    }

    /**
     * Singleton instance of [SharedPreferences].
     */
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    /**
     * Shared preference change listener that logs all updates. It must be a class field (see
     * the documentation of [SharedPreferences.registerOnSharedPreferenceChangeListener]).
     */
    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { preferences, key ->
        Timber.i("$key preference has changed. Updated preferences: ${preferences.all}")
    }

    init {
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override var nightModeEnabled
        get() = preferences.getBoolean(
            KEY_NIGHT_MODE_ENABLED,
            context.resources.getBoolean(R.bool.night_mode_enabled)
        )
        set(value) = preferences.edit {
            putBoolean(KEY_NIGHT_MODE_ENABLED, value)
        }

    override val tiersCount
        get() = preferences.getInt(
            context.getString(R.string.pref_tiers_count_key),
            context.resources.getInteger(R.integer.pref_default_tiers_count)
        )

    override val scale
        get() = preferences.getInt(
            context.getString(R.string.pref_scale_key),
            context.resources.getInteger(R.integer.pref_default_scale)
        )

    override val imageQuality
        get() = preferences.getInt(
            context.getString(R.string.pref_image_quality_key),
            context.resources.getInteger(R.integer.pref_default_image_quality)
        )

    override val defaultTierListCollectionProvided
        get() = preferences.getBoolean(
            KEY_DEFAULT_TIER_LIST_COLLECTION_PROVIDED,
            false
        )

    override fun markDefaultTierListCollectionAsProvided() {
        preferences.edit {
            putBoolean(KEY_DEFAULT_TIER_LIST_COLLECTION_PROVIDED, true)
        }
    }
}