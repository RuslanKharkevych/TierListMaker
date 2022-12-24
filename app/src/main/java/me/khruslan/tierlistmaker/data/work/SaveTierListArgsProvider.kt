package me.khruslan.tierlistmaker.data.work

import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Provider of arguments to the [SaveTierListWorker]. Used to pass [TierList] that should be saved.
 */
interface SaveTierListArgsProvider {

    /**
     * Tier list that is passed. Be careful to use the getter of this property. The variable
     * automatically resets to null once it is called. Therefore the getter should be used only in
     * [SaveTierListWorker].
     */
    var tierList: TierList?
}