package me.khruslan.tierlistmaker.fakes.data.work

import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.work.SaveTierListArgsProvider

class FakeSaveTierListArgsProvider : SaveTierListArgsProvider {
    override var tierList: TierList? = null
}