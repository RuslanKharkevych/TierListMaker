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
import timber.log.Timber

/**
 * [AppCompatActivity] that represents home task. Is a launch activity.
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeActivityViewModel by viewModels()

    /**
     * Companion object of [HomeActivity] that stores keys for saving and restoring view state and
     * other constants.
     */
    companion object {
        private const val KEY_DRAWER_OPENED = "isDrawerOpened"
        const val SPLASH_SCREEN_EXIT_ANIM_DURATION = 400L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        setContentView()
        setupNavigation()
        setupThemeSwitcher()
        splashScreen.setupExitAnimation()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_DRAWER_OPENED, binding.root.isDrawerOpen(GravityCompat.START))
    }

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
     */
    private fun setupNavigation() {
        val navController = findNavHostFragmentById(R.id.home_content).navController
        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.root)
        binding.navView.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener(FragmentNavigationLogger())
        binding.root.addDrawerListener(DrawerStateLogger())
    }

    /**
     * Initializes logic for toggling theme when user clicks on "Change theme" image button in
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