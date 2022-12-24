package me.khruslan.tierlistmaker.ui.screens.home

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.databinding.ActivityHomeBinding
import me.khruslan.tierlistmaker.ui.viewmodels.HomeViewModel
import me.khruslan.tierlistmaker.utils.extensions.findNavHostFragmentById

/**
 * [AppCompatActivity] that represents home task. Is a launch activity.
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    /**
     * Companion object of [HomeActivity] that stores keys for saving and restoring view state.
     */
    companion object {
        private const val KEY_DRAWER_OPENED = "isDrawerOpened"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()
        setupNavigation()
        setupThemeSwitcher()
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
    }

    /**
     * Initializes logic for toggling theme when user clicks on "Change theme" image button in
     * navigation drawer.
     */
    private fun setupThemeSwitcher() {
        val navHeaderView = binding.navView.getHeaderView(0)
        val btnTheme: ImageButton = navHeaderView.findViewById(R.id.btn_theme)
        btnTheme.setOnClickListener {
            viewModel.toggleTheme()
        }
    }
}