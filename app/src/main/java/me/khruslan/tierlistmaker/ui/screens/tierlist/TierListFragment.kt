package me.khruslan.tierlistmaker.ui.screens.tierlist

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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.drag.DragLocation
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.models.tierlist.BacklogDataChanged
import me.khruslan.tierlistmaker.data.models.tierlist.BacklogImagesAdded
import me.khruslan.tierlistmaker.data.models.tierlist.BacklogItemChanged
import me.khruslan.tierlistmaker.data.models.tierlist.BacklogItemInserted
import me.khruslan.tierlistmaker.data.models.tierlist.ImageRemoved
import me.khruslan.tierlistmaker.data.models.tierlist.ImageSizeUpdated
import me.khruslan.tierlistmaker.data.models.tierlist.Tier
import me.khruslan.tierlistmaker.data.models.tierlist.TierChanged
import me.khruslan.tierlistmaker.data.models.tierlist.TierInserted
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.data.models.tierlist.TierListChanged
import me.khruslan.tierlistmaker.data.models.tierlist.TierListEvent
import me.khruslan.tierlistmaker.data.models.tierlist.TierListExportError
import me.khruslan.tierlistmaker.data.models.tierlist.TierListReadyToShare
import me.khruslan.tierlistmaker.data.models.tierlist.TierListReadyToView
import me.khruslan.tierlistmaker.data.models.tierlist.TrashBinHighlightUpdated
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.data.repositories.file.FileManager
import me.khruslan.tierlistmaker.databinding.FragmentTierListBinding
import me.khruslan.tierlistmaker.ui.adapters.TierAdapter
import me.khruslan.tierlistmaker.ui.adapters.TierListImageAdapter
import me.khruslan.tierlistmaker.ui.models.LoadingProgress
import me.khruslan.tierlistmaker.ui.navigation.TierListResultContract
import me.khruslan.tierlistmaker.ui.screens.common.EnterTierListTitleDialog
import me.khruslan.tierlistmaker.ui.viewmodels.TierListViewModel
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION
import me.khruslan.tierlistmaker.utils.drag.TierListDragListener
import me.khruslan.tierlistmaker.utils.enableReordering
import me.khruslan.tierlistmaker.utils.scroll.AutoScrollManager
import me.khruslan.tierlistmaker.utils.view.TierListBottomBarBinder
import timber.log.Timber

/**
 * [Fragment] that represents a tier list.
 * It is a start destination for the tier list navigation graph.
 */
@AndroidEntryPoint
class TierListFragment : Fragment() {
    private var _binding: FragmentTierListBinding? = null
    private val binding get() = _binding!!
    private val args: TierListFragmentArgs by navArgs()
    private val viewModel: TierListViewModel by viewModels()

    /**
     * [RecyclerView.Adapter] for the tiers.
     */
    private lateinit var tiersAdapter: TierAdapter

    /**
     * [RecyclerView.Adapter] for the backlog images.
     */
    private lateinit var backlogAdapter: TierListImageAdapter

    /**
     * Manager that performs automatic scrolling withing tier list recycler view.
     */
    private lateinit var autoScrollManager: AutoScrollManager

    /**
     * Binder that enables / disables buttons in the bottom bar depending on the tier list state.
     */
    private lateinit var bottomBarBinder: TierListBottomBarBinder

    /**
     * Listener of the tier list drag events.
     */
    private val dragListener = object : TierListDragListener() {
        override fun onDragStarted(dragData: ImageDragData) {
            Timber.i("Invoked onDragStarted event. Drag data: $dragData")
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
            viewModel.restoreReleasedImage()
            autoScrollManager.stopScrolling()
        }

        override fun onIsDraggingChanged(isDragging: Boolean) {
            findToolbarMenuItem(R.id.item_rename_tier_list).isVisible = !isDragging
            findToolbarMenuItem(R.id.item_remove_image).isVisible = isDragging
        }
    }

