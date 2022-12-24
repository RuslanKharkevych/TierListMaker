package me.khruslan.tierlistmaker.ui.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.ui.screens.tierlist.TierListActivity
import me.khruslan.tierlistmaker.utils.getParcelableExtraCompat
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
        return try {
            parseTierList(resultCode, intent)
        } catch (e: TierListResultException) {
            Timber.e(e, "Failed to parse result")
            null
        }
    }

    /**
     * Returns [TierList] obtained as an extra from the [Intent]. Throws [TierListResultException]
     * in case result code is not successful or intent doesn't contain such extra.
     *
     * @param resultCode result code returned by the child activity.
     * @param intent an [Intent] that can contain [TierList] extra.
     * @return parsed [TierList].
     */
    private fun parseTierList(resultCode: Int, intent: Intent?): TierList {
        when {
            resultCode != Activity.RESULT_OK ->
                throw TierListResultException("resultCode is $resultCode")
            intent == null -> throw TierListResultException("intent is null")
            else -> {
                val result: TierList? = intent.getParcelableExtraCompat(EXTRA_TIER_LIST)
                if (result != null) {
                    return result
                } else {
                    throw TierListResultException("$EXTRA_TIER_LIST is null")
                }
            }
        }
    }
}