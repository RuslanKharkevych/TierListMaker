package me.khruslan.tierlistmaker.work

import me.khruslan.tierlistmaker.data.tierlist.TierList
import timber.log.Timber

class SaveTierListArgsProvider {
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