    /**
     * Observer of data changes in the [tiersAdapter]. Performs scroll to the inserted tier and
     * invalidates bottom bar buttons.
     */
    private val tiersDataObserver = object : RecyclerView.AdapterDataObserver() {
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
     * [ActivityResultLauncher] of the [ActivityResultContracts.GetMultipleContents].
     * Allows user to pick one or more images.
     */
    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { imageUris ->
            Timber.i("Received image picker result. Images: $imageUris")
            viewModel.saveImages(imageUris)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addOnBackPressedCallback()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTierListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        tiersAdapter.unregisterAdapterDataObserver(tiersDataObserver)
        _binding = null
    }

    /**
     * Initializes all [LiveData] observers.
     */
    private fun initObservers() {
        viewModel.tierListEvent.observe(viewLifecycleOwner, tierListEventObserver)
        viewModel.loadingProgressLiveData.observe(viewLifecycleOwner, loadingProgressObserver)
    }

    /**
     * Initializes [tiersAdapter] and [backlogAdapter].
     */
    private fun initTierList() {
        val imageSize = viewModel.imageSize
        initTiersAdapter(tiers = args.tierList.tiers, imageSize = imageSize)
        initBacklogAdapter(images = args.tierList.backlogImages, imageSize = imageSize)
    }

    /**
     * [Observer] for the loading progress. Shows determinate progress of adding new images or
     * indeterminate progress of creating a tier list image file.
     */
    private val loadingProgressObserver = Observer<LoadingProgress?> { progress ->
        when (progress) {
            is LoadingProgress.Determinate -> showDeterminateLoadingProgress(progress)
            LoadingProgress.Indeterminate -> showIndeterminateLoadingProgress()
            null -> hideLoadingProgress()
        }
    }

    /**
     * [Observer] of the tier list events. Notifies adapters about the changes in the tier list.
     */
    private val tierListEventObserver = Observer<TierListEvent> { event ->
        when (event) {
            is BacklogDataChanged -> backlogAdapter.notifyDataSetChanged()
            is BacklogImagesAdded -> {
                backlogAdapter.notifyDataSetChanged()
                binding.listBacklogImages.smoothScrollToPosition(0)
            }
            is BacklogItemChanged -> backlogAdapter.notifyItemChanged(event.itemPosition)
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
            }
            is TierListReadyToShare -> shareTierList(event.uri)
            is TierListReadyToView -> viewTierList(event.uri)
            is TierListExportError -> presentTierListErrorSnackbar(event.errorMessageResId)
        }
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
     * Initializes [tiersAdapter] and attaches it to the [RecyclerView].
     *
     * @param tiers list of the tiers.
     * @param imageSize size of the image.
     */
    private fun initTiersAdapter(tiers: MutableList<Tier>, imageSize: Int) {
        tiersAdapter = TierAdapter(
            tiers = tiers,
            dragListener = dragListener,
            imageSize = imageSize,
            onTierRemoved = { tier ->
                viewModel.insertImagesToBacklog(tier.images)
                viewModel.updateTierStyles()
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
     * Initializes [backlogAdapter] and attaches it to the [RecyclerView].
     *
     * @param images list of backlog images.
     * @param imageSize size of the image.
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
     * Initializes toolbar title, navigation action and menu click listeners. Drag listener and tag
     * are added to be able to receive drag events (related to the trash bin action).
     */
    private fun initToolbar() {
        with(binding.toolbar) {
            title = args.tierList.title
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
     * Initializes [bottomBarBinder] and click listeners of the bottom bar image buttons.
     */
    private fun initBottomBar() {
        with(binding.groupBottomBar) {
            bottomBarBinder = TierListBottomBarBinder(this, viewModel.tierList)
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
            btnAddNewImage.setOnClickListener {
                Timber.i("Add new image button clicked")
                launchImagePicker()
            }
            btnView.setOnClickListener {
                Timber.i("View button clicked")
                viewModel.viewTierList()
            }
            btnShare.setOnClickListener {
                Timber.i("Share button clicked")
                viewModel.shareTierList()
            }
        }
    }

    /**
     * Launches [imagePickerLauncher] to get images from the device. Presents snackbar with error
     * message in case no suitable activities are found.
     */
    private fun launchImagePicker() {
        try {
            Timber.i("Launching image picker")
            imagePickerLauncher.launch(FileManager.MIME_TYPE_IMAGE)
        } catch (_: ActivityNotFoundException) {
            Timber.i("Activity not found, presenting error snackbar")
            presentTierListErrorSnackbar(R.string.snackbar_msg_no_image_picker_apps_found)
        }
    }

    /**
     * Overrides default back pressed listener. Sets activity result and finishes it.
     */
    private fun addOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            setTierListResultAndFinishActivity()
        }
    }

    /**
     * Sets [TierList] as activity result and finishes it.
     */
    private fun setTierListResultAndFinishActivity() {
        val data = TierListResultContract.newData(viewModel.tierList)
        requireActivity().run {
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    /**
     * Shows / updates a determinate loading progress as a subtitle with a linear progress indicator
     * on the top bar.
     *
     * @param progress current loading progress.
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
     * Launches intent to share tier list. Presents snackbar with error message in case no suitable
     * activities are found.
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
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            presentTierListErrorSnackbar(R.string.snackbar_msg_no_share_apps_found)
        }
    }

    /**
     * Launches intent to view tier list. Presents snackbar with error message in case no suitable
     * activities are found.
     *
     * @param uri URI holding a tier list image file.
     */
    private fun viewTierList(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, FileManager.MIME_TYPE_IMAGE_JPEG)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            presentTierListErrorSnackbar(R.string.snackbar_msg_no_view_apps_found)
        }
    }

    /**
     * Presents [Snackbar] to inform user that an error has occurred.
     *
     * @param errorMessageResId string resource of the error message to show.
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
     */
    private fun presentImageRemovedSnackbar() {
        Timber.i("Presenting image removed snackbar")
        Snackbar
            .make(binding.root, R.string.snackbar_msg_image_removed, Snackbar.LENGTH_LONG)
            .setAnchorView(R.id.group_bottom_bar)
            .setAction(R.string.snackbar_action_restore) {
                Timber.i("Restore button clicked")
                viewModel.restoreReleasedImage()
            }
            .show()
    }

    /**
     * Shows or hides hint (as a toolbar subtitle) about how to remove image. Should be visible
     * when user drags an image on top of toolbar.
     *
     * @param visible whether remove image hint has to be shown.
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
     * @param itemId identifier of the item to find.
     * @return The menu item object.
     */
    private fun findToolbarMenuItem(@IdRes itemId: Int): MenuItem {
        return binding.toolbar.menu.findItem(itemId)
    }

    /**
     * Shows dialog with input field that asks user to enter new tier list title. Current title is
     * prefilled in input field. On save click updates tier list and toolbar title.
     **/
    private fun showEnterTierListTitleDialog() {
        EnterTierListTitleDialog.Builder()
            .setDialogTitle(R.string.dialog_title_rename_tier_list)
            .setTierListTitle(viewModel.tierList.title)
            .setOnConfirmListener { title ->
                binding.toolbar.title = title
                viewModel.tierList.title = title
            }
            .build()
            .show(requireActivity())
    }
}