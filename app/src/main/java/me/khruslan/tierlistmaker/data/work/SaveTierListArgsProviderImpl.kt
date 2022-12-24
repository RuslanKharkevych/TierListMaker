package me.khruslan.tierlistmaker.data.work

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import timber.log.Timber
import javax.inject.Inject

/**
 * Implementation of [SaveTierListArgsProvider].
 */
class SaveTierListArgsProviderImpl @Inject constructor() : SaveTierListArgsProvider {

    override var tierList: TierList? = null
        get() {
            if (field == null) logError("get tierList: field is null")
            return field.also { field = null }
        }
        set(value) {
            if (field != null) logError("set tierList: field = $field, value = $value")
            field = value
        }

    /**
     * Logs errors that occur in [SaveTierListArgsProvider].
     *
     * @param message description of an error.
     */
    private fun logError(message: String) {
        val exception = SaveTierListArgsException(message)
        Timber.e(exception, "Unexpected args state")
    }

    /**
     * Exception for errors that could happen inside [SaveTierListArgsProvider].
     *
     * @param message description of the error.
     */
    private class SaveTierListArgsException(message: String) : Exception(message)
}