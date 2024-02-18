package me.khruslan.tierlistmaker.presentation.utils.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.presentation.screens.tierlist.TierListActivity
import me.khruslan.tierlistmaker.util.getParcelableExtraCompat
import timber.log.Timber

/**
 * Activity result contract used for getting tier list result from an activity.
 *
 * Tier list is used as both input and output of this contract. No modifications are done to the
 * input. Output is nullable in case parsing fails due to unexpected error.
 */
class TierListResultContract : ActivityResultContract<TierList, TierList?>() {

    /**
     * Creator of the data [Intent].
     */
    companion object IntentCreator {

        /**
         * Name of the intent extra for tier list.
         */
        private const val EXTRA_TIER_LIST = "me.khruslan.tierlistmaker.TIER_LIST"

        /**
         * Creates data intent.
         *
         * This data should be passed to [Activity.setResult] function in the activity that was
         * started with [TierListResultContract].
         *
         * @param tierList Tier list passed as activity result.
         * @return Created data intent.
         */
        fun newData(tierList: TierList): Intent {
            return Intent().apply {
                putExtra(EXTRA_TIER_LIST, tierList)
            }
        }
    }

    /**
     * Creates an intent that can be used for [Activity.onActivityResult].
     *
     * @param context Activity context.
     * @param input Passed tier list.
     * @return Created intent.
     */
    override fun createIntent(context: Context, input: TierList) =
        TierListActivity.newIntent(context, input)

    /**
     * Converts result obtained from [Activity.onActivityResult] to the tier list.
     *
     * @param resultCode The integer result code returned by the child activity through its
     * [Activity.setResult].
     * @param intent An intent, which can return result data to the caller (various data can be attached
     * to intent "extras").
     * @return Resolved tier list or null in case of parsing error.
     */
    override fun parseResult(resultCode: Int, intent: Intent?): TierList? {
        return try {
            parseTierList(resultCode, intent)
        } catch (e: TierListResultException) {
            Timber.e(e, "Failed to parse result")
            null
        }
    }

    /**
     * Returns tier list obtained as an extra from the intent.
     *
     * @param resultCode Result code returned by the child activity.
     * @param intent An intent that is expected to contain tier list extra.
     * @return Parsed tier list.
     * @throws [TierListResultException] In case result code is not successful or intent doesn't contain such extra.
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