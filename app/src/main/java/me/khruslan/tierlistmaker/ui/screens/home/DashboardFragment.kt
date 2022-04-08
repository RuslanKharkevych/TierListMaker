package me.khruslan.tierlistmaker.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.FragmentDashboardBinding
import me.khruslan.tierlistmaker.navigation.TierListResultContract
import me.khruslan.tierlistmaker.ui.adapters.TierListPreviewAdapter
import me.khruslan.tierlistmaker.ui.screens.tierlist.TierListActivity
import me.khruslan.tierlistmaker.viewmodels.DashboardViewModel
import timber.log.Timber

/**
 * [Fragment] that represents dashboard screen.
 * Is a start destination for the home navigation graph.
 */
@AndroidEntryPoint
class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()

    /**
     * Adapter for the tier list previews.
     */
    private lateinit var previewsAdapter: TierListPreviewAdapter

    /**
     * [ActivityResultLauncher] of the [TierListResultContract].
     * Used to launch [TierListActivity] and obtain the created or updated [TierList] as a result.
     */
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

    /**
     * Sets [View.OnClickListener] to the "Add new list" button.
     */
    private fun initAddNewListButton() {
        binding.btnAddNewList.setOnClickListener {
            val title = getString(R.string.tier_list_default_title)
            val tierList = viewModel.createNewTierList(title)
            tierListLauncher.launch(tierList)
        }
    }

    /**
     * Initializes all [LiveData] observers.
     */
    private fun initObservers() {
        viewModel.tierListPreviewsLiveData.observe(viewLifecycleOwner, tierListPreviewsObserver)
        viewModel.addPreviewLiveData.observe(viewLifecycleOwner, addPreviewObserver)
        viewModel.updatePreviewsLiveData.observe(viewLifecycleOwner, updatePreviewsObserver)
    }

    /**
     * Observer for the tier list previews.
     * Initializes [previewsAdapter] and attaches it to the [RecyclerView].
     */
    private val tierListPreviewsObserver = Observer<MutableList<TierList.Preview>> { previews ->
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

    /**
     * Observer for the added preview event.
     * Notifies [previewsAdapter] that new preview was inserted and scrolls to it.
     */
    private val addPreviewObserver = Observer<Int> { position ->
        previewsAdapter.notifyItemInserted(position)
        binding.listTierLists.smoothScrollToPosition(position)
    }

    /**
     * Observer for the updated preview event.
     * Notifies [previewsAdapter] that data set was changed.
     */
    private val updatePreviewsObserver = Observer<Unit> {
        previewsAdapter.notifyDataSetChanged()
    }
}