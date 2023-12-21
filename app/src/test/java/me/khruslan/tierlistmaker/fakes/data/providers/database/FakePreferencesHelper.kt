package me.khruslan.tierlistmaker.fakes.data.providers.database

import me.khruslan.tierlistmaker.data.providers.database.PreferencesHelper

class FakePreferencesHelper : PreferencesHelper {
    override var nightModeEnabled: Boolean = false
    override var tiersCount: Int = 5
    override var scale: Int = 4
    override var imageQuality: Int = 90

    override var defaultTierListCollectionProvided = false
        private set

    override fun markDefaultTierListCollectionAsProvided() {
        defaultTierListCollectionProvided = true
    }
}