package me.khruslan.tierlistmaker.presentation.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.FragmentCollectionBinding
import me.khruslan.tierlistmaker.presentation.adapters.TierListPreviewAdapter
import me.khruslan.tierlistmaker.presentation.models.ListState
import me.khruslan.tierlistmaker.presentation.screens.common.EnterTierListTitleDialog
import me.khruslan.tierlistmaker.presentation.screens.tierlist.TierListActivity
import me.khruslan.tierlistmaker.presentation.utils.FeedbackUtils
import me.khruslan.tierlistmaker.presentation.utils.hints.collection.CollectionHintGroup
import me.khruslan.tierlistmaker.presentation.utils.hints.collection.CollectionHintStep
import me.khruslan.tierlistmaker.presentation.utils.hints.core.HintStep
import me.khruslan.tierlistmaker.presentation.utils.hints.tierlist.TierListHintStep
import me.khruslan.tierlistmaker.presentation.utils.navigation.TierListNavArgs
import me.khruslan.tierlistmaker.presentation.utils.navigation.TierListResultContract
import me.khruslan.tierlistmaker.presentation.utils.navigation.TierListResultException
import me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable.enableReordering
import me.khruslan.tierlistmaker.presentation.utils.setOnThrottledClickListener
import me.khruslan.tierlistmaker.presentation.viewmodels.CollectionViewModel
import me.khruslan.tierlistmaker.presentation.viewmodels.HomeActivityViewModel
import me.khruslan.tierlistmaker.util.log.navigation.setLogTag
import timber.log.Timber

/**
 * Fragment that represents tier list collection screen.
 *
 * It is a start destination for the home navigation graph.
 *
 * @constructor Default no-arg constructor.
 */
@AndroidEntryPoint
class CollectionFragment : Fragment() {

    /**
     * Nullable reference of [binding].
     *
     * Must be initialized in [onCreateView] and deinitialized in [onDestroyView].
     */
    private var _binding: FragmentCollectionBinding? = null

    /**
     * View binding of the fragment.
     *
     * Make sure to access this property only when fragment's view is created to avoid
     * [NullPointerException].
     */
    private val binding get() = _binding!!

    /**
     * View model of the fragment.
     *
     * Provides UI state and handles user actions.
     */
    private val viewModel: CollectionViewModel by viewModels()

    /**
     * View model of the hosting activity.
     *
     * Used for handling events sent by other fragments.
     */
    private val activityViewModel: HomeActivityViewModel by activityViewModels()

    /**
     * Recycler view adapter for the tier list previews.
     *
     * Initialized when tier list previews are fetched.
     */
    private lateinit var previewsAdapter: TierListPreviewAdapter

    /**
     * Activity result launcher of the [TierListResultContract].
     *
     * Used to launch [TierListActivity] and obtain the created or updated tier list as a result.
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
     * Collection fragment constants for internal use.
     */
    private companion object Constants {

        /**
         * A log tag of the alert for confirmation tier list removal.
         */
        private const val REMOVE_TIER_LIST_CONFIRMATION_ALERT_LOG_TAG =
            "RemoveTierListConfirmationAlert"
    }

    /**
     * Inflates [binding] and returns its root.
     *
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater Used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be
     * attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     * saved state as given here.
     *
     * @return Returns the root view of the fragment's binding.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Initializes button click listeners and live data observers.
     *
     * Called immediately after [onCreateView] has returned, but before any saved state has been
     * restored in to the view.
     *
     * @param view The view returned by [onCreateView] method.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     * saved state as given here.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAddNewListButton()
        initReportIssueButton()
        initObservers()
    }

    /**
     * Deinitializes [binding].
     *
     * Called when the view previously created by onCreateView has been detached from the fragment.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Sets click listener to the "Add new list" button.
     *
     * On click shows [EnterTierListTitleDialog].
     */
    private fun initAddNewListButton() {
        binding.btnAddNewList.setOnThrottledClickListener {
            Timber.i("Add new list button clicked")
            showEnterTierListTitleDialog()
        }
    }

    /**
     * Sets click listener to the "Report the issue" button.
     *
     * On click calls [FeedbackUtils.reportIssue].
     */
    private fun initReportIssueButton() {
        binding.groupError.btnReport.setOnThrottledClickListener {
            Timber.i("Report the issue button clicked")
            FeedbackUtils.reportIssue(requireActivity())
        }
    }

    /**
     * Initializes all live data observers.
     *
     * This function must be called from [onViewCreated] because all observers are scoped to the
     * fragment's view lifecycle.
     */
    private fun initObservers() {
        viewModel.tierListPreviewsLiveData.observe(viewLifecycleOwner, tierListPreviewsObserver)
        viewModel.addPreviewEvent.observe(viewLifecycleOwner, addPreviewObserver)
        viewModel.updatePreviewEvent.observe(viewLifecycleOwner, updatePreviewsObserver)
        viewModel.listStateLiveData.observe(viewLifecycleOwner, listStateObserver)
        viewModel.errorEvent.observe(viewLifecycleOwner, errorObserver)
        viewModel.tierListCreatedEvent.observe(viewLifecycleOwner, tierListCreatedObserver)
        activityViewModel.hintEvent.observe(viewLifecycleOwner, hintObserver)
    }

