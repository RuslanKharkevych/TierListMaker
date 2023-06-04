package me.khruslan.tierlistmaker.utils.view

import androidx.annotation.StringRes
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import timber.log.Timber

/**
 * Sets click listener for the preference found by the key resolved from [keyResId]. If the
 * preference is not found, logs error.
 *
 * @param keyResId string resource identifier of the preference key.
 * @param onClick the callback to be invoked when the preference is clicked.
 */
fun PreferenceFragmentCompat.setOnPreferenceClickListener(
    @StringRes keyResId: Int,
    onClick: () -> Unit
) {
    try {
        findPreference(keyResId).setOnClickListener(onClick)
    } catch (e: PreferenceNotFoundException) {
        Timber.e(e, "Unable to set preference click listener")
    }
}

/**
 * Sets summary for the preference found by the key resolved from [keyResId]. If the preference is
 * not found, logs error.
 *
 * @param keyResId string resource identifier of the preference key.
 * @param summary preference summary.
 */
fun PreferenceFragmentCompat.setPreferenceSummary(@StringRes keyResId: Int, summary: String) {
    try {
        findPreference(keyResId).summary = summary
    } catch (e: PreferenceNotFoundException) {
        Timber.e(e, "Unable to set preference summary")
    }
}

/**
 * Finds preference by the key resolved from [keyResId].
 *
 * @param keyResId string resource identifier of the preference key.
 * @return found preference.
 * @throws [PreferenceNotFoundException] when preference was not found.
 */
private fun PreferenceFragmentCompat.findPreference(@StringRes keyResId: Int): Preference {
    val key = getString(keyResId)
    return findPreference(key) ?: throw PreferenceNotFoundException(key)
}

/**
 * Sets click listener for this preference.
 *
 * @receiver clickable preference.
 * @param onClick the callback to be invoked when this preference is clicked.
 */
private fun Preference.setOnClickListener(onClick: () -> Unit) {
    setOnPreferenceClickListener {
        onClick()
        true
    }
}

/**
 * Thrown to indicate that the preference was not found.
 *
 * @param preferenceKey key of the preference.
 */
private class PreferenceNotFoundException(preferenceKey: String) :
    Exception("Preference with key $preferenceKey not found")