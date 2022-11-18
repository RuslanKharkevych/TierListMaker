package me.khruslan.tierlistmaker.data.work

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import timber.log.Timber

/**
 * Provider of arguments to the [SaveTierListWorker]. Used to pass [TierList] that should be saved.
 */
class SaveTierListArgsProvider {

    /**
     * Tier list that is passed. Be careful to use the getter of this property.
     * The variable automatically resets to null once it is called.
     * Therefore the getter should be used only in [SaveTierListWorker].
     */
    var tierList: TierList? = null
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