    /**
     * Observer of the tier list previews.
     *
     * Initializes [previewsAdapter] and attaches it to the recycler view.
     */
    private val tierListPreviewsObserver = Observer<MutableList<TierList.Preview>> { previews ->
        previewsAdapter = TierListPreviewAdapter(
            tierListPreviews = previews,
            onClick = { position ->
                Timber.i("Clicked on tier list at position $position")
                val tierList = viewModel.getTierListAtPosition(position)
                navigateToTierList(tierList)
            },
            onPreviewMoved = { from, to ->
                Timber.i("Moved tier list from position $from to $to")
                viewModel.swapTierLists(from, to)
            },
            onPreviewSwiped = { index ->
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
     * Observer of the added preview event.
     *
     * Notifies [previewsAdapter] that new preview has been inserted and scrolls to it.
     */
    private val addPreviewObserver = Observer<Unit> {
        Timber.i("Inserted new tier list")
        previewsAdapter.notifyDataSetChanged()
        binding.listTierLists.smoothScrollToPosition(0)
    }

    /**
     * Observer of the updated preview event.
     *
     * Notifies [previewsAdapter] that the preview has been updated.
     */
    private val updatePreviewsObserver = Observer<Int> { position ->
        Timber.i("Updated tier list at position $position")
        previewsAdapter.notifyItemChanged(position)
    }

    /**
     * Observer of the state of the loaded list of previews.
     *
     * Updates visibility of loading, error and empty layouts.
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
     * Observer of error events.
     *
     * Presents error snackbar with observed error message.
     */
    private val errorObserver = Observer<Int> { errorMessageResId ->
        Timber.i("Presenting error snackbar")
        presentErrorSnackbar(errorMessageResId)
    }

    /**
     * Observer of the created tier list event.
     *
     * Launches [tierListLauncher].
     */
    private val tierListCreatedObserver = Observer<TierList> { tierList ->
        Timber.i("Starting tier list activity for result. Tier list: $tierList")
        navigateToTierList(tierList)
    }

    /**
     * Observer of the hint events.
     *
     * Depending on the hint type, either shows a hint from [CollectionHintGroup] or navigates to
     * [TierListActivity] to show [TierListHintStep].
     *
     * @throws IllegalArgumentException If observed step is neither [CollectionHintStep] nor
     * [TierListHintStep].
     */
    private val hintObserver = Observer<HintStep> { step ->
        when (step) {
            is CollectionHintStep -> CollectionHintGroup(requireActivity(), binding).show(step)
            is TierListHintStep -> handleTierListHint(step)
            else -> throw IllegalArgumentException("Unknown hint step: $step")
        }
    }

    /**
     * Shows [Snackbar] with error message and "Refresh" action.
     *
     * On "Refresh" button click refreshes previews with animation.
     *
     * @param textResId Error message resource identifier.
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
     * Shows confirmation alert for removing tier list when preview is swiped.
     *
     * On positive button click removes tier list and the corresponding preview. On negative button
     * click or when alert is canceled restores swiped preview.
     *
     * @param tierListIndex Position of the tier list to remove.
     */
    private fun showRemoveTierListConfirmationAlert(tierListIndex: Int) {
        val tierListTitle = viewModel.getTierListAtPosition(tierListIndex).title
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
     * Shows dialog with input field that asks user to enter tier list title.
     *
     * On save click creates a new tier list with entered title.
     */
    private fun showEnterTierListTitleDialog() {
        EnterTierListTitleDialog.Builder()
            .setDialogTitle(R.string.dialog_title_name_tier_list)
            .setOnConfirmListener { title -> viewModel.createNewTierList(title) }
            .build()
            .show(requireActivity())
    }

    /**
     * Launches [tierListLauncher].
     *
     * Navigates to [TierListActivity] and optionally shows tier list hint.
     *
     * @param tierList Tier list to show.
     * @param hintStep Hint step to show (optional).
     */
    private fun navigateToTierList(tierList: TierList, hintStep: TierListHintStep? = null) {
        val args = TierListNavArgs(tierList, hintStep)
        tierListLauncher.launch(args)
    }

    /**
     * Attempts to show tier list hint.
     *
     * Navigates to the first tier list and shows provided hint step. In case no tier lists found,
     * presents "No tier lists found" snackbar.
     *
     * @param step Hint step to show.
     */
    private fun handleTierListHint(step: TierListHintStep) {
        val tierList = viewModel.getFirstTierListOrNull()

        if (tierList != null) {
            navigateToTierList(tierList, step)
        } else {
            presentNoTierListsFoundSnackbar()
        }
    }

    /**
     * Shows [Snackbar] with "No tier lists found" message and no actions.
     */
    private fun presentNoTierListsFoundSnackbar() {
        Snackbar
            .make(binding.root, R.string.no_tier_lists_found_message, Snackbar.LENGTH_LONG)
            .setAnchorView(binding.btnAddNewList)
            .show()
    }
}