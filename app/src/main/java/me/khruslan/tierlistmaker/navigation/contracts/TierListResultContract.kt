package me.khruslan.tierlistmaker.navigation.contracts

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.ui.screens.tierlist.TierListActivity
import timber.log.Timber

class TierListResultContract : ActivityResultContract<TierList, TierList?>() {
    companion object {
        private const val EXTRA_TIER_LIST = "me.khruslan.tierlistmaker.TIER_LIST"

        fun newData(tierList: TierList) = Intent().apply {
            putExtra(EXTRA_TIER_LIST, tierList)
        }
    }

    override fun createIntent(context: Context, input: TierList) =
        TierListActivity.newIntent(context, input)

    override fun parseResult(resultCode: Int, intent: Intent?): TierList? {
        when {
            resultCode != Activity.RESULT_OK -> {
                Timber.e("parseResult: resultCode is $resultCode")
            }
            intent == null -> Timber.e("parseResult: intent is null")
            else -> {
                val result = intent.getParcelableExtra(EXTRA_TIER_LIST) as? TierList
                if (result != null) {
                    return result
                } else {
                    Timber.e("parseResult: $EXTRA_TIER_LIST is null")
                }
            }
        }

        return null
    }
}