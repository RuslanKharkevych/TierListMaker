package me.khruslan.tierlistmaker.presentation.screens.tierlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.ActivityTierListBinding
import me.khruslan.tierlistmaker.presentation.utils.hints.tierlist.TierListHintStep
import me.khruslan.tierlistmaker.presentation.viewmodels.TierListActivityViewModel
import me.khruslan.tierlistmaker.util.analytics.AnalyticsService
import me.khruslan.tierlistmaker.util.findNavHostFragmentById
import me.khruslan.tierlistmaker.util.getParcelableExtraCompat
import me.khruslan.tierlistmaker.util.getSerializableExtraCompat
import me.khruslan.tierlistmaker.util.log.navigation.FragmentNavigationLogger
import javax.inject.Inject

/**
 * Activity that represents tier list task.
 *
 * Can be launched from the home task. Hosts fragments from the tier list navigation graph.
 *
 * @constructor Default no-arg constructor.
 */
@AndroidEntryPoint
class TierListActivity : AppCompatActivity() {

    /**
     * Navigation helpers and constants.
     *
     * Used to create the intent for launching [TierListActivity].
     */
    companion object NavHelpers {

        /**
         * Name of the intent extra for tier list.
         *
         * The extra with this key must be passed to the intent when starting this activity.
         */
        private const val EXTRA_TIER_LIST = "me.khruslan.tierlistmaker.TIER_LIST"

        /**
         * Name of the intent extra for hint step.
         *
         * The extra with this key can be optionally passed to the intent when starting this
         * activity.
         */
        private const val EXTRA_HINT_STEP = "me.khruslan.tierlistmaker.HINT_STEP"

        /**
         * Key of the fragment argument for the tier list.
         *
         * Argument with the same name must be declared in the start destination of the navigation
         * graph.
         */
        private const val KEY_TIER_LIST = "tierList"

        /**
         * Key of the fragment argument for the hint step name.
         *
         * Argument with the same name must be declared in the start destination of the navigation
         * graph.
         */
        private const val KEY_HINT_STEP_NAME = "hintStepName"

        /**
         * Creates the intent for launching [TierListActivity].
         *
         * Always prefer this function over manually constructing the intent.
         *
         * @param context Activity context.
         * @param tierList Required tier list argument.
         * @param hintStep Optional hint step argument.
         * @return Created intent that can be used to start activity.
         */
        fun newIntent(
            context: Context,
            tierList: TierList,
            hintStep: TierListHintStep? = null
        ): Intent {
            return Intent(context, TierListActivity::class.java)
                .putExtra(EXTRA_TIER_LIST, tierList)
                .putExtra(EXTRA_HINT_STEP, hintStep)
        }
    }

    /**
     * View model of the activity.
     *
     * Saves tier list in the database. The tier list argument is passed to the view model through
     * the saved state handle. Make sure that the same extra name is used there.
     */
    private val viewModel: TierListActivityViewModel by viewModels()

    /**
     * Service for logging analytic events.
     *
     * Used for capturing navigation destination changes.
     */
    @Inject
    lateinit var analyticsService: AnalyticsService

    /**
     * Bundle to set in navigation graph.
     *
     * Created from intent extras. The arguments from this bundle can be accessed in the fragment,
     * which is a start destination of the tier list navigation graph. Note that [EXTRA_HINT_STEP]
     * is mapped to [KEY_HINT_STEP_NAME] because nullable enums are not supported by the Safe Args
     * plugin.
     */
    private val navGraphBundle: Bundle
        get() {
            val tierList = intent.getParcelableExtraCompat<TierList>(EXTRA_TIER_LIST)
            val hintStep = intent.getSerializableExtraCompat<TierListHintStep>(EXTRA_HINT_STEP)
            return bundleOf(KEY_TIER_LIST to tierList, KEY_HINT_STEP_NAME to hintStep?.name)
        }

    /**
     * Inflates activity view from XML and configures navigation graph.
     *
     * Called when the activity is starting.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut
     * down then this bundle contains the data it most recently supplied in.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()
        setNavigationGraph()
    }

    /**
     * Synchronously saves tier list to the database when app goes to background.
     *
     * Called as part of the activity lifecycle when the user no longer actively interacts with the
     * activity, but it is still visible on screen.
     */
    override fun onPause() {
        if (!isFinishing) viewModel.saveTierList()
        super.onPause()
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
     *
     * Additionally adds logger of fragment navigation events.
     */
    private fun setNavigationGraph() {
        val navController = findNavHostFragmentById(R.id.tier_list_content).navController
        navController.setGraph(R.navigation.tier_list_graph, navGraphBundle)
        navController.addOnDestinationChangedListener(FragmentNavigationLogger(analyticsService))
    }
}