package me.khruslan.tierlistmaker.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.ui.screens.tierlist.TierListActivity
import timber.log.Timber

/**
 * [ActivityResultContract] implementation used for getting [TierList] result from an activity.
 */
class TierListResultContract : ActivityResultContract<TierList, TierList?>() {

    /**
     * Companion object used for creating data [Intent].
     */
    companion object {
        private const val EXTRA_TIER_LIST = "me.khruslan.tierlistmaker.TIER_LIST"

        /**
         * Creates data [Intent]. This data should be passed to [Activity.setResult]
         * function in the activity that was started with [TierListResultContract].
         *
         * @param tierList tier list passed as activity result.
         * @return created data [Intent].
         */
        fun newData(tierList: TierList): Intent {
            return Intent().apply {
                putExtra(EXTRA_TIER_LIST, tierList)
            }
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