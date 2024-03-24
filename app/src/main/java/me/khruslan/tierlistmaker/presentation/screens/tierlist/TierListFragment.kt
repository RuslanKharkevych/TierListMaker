package me.khruslan.tierlistmaker.presentation.screens.tierlist

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.models.tierlist.BacklogDataChanged
import me.khruslan.tierlistmaker.data.models.tierlist.BacklogImagesAdded
import me.khruslan.tierlistmaker.data.models.tierlist.BacklogItemInserted
import me.khruslan.tierlistmaker.data.models.tierlist.ImageRemoved
import me.khruslan.tierlistmaker.data.models.tierlist.ImageSizeUpdated
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierChanged
import me.khruslan.tierlistmaker.data.models.tierlist.TierInserted
import me.khruslan.tierlistmaker.data.models.tierlist.TierListChanged
import me.khruslan.tierlistmaker.data.models.tierlist.TierListEvent
import me.khruslan.tierlistmaker.data.models.tierlist.TierListExportError
import me.khruslan.tierlistmaker.data.models.tierlist.TierListReadyToShare
import me.khruslan.tierlistmaker.data.models.tierlist.TierListReadyToView
import me.khruslan.tierlistmaker.data.models.tierlist.TrashBinHighlightUpdated
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.data.providers.file.FileManager
import me.khruslan.tierlistmaker.databinding.FragmentTierListBinding
import me.khruslan.tierlistmaker.presentation.adapters.TierAdapter
import me.khruslan.tierlistmaker.presentation.adapters.TierListImageAdapter
import me.khruslan.tierlistmaker.presentation.models.LoadingProgress
import me.khruslan.tierlistmaker.presentation.models.drag.DragLocation
import me.khruslan.tierlistmaker.presentation.screens.common.EnterTierListTitleDialog
import me.khruslan.tierlistmaker.presentation.utils.drag.TierListDragListener
import me.khruslan.tierlistmaker.presentation.utils.hints.tierlist.TierListHintGroup
import me.khruslan.tierlistmaker.presentation.utils.hints.tierlist.TierListHintStep
import me.khruslan.tierlistmaker.presentation.utils.navigation.TierListResultContract
import me.khruslan.tierlistmaker.presentation.utils.recyclerview.reorderable.enableReordering
import me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll.AutoScrollManager
import me.khruslan.tierlistmaker.presentation.utils.setOnThrottledClickListener
import me.khruslan.tierlistmaker.presentation.utils.tierlist.TierListBottomBarBinder
import me.khruslan.tierlistmaker.presentation.viewmodels.TierListViewModel
import me.khruslan.tierlistmaker.util.BACKLOG_TIER_POSITION
import me.khruslan.tierlistmaker.util.analytics.AnalyticsService
import me.khruslan.tierlistmaker.util.analytics.HintShown
import me.khruslan.tierlistmaker.util.analytics.ImageDeleted
import me.khruslan.tierlistmaker.util.analytics.TierRemoved
import timber.log.Timber
import javax.inject.Inject

/**
 * Fragment that represents a tier list.
 *
 * It is a start destination for the tier list navigation graph.
 *
 * @constructor Default no-arg constructor.
 */
@AndroidEntryPoint
class TierListFragment : Fragment() {

    /**
     * Nullable reference of [binding].
     *
     * Must be initialized in [onCreateView] and deinitialized in [onDestroyView].
     */
    private var _binding: FragmentTierListBinding? = null

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
    private val viewModel: TierListViewModel by viewModels()

    /**
     * Service for logging analytic events.
     */
    @Inject
    lateinit var analyticsService: AnalyticsService

    /**
     * Recycler view adapter for the tiers.
     *
     * Initialized when fragment's view is created.
     */
    private lateinit var tiersAdapter: TierAdapter

    /**
     * Recycler view adapter for the backlog images.
     *
     * Initialized when fragment's view is created.
     */
    private lateinit var backlogAdapter: TierListImageAdapter

    /**
     * Manager that performs automatic scrolling withing the tiers recycler view.
     *
     * Initialized once recycler view is available.
     */
    private lateinit var autoScrollManager: AutoScrollManager

