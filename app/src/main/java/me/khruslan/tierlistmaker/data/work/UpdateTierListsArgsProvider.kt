package me.khruslan.tierlistmaker.data.work

import me.khruslan.tierlistmaker.data.models.tierlist.TierList

/**
 * Provider of arguments to the [UpdateTierListsWorker]. Used to pass the updated list of tier
 * lists that should be saved in the database.
 */
interface UpdateTierListsArgsProvider {

    /**
     * Tier lists that are passed. Be careful to use the getter of this property. The variable
     * automatically resets to null once it is called. Therefore the getter should be used only in
     * [UpdateTierListsWorker].
     */
    var tierLists: List<TierList>?
}