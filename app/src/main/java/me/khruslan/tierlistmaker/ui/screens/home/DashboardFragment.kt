package me.khruslan.tierlistmaker.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.tierlist.TierListPreview
import me.khruslan.tierlistmaker.databinding.FragmentDashboardBinding
import me.khruslan.tierlistmaker.navigation.contracts.TierListResultContract
import me.khruslan.tierlistmaker.ui.adapters.TierListPreviewAdapter
import me.khruslan.tierlistmaker.viewmodels.DashboardViewModel
import timber.log.Timber

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var previewsAdapter: TierListPreviewAdapter

    private val tierListLauncher = registerForActivityResult(TierListResultContract()) { result ->
        if (result != null) {
            viewModel.handleTierListResult(result)
        } else {
            Timber.e("tierListLauncher: result is null")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAddNewListButton()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAddNewListButton() {
        binding.btnAddNewList.setOnClickListener {
            val title = getString(R.string.tier_list_default_title)
            val tierList = viewModel.createNewTierList(title)
            tierListLauncher.launch(tierList)
        }
    }

    private fun initObservers() {
        viewModel.tierListPreviewsLiveData.observe(viewLifecycleOwner, tierListPreviewsObserver)
        viewModel.addPreviewLiveData.observe(viewLifecycleOwner, addPreviewObserver)
        viewModel.updatePreviewsLiveData.observe(viewLifecycleOwner, updatePreviewsObserver)
    }

    private val tierListPreviewsObserver = Observer<MutableList<TierListPreview>> { previews ->
        previewsAdapter = TierListPreviewAdapter(
            tierListPreviews = previews,
            onClick = { position ->
                tierListLauncher.launch(viewModel.getTierListByPosition(position))
            }
        )

        with(binding.listTierLists) {
            adapter = previewsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private val addPreviewObserver = Observer<Int> { position ->
        previewsAdapter.notifyItemInserted(position)
        binding.listTierLists.smoothScrollToPosition(position)
    }

    private val updatePreviewsObserver = Observer<Unit> {
        previewsAdapter.notifyDataSetChanged()
    }
}