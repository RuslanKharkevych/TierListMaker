package me.khruslan.tierlistmaker.ui.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()
        setupView()
    }

    private fun setContentView() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener {}
    }
}