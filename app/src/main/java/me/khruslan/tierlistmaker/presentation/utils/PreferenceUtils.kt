package me.khruslan.tierlistmaker.presentation.utils

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
        val preference = findPreference(keyResId)
        preference.setOnThrottledPreferenceClickListener {
            Timber.i("${preference.key} preference clicked")
            onClick()
        }
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
 * Thrown to indicate that the preference was not found.
 *
 * @param preferenceKey key of the preference.
 */
private class PreferenceNotFoundException(preferenceKey: String) :
    Exception("Preference with key $preferenceKey not found")