    /**
     * Binder that enables / disables buttons in the bottom bar depending on the tier list state.
     *
     * Initialized when fragment's view is created.
     */
    private lateinit var bottomBarBinder: TierListBottomBarBinder

    /**
     * Snackbar that allows to restore removed image.
     *
     * Must be dismissed once new drag is started to avoid restoring wrong image.
     */
    private var imageRemovedSnackbar: Snackbar? = null

    /**
     * Getter of the tier list model.
     */
    private val tierList get() = viewModel.tierList

    /**
     * Listener of the tier list drag events.
     *
     * 1. On drag started - dismisses [imageRemovedSnackbar] and starts a new drag.
     * 2. On drag location changed - updates drag target and drag location in [autoScrollManager].
     * 3. On drop - drops image and stops auto-scrolling.
     * 4. On drag ended - removes drag target, restores released image and stops auto-scrolling.
     */
    private val dragListener: TierListDragListener = object : TierListDragListener() {
        override fun onDragStarted(dragData: ImageDragData) {
            Timber.i("Invoked onDragStarted event. Drag data: $dragData")
            imageRemovedSnackbar?.dismiss()
            viewModel.startDrag(dragData)
        }

        override fun onDragLocationChanged(dragLocation: DragLocation?) {
            Timber.i("Invoked onDragLocationChanged event. Drag location: $dragLocation")
            viewModel.updateDragTarget(dragLocation?.target)
            autoScrollManager.updateDragLocation(dragLocation)
        }

        override fun onDrop(dragData: ImageDragData) {
            Timber.i("Invoked onDrop event. Drag data: $dragData")
            viewModel.dropImage(dragData)
            autoScrollManager.stopScrolling()
        }

        override fun onDragEnded() {
            Timber.i("Invoked onDragEnded event")
            viewModel.updateDragTarget(null)
            viewModel.restoreReleasedImage()
            autoScrollManager.stopScrolling()
        }

        override fun onIsDraggingChanged(isDragging: Boolean) {
            findToolbarMenuItem(R.id.item_rename_tier_list).isVisible = !isDragging
            findToolbarMenuItem(R.id.item_remove_image).isVisible = isDragging
        }
    }

