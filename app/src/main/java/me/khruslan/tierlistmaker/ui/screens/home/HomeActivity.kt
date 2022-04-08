package me.khruslan.tierlistmaker.ui.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.databinding.ActivityHomeBinding

/**
 * [AppCompatActivity] that represents home task. Is a launch activity.
 */
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()
        setupView()
    }

    /**
     * Inflates [binding] and sets its root as a content view.
     */
    private fun setContentView() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * Sets click listener for the toolbar navigation icon.
     */
    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener {}
    }
}