package me.khruslan.tierlistmaker.ui.screens.tierlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.databinding.ActivityTierListBinding
import me.khruslan.tierlistmaker.utils.extensions.findNavHostFragmentById

@AndroidEntryPoint
class TierListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()
        setNavigationGraph()
    }

    private fun setContentView() {
        val binding = ActivityTierListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setNavigationGraph() {
        val navHostFragment = findNavHostFragmentById(R.id.tier_list_content)
        navHostFragment.navController.setGraph(R.navigation.tier_list_graph, intent.extras)
    }
}