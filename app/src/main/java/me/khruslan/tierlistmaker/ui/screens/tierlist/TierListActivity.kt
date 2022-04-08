package me.khruslan.tierlistmaker.ui.screens.tierlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.ActivityTierListBinding
import me.khruslan.tierlistmaker.utils.extensions.findNavHostFragmentById

/**
 * [AppCompatActivity] that represents tier list task. Can be launched from the home task.
 */
@AndroidEntryPoint
class TierListActivity : AppCompatActivity() {

    /**
     * [TierListActivity] companion object.
     * Used to create the [Intent] for launching [TierListActivity].
     */
    companion object {
        private const val EXTRA_TIER_LIST = "me.khruslan.tierlistmaker.TIER_LIST"
        private const val KEY_TIER_LIST = "tierList"

        /**
         * Creates the [Intent] for launching [TierListActivity].
         *
         * @param context activity context.
         * @param tierList tier list extra.
         * @return Created [Intent].
         */
        fun newIntent(context: Context, tierList: TierList) =
            Intent(context, TierListActivity::class.java).apply {
                putExtra(EXTRA_TIER_LIST, tierList)
            }
    }

    /**
     * [Bundle] to set in navigation graph. Created from intent extras.
     */
    private val navGraphBundle: Bundle
        get() {
            val tierList = intent.getParcelableExtra<TierList>(EXTRA_TIER_LIST)
            return bundleOf(KEY_TIER_LIST to tierList)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()
        setNavigationGraph()
    }

    /**
     * Inflates [ActivityTierListBinding] and sets its root as a content view.
     */
    private fun setContentView() {
        val binding = ActivityTierListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * Initializes navigation graph with [navGraphBundle].
     */
    private fun setNavigationGraph() {
        val navHostFragment = findNavHostFragmentById(R.id.tier_list_content)
        navHostFragment.navController.setGraph(R.navigation.tier_list_graph, navGraphBundle)
    }
}