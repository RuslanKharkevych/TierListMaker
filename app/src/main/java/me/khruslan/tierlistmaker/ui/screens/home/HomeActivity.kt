package me.khruslan.tierlistmaker.ui.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.databinding.ActivityHomeBinding
import me.khruslan.tierlistmaker.utils.extensions.findNavHostFragmentById

/**
 * [AppCompatActivity] that represents home task. Is a launch activity.
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()
        setupNavigation()
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
}