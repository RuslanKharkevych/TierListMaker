package me.khruslan.tierlistmaker.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.state.ListState
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
        initReportIssueButton()
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
     * Sets [View.OnClickListener] to the "Report the issue" button.
     */
    private fun initReportIssueButton() {
        binding.groupError.btnReport.setOnClickListener {
            // TODO: Report the error
        }
    }

    /**
     * Initializes all [LiveData] observers.
     */
    private fun initObservers() {
        viewModel.tierListPreviewsLiveData.observe(viewLifecycleOwner, tierListPreviewsObserver)
        viewModel.addPreviewEvent.observe(viewLifecycleOwner, addPreviewObserver)
        viewModel.updatePreviewEvent.observe(viewLifecycleOwner, updatePreviewsObserver)
        viewModel.listStateLiveData.observe(viewLifecycleOwner, listStateObserver)
        viewModel.saveErrorEvent.observe(viewLifecycleOwner, saveErrorObserver)
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
     * Observer for the added preview event. Notifies [previewsAdapter] that new preview was
     * inserted and scrolls to it.
     */
    private val addPreviewObserver = Observer<Int> { position ->
        previewsAdapter.notifyItemInserted(position)
        binding.listTierLists.smoothScrollToPosition(position)
    }

    /**
     * Observer for the updated preview event. Notifies [previewsAdapter] that the preview was
     * updated
     */
    private val updatePreviewsObserver = Observer<Int> { position ->
        previewsAdapter.notifyItemChanged(position)
    }

    /**
     * Observer for the state of the loaded list of previews. Updates visibility of **loading**,
     * **error** and **empty** view groups.
     */
    private val listStateObserver = Observer<ListState> { state ->
        with(binding) {
            groupLoading.root.isVisible = state == ListState.Loading
            groupEmpty.root.isVisible = state == ListState.Empty
            groupError.root.isVisible = state == ListState.Failed
            listTierLists.isVisible = state == ListState.Filled
        }
    }

    /**
     * Observer for the saving preview failure event.
     * Shows [Snackbar] with error message and "Refresh" action.
     */
    private val saveErrorObserver = Observer<Unit> {
        Snackbar
            .make(binding.root, R.string.save_previews_error_message, Snackbar.LENGTH_INDEFINITE)
            .setAnchorView(binding.btnAddNewList)
            .setAction(R.string.btn_refresh) {
                viewModel.refreshPreviews()
                TransitionManager.beginDelayedTransition(binding.root)
            }
            .show()
    }
}