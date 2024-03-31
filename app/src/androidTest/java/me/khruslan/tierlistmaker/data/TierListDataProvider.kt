package me.khruslan.tierlistmaker.data

import androidx.annotation.StringRes
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.providers.database.DefaultTierListCollectionProvider
import me.khruslan.tierlistmaker.utils.targetContext

class TierListDataProvider {

    private val collection by lazy {
        provideCollection()
    }

    fun withTierListData(@StringRes titleResId: Int, action: TierListData.() -> Unit) {
        val tierListData = TierListData {
            val tierListTitle = targetContext.getString(titleResId)
            collection.first { it.title == tierListTitle }
        }
        action(tierListData)
    }

    private fun provideCollection(): List<TierList> {
        val entryPoint = EntryPointAccessors.fromApplication(
            targetContext,
            DefaultTierListCollectionProviderEntryPoint::class.java
        )

        val defaultTierListCollectionProvider = entryPoint.defaultTierListCollectionProvider()
        check(defaultTierListCollectionProvider.collectionProvided) {
            "Default tier list collection must be provided in test first"
        }

        return defaultTierListCollectionProvider.provideCollection()
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface DefaultTierListCollectionProviderEntryPoint {
        fun defaultTierListCollectionProvider(): DefaultTierListCollectionProvider
    }
}