    /**
     * Observer of data changes in the [tiersAdapter].
     *
     * Performs scroll to the inserted tier and invalidates bottom bar buttons.
     */
    private val tiersDataObserver: RecyclerView.AdapterDataObserver =
        object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                bottomBarBinder.invalidateZoomButtons()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                bottomBarBinder.invalidateAddNewTierButton()
                binding.listTiers.smoothScrollToPosition(positionStart)
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                bottomBarBinder.invalidateAddNewTierButton()
            }
        }

    /**
     * Activity result launcher of the [ActivityResultContracts.GetMultipleContents].
     *
     * Allows user to pick one or more images. On result saves  all selected images.
     */
    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { imageUris ->
            Timber.i("Received image picker result. Images: $imageUris")
            viewModel.saveImages(imageUris)
        }

    /**
     * Adds on back pressed callback.
     *
     * Called after [Fragment.onAttach] and before [Fragment.onCreateView].
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     * this is the state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addOnBackPressedCallback()
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
        _binding = FragmentTierListBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Initializes view and live data observers.
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

        initObservers()
        initView()
    }

    /**
     * Unregisters [tiersDataObserver] and deinitializes [binding].
     *
     * Called when the view previously created by onCreateView has been detached from the fragment.
     */
    override fun onDestroyView() {
        super.onDestroyView()

        tiersAdapter.unregisterAdapterDataObserver(tiersDataObserver)
        _binding = null
    }

    /**
     * Initializes all live data observers.
     *
     * This function must be called from [onViewCreated] because all observers are scoped to the
     * fragment's view lifecycle.
     */
    private fun initObservers() {
        viewModel.tierListEvent.observe(viewLifecycleOwner, tierListEventObserver)
        viewModel.hintEvent.observe(viewLifecycleOwner, hintObserver)
        viewModel.loadingProgressLiveData.observe(viewLifecycleOwner, loadingProgressObserver)
    }

    /**
     * Initializes [tiersAdapter] and [backlogAdapter].
     */
    private fun initTierList() {
        val imageSize = viewModel.imageSize
        initTiersAdapter(tiers = tierList.tiers, imageSize = imageSize)
        initBacklogAdapter(images = tierList.backlogImages, imageSize = imageSize)
    }

    /**
     * Observer of the loading progress.
     *
     * Shows determinate progress of adding new images or indeterminate progress of creating a tier
     * list image file. Disables bottom bar buttons if loading is in progress.
     */
    private val loadingProgressObserver = Observer<LoadingProgress?> { progress ->
        bottomBarBinder.setIntentButtonsEnabled(progress == null)
        when (progress) {
            is LoadingProgress.Determinate -> showDeterminateLoadingProgress(progress)
            LoadingProgress.Indeterminate -> showIndeterminateLoadingProgress()
            null -> hideLoadingProgress()
        }
    }

    /**
     * Observer of the tier list events.
     *
     * Notifies adapters about the changes in the tier list.
     */
    private val tierListEventObserver = Observer<TierListEvent> { event ->
        when (event) {
            is BacklogDataChanged -> backlogAdapter.notifyDataSetChanged()
            is BacklogImagesAdded -> {
                backlogAdapter.notifyDataSetChanged()
                binding.listBacklogImages.smoothScrollToPosition(0)
            }
            is BacklogItemInserted -> backlogAdapter.notifyItemInserted(event.itemPosition)
            is TierListChanged -> tiersAdapter.notifyDataSetChanged()
            is TierChanged -> tiersAdapter.notifyItemChanged(event.tierPosition)
            is TierInserted -> tiersAdapter.notifyItemInserted(event.tierPosition)
            is ImageSizeUpdated -> {
                tiersAdapter.updateImageSize(event.imageSize)
                backlogAdapter.updateImageSize(event.imageSize)
            }
            is TrashBinHighlightUpdated -> setRemoveImageHintVisible(event.highlighted)
            is ImageRemoved -> {
                setRemoveImageHintVisible(false)
                presentImageRemovedSnackbar()
                analyticsService.logEvent(ImageDeleted(event.image.payload, tierList.title))
            }
            is TierListReadyToShare -> shareTierList(event.uri)
            is TierListReadyToView -> viewTierList(event.uri)
            is TierListExportError -> presentTierListErrorSnackbar(event.errorMessageResId)
        }
    }

    /**
     * Observer of the hint events.
     *
     * Shows a hint from [TierListHintGroup] and logs [HintShown] analytic event.
     */
    private val hintObserver = Observer<TierListHintStep> { step ->
        val hintGroup = TierListHintGroup(requireActivity(), binding)
        hintGroup.show(step)
        analyticsService.logEvent(HintShown(hintGroup.name, step.name))
    }

    /**
     * Initializes toolbar, bottom bar, adapters and loading indicator.
     */
    private fun initView() {
        initToolbar()
        initBottomBar()
        initTierList()
    }

    /**
     * Initializes [tiersAdapter] and attaches it to the recycler view.
     *
     * Configures recycler view to disable animations, enable auto-scrolling and reordering.
     * Registers [tiersDataObserver].
     *
     * @param tiers List of the tiers.
     * @param imageSize Size of the image.
     */
    private fun initTiersAdapter(tiers: MutableList<Tier>, imageSize: Int) {
        tiersAdapter = TierAdapter(
            tiers = tiers,
            dragListener = dragListener,
            imageSize = imageSize,
            onTierRemoved = { index, tier ->
                viewModel.insertImagesToBacklog(tier.images)
                viewModel.updateTierStyles()
                analyticsService.logEvent(TierRemoved(index, tierList.title))
            }
        )
        tiersAdapter.registerAdapterDataObserver(tiersDataObserver)

        with(binding.listTiers) {
            itemAnimator = null
            adapter = tiersAdapter
            layoutManager = LinearLayoutManager(activity)
            autoScrollManager = AutoScrollManager(this)
            enableReordering()
        }
    }

    /**
     * Initializes [backlogAdapter] and attaches it to the recycler view.
     *
     * @param images List of backlog images.
     * @param imageSize Size of the image.
     */
    private fun initBacklogAdapter(images: MutableList<Image>, imageSize: Int) {
        backlogAdapter = TierListImageAdapter(
            images = images,
            dragListener = dragListener,
            tierPosition = BACKLOG_TIER_POSITION,
            imageSize = imageSize
        )

        with(binding.listBacklogImages) {
            setOnDragListener(dragListener)
            tag = TierDragData(BACKLOG_TIER_POSITION)
            adapter = backlogAdapter
            layoutManager = LinearLayoutManager(
                activity,
                RecyclerView.HORIZONTAL,
                false
            )
        }
    }

    /**
     * Initializes toolbar title, navigation action and menu click listeners.
     *
     * Drag listener and tag are added to be able to receive drag events (related to the trash bin
     * action).
     */
    private fun initToolbar() {
        with(binding.toolbar) {
            title = tierList.title
            tag = TrashBinDragData
            setOnDragListener(dragListener)

            setNavigationOnClickListener {
                Timber.i("Back button clicked")
                setTierListResultAndFinishActivity()
            }

            setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.item_rename_tier_list) {
                    Timber.i("Rename tier list button clicked")
                    showEnterTierListTitleDialog()
                    true
                } else {
                    false
                }
            }
        }
    }

    /**
     * Initializes bottom bar layout.
     *
     * Initializes [bottomBarBinder] and click listeners of the bottom bar image buttons.
     */
    private fun initBottomBar() {
        with(binding.groupBottomBar) {
            bottomBarBinder = TierListBottomBarBinder(this, tierList)
            btnZoomIn.setOnClickListener {
                Timber.i("Zoom in button clicked")
                viewModel.zoomIn()
            }
            btnZoomOut.setOnClickListener {
                Timber.i("Zoom out button clicked")
                viewModel.zoomOut()
            }
            btnAddNewTier.setOnClickListener {
                Timber.i("Add new tier button clicked")
                viewModel.addNewTier()
            }
            btnAddNewImage.setOnThrottledClickListener {
                Timber.i("Add new image button clicked")
                launchImagePicker()
            }
            btnView.setOnThrottledClickListener {
                Timber.i("View button clicked")
                viewModel.viewTierList()
            }
            btnShare.setOnThrottledClickListener {
                Timber.i("Share button clicked")
                viewModel.shareTierList()
            }
        }
    }

    /**
     * Launches [imagePickerLauncher] to get images from the device.
     *
     * Presents snackbar with error message in case no suitable activities are found.
     */
    private fun launchImagePicker() {
        try {
            Timber.i("Launching image picker")
            imagePickerLauncher.launch(FileManager.MIME_TYPE_IMAGE)
        } catch (e: ActivityNotFoundException) {
            Timber.w(e, "Activity not found, presenting error snackbar")
            presentTierListErrorSnackbar(R.string.snackbar_msg_no_image_picker_apps_found)
        }
    }

    /**
     * Overrides default back pressed listener.
     *
     * Sets activity result and finishes it.
     */
    private fun addOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            setTierListResultAndFinishActivity()
        }
    }

    /**
     * Sets tier list as activity result and finishes it.
     */
    private fun setTierListResultAndFinishActivity() {
        val data = TierListResultContract.newData(tierList)
        requireActivity().run {
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    /**
     * Shows / updates a determinate loading progress as a subtitle with a linear progress indicator
     * on the top bar.
     *
     * @param progress Current loading progress.
     */
    private fun showDeterminateLoadingProgress(progress: LoadingProgress.Determinate) {
        binding.toolbar.subtitle = getString(
            R.string.toolbar_subtitle_determinate_loading,
            progress.currentItem,
            progress.totalItems
        )
        binding.progressLoading.isIndeterminate = false
        binding.progressLoading.show()
        binding.progressLoading.progress = progress.percent
    }

    /**
     * Shows an indeterminate loading progress as a subtitle with a linear progress indicator on the
     * top bar.
     */
    private fun showIndeterminateLoadingProgress() {
        binding.toolbar.subtitle = getString(R.string.toolbar_subtitle_indeterminate_loading)
        binding.progressLoading.isIndeterminate = true
        binding.progressLoading.show()
    }

    /**
     * Hides determinate / indeterminate loading progress on the top bar.
     */
    private fun hideLoadingProgress() {
        binding.toolbar.subtitle = ""
        binding.progressLoading.visibility = View.GONE
        TransitionManager.beginDelayedTransition(binding.root)
    }

    /**
     * Launches intent to share tier list.
     *
     * Presents snackbar with error message in case no suitable activities are found.
     *
     * @param uri URI holding a tier list image file.
     */
    private fun shareTierList(uri: Uri) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = FileManager.MIME_TYPE_IMAGE_JPEG
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        try {
            Timber.i("Launching share tier list intent")
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Timber.w(e, "Activity not found, presenting error snackbar")
            presentTierListErrorSnackbar(R.string.snackbar_msg_no_share_apps_found)
        }
    }

    /**
     * Launches intent to view tier list.
     *
     * Presents snackbar with error message in case no suitable activities are found.
     *
     * @param uri URI holding a tier list image file.
     */
    private fun viewTierList(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, FileManager.MIME_TYPE_IMAGE_JPEG)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        try {
            Timber.i("Launching view tier list intent")
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Timber.w(e, "Activity not found, presenting error snackbar")
            presentTierListErrorSnackbar(R.string.snackbar_msg_no_view_apps_found)
        }
    }

    /**
     * Presents [Snackbar] to inform user that an error has occurred.
     *
     * The snackbar doesn't have any actions.
     *
     * @param errorMessageResId String resource of the error message to show.
     */
    private fun presentTierListErrorSnackbar(@StringRes errorMessageResId: Int) {
        Snackbar
            .make(binding.root, errorMessageResId, Snackbar.LENGTH_LONG)
            .setAnchorView(R.id.group_bottom_bar)
            .show()
    }

    /**
     * Presents [Snackbar] to inform user that image was removed with an action button that allows
     * to restore removed image.
     *
     * The snackbar is assigned to [imageRemovedSnackbar] field and cleared when it's dismissed.
     */
    private fun presentImageRemovedSnackbar() {
        Timber.i("Presenting image removed snackbar")
        imageRemovedSnackbar = Snackbar
            .make(binding.root, R.string.snackbar_msg_image_removed, Snackbar.LENGTH_LONG)
            .setAnchorView(R.id.group_bottom_bar)
            .setAction(R.string.snackbar_action_restore) {
                Timber.i("Restore button clicked")
                viewModel.restoreReleasedImage()
            }
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    imageRemovedSnackbar = null
                }
            })
            .also {
                it.show()
            }
    }

    /**
     * Shows or hides hint (as a toolbar subtitle) about how to remove image.
     *
     * Should be visible when user drags an image on top of toolbar.
     *
     * @param visible Whether remove image hint has to be shown.
     */
    private fun setRemoveImageHintVisible(visible: Boolean) {
        binding.toolbar.subtitle = if (visible) {
            getString(R.string.toolbar_subtitle_release_to_remove_image)
        } else {
            null
        }
    }

    /**
     * Returns the toolbar menu item with a particular identifier.
     *
     * To avoid null pointer errors, make sure that provided ID is guaranteed to exist.
     *
     * @param itemId Identifier of the item to find.
     * @return The menu item object.
     */
    private fun findToolbarMenuItem(@IdRes itemId: Int): MenuItem {
        return binding.toolbar.menu.findItem(itemId)
    }

    /**
     * Shows dialog with input field that asks user to enter new tier list title.
     *
     * Current title is prefilled in input field. On save click updates tier list and toolbar title.
     **/
    private fun showEnterTierListTitleDialog() {
        EnterTierListTitleDialog.Builder()
            .setDialogTitle(R.string.dialog_title_rename_tier_list)
            .setTierListTitle(tierList.title)
            .setOnConfirmListener { title ->
                binding.toolbar.title = title
                viewModel.updateTierListTitle(title)
            }
            .build()
            .show(requireActivity())
    }
}