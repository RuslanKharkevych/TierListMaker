package me.khruslan.tierlistmaker.work

import me.khruslan.tierlistmaker.data.tierlist.TierList
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
            if (field == null) Timber.e("get tierList: field is null")
            return field.also { field = null }
        }
        set(value) {
            if (field != null) Timber.e("set tierList: field = $field, value = $value")
            field = value
        }
}