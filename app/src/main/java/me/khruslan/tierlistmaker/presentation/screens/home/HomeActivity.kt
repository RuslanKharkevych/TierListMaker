package me.khruslan.tierlistmaker.presentation.screens.home

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.databinding.ActivityHomeBinding
import me.khruslan.tierlistmaker.presentation.viewmodels.HomeActivityViewModel
import me.khruslan.tierlistmaker.util.findNavHostFragmentById
import me.khruslan.tierlistmaker.util.log.navigation.DrawerStateLogger
import me.khruslan.tierlistmaker.util.log.navigation.FragmentNavigationLogger
import me.khruslan.tierlistmaker.presentation.utils.AnimatorUtils
import me.khruslan.tierlistmaker.presentation.utils.AnimatorUtils.addOnEndAction
import me.khruslan.tierlistmaker.util.analytics.AnalyticsService
import timber.log.Timber
import javax.inject.Inject

/**
 * Activity that represents home task.
 *
 * It is a launch activity. Displays a splash screen when it's created. Hosts fragments from the
 * home navigation graph.
 *
 * @constructor Default no-arg constructor.
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    /**
     * View binding of the activity.
     *
     * Consists top app bar, navigation drawer and fragment container.
     */
    private lateinit var binding: ActivityHomeBinding

    /**
     * View model of the activity.
     *
     * Used for changing application theme and communication between fragments.
     */
    private val viewModel: HomeActivityViewModel by viewModels()

    /**
     * Service for logging analytic events.
     *
     * Used for capturring navigation destionation changes.
     */
    @Inject
    lateinit var analyticsService: AnalyticsService

    /**
     * Keys for saving and restoring view state and other constants.
     */
    companion object Constants {

        /**
         * Key for saving and restoring drawer open/closed state.
         */
        private const val KEY_DRAWER_OPENED = "isDrawerOpened"

        /**
         * A duration of the splash screen exit animation in milliseconds.
         */
        const val SPLASH_SCREEN_EXIT_ANIM_DURATION = 400L
    }

    /**
     * Called when the activity is starting.
     *
     * - Installs and configures splash screen.
     * - Inflates activity view from XML.
     * - Configures navigation.
     * - Registers theme switcher click listener.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut
     * down then this bundle contains the data it most recently supplied in.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        setContentView()
        setupNavigation()
        setupThemeSwitcher()
        splashScreen.setupExitAnimation()
    }

    /**
     * Saves navigation drawer opened/closed state.
     *
     * Called to retrieve per-instance state from an activity before being killed so that the state
     * can be restored in [onRestoreInstanceState].
     *
     * @param outState Bundle in which to place the saved state.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_DRAWER_OPENED, binding.root.isDrawerOpen(GravityCompat.START))
    }

    /**
     * Restores navigation drawer open/closed state and opens drawer if needed.
     *
     * This method is called after [AppCompatActivity.onStart] when the activity is being
     * re-initialized from a previously saved state.
     *
     * @param savedInstanceState The data most recently supplied in [onSaveInstanceState].
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState.getBoolean(KEY_DRAWER_OPENED)) {
            binding.root.openDrawer(GravityCompat.START)
        }
    }

    /**
     * Inflates [binding] and sets its root as a content view.
     */
    private fun setContentView() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * Connects app bar and navigation drawer with nav controller.
     *
     * Additionally adds loggers of fragment navigation and drawer state change events.
     */
    private fun setupNavigation() {
        val navController = findNavHostFragmentById(R.id.home_content).navController
        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.root)
        binding.navView.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener(FragmentNavigationLogger(analyticsService))
        binding.root.addDrawerListener(DrawerStateLogger())
    }

    /**
     * Initializes logic for toggling theme when user clicks on "Change theme" image button in the
     * navigation drawer.
     */
    private fun setupThemeSwitcher() {
        val navHeaderView = binding.navView.getHeaderView(0)
        val btnTheme: ImageButton = navHeaderView.findViewById(R.id.btn_theme)

        btnTheme.setOnClickListener {
            Timber.i("Theme toggle button clicked")
            viewModel.toggleTheme()
        }
    }

    /**
     * Initializes and starts circular conceal animation on splash screen exit.
     */
    private fun SplashScreen.setupExitAnimation() {
        setOnExitAnimationListener { splashScreenViewProvider ->
            AnimatorUtils
                .createCircularConceal(splashScreenViewProvider.view)
                .setDuration(SPLASH_SCREEN_EXIT_ANIM_DURATION)
                .addOnEndAction {
                    splashScreenViewProvider.remove()
                    Timber.i("Splash screen animation finished")
                }
                .start()
        }
    }
}