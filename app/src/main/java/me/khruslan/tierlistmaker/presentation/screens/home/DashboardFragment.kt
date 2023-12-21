package me.khruslan.tierlistmaker.presentation.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.FragmentDashboardBinding
import me.khruslan.tierlistmaker.presentation.adapters.TierListPreviewAdapter
import me.khruslan.tierlistmaker.presentation.models.ListState
import me.khruslan.tierlistmaker.presentation.utils.navigation.TierListResultContract
import me.khruslan.tierlistmaker.presentation.utils.navigation.TierListResultException
import me.khruslan.tierlistmaker.presentation.screens.common.EnterTierListTitleDialog
import me.khruslan.tierlistmaker.presentation.screens.tierlist.TierListActivity
import me.khruslan.tierlistmaker.presentation.viewmodels.DashboardViewModel
import me.khruslan.tierlistmaker.util.log.navigation.setLogTag
import me.khruslan.tierlistmaker.presentation.utils.FeedbackUtils
import me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable.enableReordering
import me.khruslan.tierlistmaker.presentation.utils.setOnThrottledClickListener
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
        Timber.i("Handling tier list result: $result")
        try {
            viewModel.handleTierListResult(result)
        } catch (e: TierListResultException) {
            Timber.e(e, "Unable to parse tier list result")
        }
    }

    /**
     * Companion object of the [DashboardFragment] used for storing constants.
     */
    private companion object {
        private const val REMOVE_TIER_LIST_CONFIRMATION_ALERT_LOG_TAG =
            "RemoveTierListConfirmationAlert"
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
     * Sets click listener to the "Add new list" button.
     */
    private fun initAddNewListButton() {
        binding.btnAddNewList.setOnThrottledClickListener {
            Timber.i("Add new list button clicked")
            showEnterTierListTitleDialog()
        }
    }

    /**
     * Sets [View.OnClickListener] to the "Report the issue" button.
     */
    private fun initReportIssueButton() {
        binding.groupError.btnReport.setOnClickListener {
            Timber.i("Report the issue button clicked")
            FeedbackUtils.reportIssue(requireActivity())
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
        viewModel.errorEvent.observe(viewLifecycleOwner, errorObserver)
        viewModel.tierListCreatedEvent.observe(viewLifecycleOwner, tierListCreatedObserver)
    }

    /**
     * Observer for the tier list previews.
     * Initializes [previewsAdapter] and attaches it to the [RecyclerView].
     */
    private val tierListPreviewsObserver = Observer<MutableList<TierList.Preview>> { previews ->
        previewsAdapter = TierListPreviewAdapter(
            tierListPreviews = previews,
            onClick = { position ->
                Timber.i("Clicked on tier list at position $position")
                tierListLauncher.launch(viewModel.getTierListByPosition(position))
            },
            onTierListMoved = { from, to ->
                Timber.i("Moved tier list from position $from to $to")
                viewModel.swapTierLists(from, to)
            },
            onTierListSwiped = { index ->
                Timber.i("Swiped tier list at position $index")
                showRemoveTierListConfirmationAlert(index)
            }
        )

        with(binding.listTierLists) {
            adapter = previewsAdapter
            layoutManager = LinearLayoutManager(activity)
            enableReordering()
        }
    }

    /**
     * Observer for the added preview event. Notifies [previewsAdapter] that new preview was
     * inserted and scrolls to it.
     */
    private val addPreviewObserver = Observer<Int> { position ->
        Timber.i("Inserted tier list at position $position")
        previewsAdapter.notifyItemInserted(position)
        binding.listTierLists.smoothScrollToPosition(position)
    }

    /**
     * Observer for the updated preview event. Notifies [previewsAdapter] that the preview was
     * updated
     */
    private val updatePreviewsObserver = Observer<Int> { position ->
        Timber.i("Updated tier list at position $position")
        previewsAdapter.notifyItemChanged(position)
    }

    /**
     * Observer for the state of the loaded list of previews. Updates visibility of **loading**,
     * **error** and **empty** view groups.
     */
    private val listStateObserver = Observer<ListState> { state ->
        Timber.i("Observed list state: $state")
        with(binding) {
            groupLoading.root.isVisible = state == ListState.Loading
            groupEmpty.root.isVisible = state == ListState.Empty
            groupError.root.isVisible = state == ListState.Failed
            listTierLists.isVisible = state == ListState.Filled
        }
    }

    /**
     * Observer for error events. Presents error snackbar.
     * @see presentErrorSnackbar
     */
    private val errorObserver = Observer<Int> { errorMessageResId ->
        Timber.i("Presenting error snackbar")
        presentErrorSnackbar(errorMessageResId)
    }

    /**
     * Observer for the created tier list event. Launches [tierListLauncher].
     */
    private val tierListCreatedObserver = Observer<TierList> { tierList ->
        Timber.i("Starting tier list activity for result. Tier list: $tierList")
        tierListLauncher.launch(tierList)
    }

    /**
     * Shows [Snackbar] with error message and "Refresh" action.
     *
     * @param textResId error message resource identifier.
     */
    private fun presentErrorSnackbar(@StringRes textResId: Int) {
        Snackbar
            .make(binding.root, textResId, Snackbar.LENGTH_INDEFINITE)
            .setAnchorView(binding.btnAddNewList)
            .setAction(R.string.btn_refresh) {
                Timber.i("Refresh button pressed")
                viewModel.refreshPreviews()
                TransitionManager.beginDelayedTransition(binding.root)
            }
            .show()
    }

    /**
     * Shows confirmation alert for removing tier list when preview is swiped. On positive button
     * click removes tier list and the corresponding preview. On negative button click or when
     * alert is canceled restores swiped preview.
     *
     * @param tierListIndex position of the tier list to remove.
     */
    private fun showRemoveTierListConfirmationAlert(tierListIndex: Int) {
        val tierListTitle = viewModel.getTierListByPosition(tierListIndex).title
        val alertTitle = getString(R.string.remove_tier_list_confirmation_title, tierListTitle)

        MaterialAlertDialogBuilder(requireActivity())
            .setTitle(alertTitle)
            .setPositiveButton(R.string.btn_yes) { _, _ ->
                Timber.i("Confirmed removing the tier list")
                previewsAdapter.removePreview(tierListIndex)
                viewModel.removeTierList(tierListIndex)
            }
            .setNegativeButton(R.string.btn_no) { _, _ ->
                Timber.i("Declined removing the tier list")
                previewsAdapter.notifyItemChanged(tierListIndex)
            }
            .setOnCancelListener {
                Timber.i("Canceled removing the tier list")
                previewsAdapter.notifyItemChanged(tierListIndex)
            }
            .create()
            .setLogTag(REMOVE_TIER_LIST_CONFIRMATION_ALERT_LOG_TAG)
            .show()
    }

    /**
     * Shows dialog with input field that asks user to enter tier list title. On save click creates
     * a new tier list with entered title.
     */
    private fun showEnterTierListTitleDialog() {
        EnterTierListTitleDialog.Builder()
            .setDialogTitle(R.string.dialog_title_name_tier_list)
            .setOnConfirmListener { title -> viewModel.createNewTierList(title) }
            .build()
            .show(requireActivity())
    }
}