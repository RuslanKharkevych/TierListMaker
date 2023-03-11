package me.khruslan.tierlistmaker.data.work

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import timber.log.Timber
import javax.inject.Inject

/**
 * Implementation of [UpdateTierListsArgsProvider].
 */
class UpdateTierListsArgsProviderImpl @Inject constructor() : UpdateTierListsArgsProvider {

    override var tierLists: List<TierList>? = null
        get() {
            if (field == null) logError("get tierLists: field is null")
            return field.also { field = null }
        }
        set(value) {
            if (field != null) logError("set tierLists: field = $field, value = $value")
            field = value
        }

    /**
     * Logs errors that occur in [UpdateTierListsArgsProvider].
     *
     * @param message description of an error.
     */
    private fun logError(message: String) {
        val exception = UpdateTierListsArgsException(message)
        Timber.e(exception, "Unexpected args state")
    }

    /**
     * Exception for errors that could happen inside [UpdateTierListsArgsProvider].
     *
     * @param message description of the error.
     */
    private class UpdateTierListsArgsException(message: String) : Exception(message)
}