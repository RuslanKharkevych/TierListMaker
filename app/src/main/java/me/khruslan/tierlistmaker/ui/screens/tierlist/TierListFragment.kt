package me.khruslan.tierlistmaker.ui.screens.tierlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.data.models.drag.TierDragData
import me.khruslan.tierlistmaker.data.models.tierlist.*
import me.khruslan.tierlistmaker.ui.models.LoadingProgress
import me.khruslan.tierlistmaker.data.models.tierlist.image.Image
import me.khruslan.tierlistmaker.databinding.FragmentTierListBinding
import me.khruslan.tierlistmaker.ui.navigation.TierListResultContract
import me.khruslan.tierlistmaker.data.repositories.file.FileManager
import me.khruslan.tierlistmaker.ui.adapters.TierAdapter
import me.khruslan.tierlistmaker.ui.adapters.TierListImageAdapter
import me.khruslan.tierlistmaker.ui.adapters.reorderable.ReorderableCallback
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION
import me.khruslan.tierlistmaker.utils.drag.TierListDragListener
import me.khruslan.tierlistmaker.utils.extensions.setResultDataAndFinish
import me.khruslan.tierlistmaker.ui.viewmodels.TierListViewModel

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
     * Listener of the tier list drag events.
     */
    private val dragListener = object : TierListDragListener() {
        override fun onDragStarted(dragData: ImageDragData) = viewModel.startDrag(dragData)

        override fun onDragTargetChanged(targetData: DragData?) {
            viewModel.updateDragTarget(targetData)
        }

        override fun onDrop(dragData: ImageDragData) = viewModel.dropImage(dragData)

        override fun onDragEnded() = viewModel.endDrag()
    }

    /**
     * Observer of the item range inserted event for the [tiersAdapter].
     * Performs to scroll to the inserted tier.
     */
    private val tiersDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            binding.listTiers.smoothScrollToPosition(positionStart)
        }
    }

    /**
     * [ActivityResultLauncher] of the [ActivityResultContracts.GetMultipleContents].
     * Allows user to pick one or more images.
     */
    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { imageUris ->
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

    override fun onPause() {
        super.onPause()

        // Save tier list when app goes to background
        if (!requireActivity().isFinishing) {
            viewModel.enqueueSaveTierListWork()
        }
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
     * [Observer] for the loading progress. Shows progress of adding new images
     */
    private val loadingProgressObserver = Observer<LoadingProgress?> { progress ->
        if (progress == null) {
            binding.toolbar.subtitle = ""
            binding.progressLoading.hide()
        } else {
            binding.toolbar.subtitle = getString(
                R.string.toolbar_subtitle_loading,
                progress.currentItem,
                progress.totalItems
            )
            binding.progressLoading.show()
            binding.progressLoading.progress = progress.percent
        }
    }

    /**
     * [Observer] of the tier list events. Notifies adapters about the changes in the tier list
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
            is TrashBinHighlightUpdated -> TODO("Update trash bin UI")
            is ImageRemoved -> TODO("Update the UI")
        }
    }

    /**
     * Initializes toolbar, adapters and loading indicator.
     */
    private fun initView() {
        initToolbar()
        initTierList()
        binding.progressLoading.setVisibilityAfterHide(View.GONE)
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
            imageSize = imageSize
        )
        tiersAdapter.registerAdapterDataObserver(tiersDataObserver)

        with(binding.listTiers) {
            itemAnimator = null
            adapter = tiersAdapter
            layoutManager = LinearLayoutManager(activity)
            ItemTouchHelper(ReorderableCallback(tiersAdapter)).attachToRecyclerView(this)
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
     * Initializes toolbar title, navigation action and menu click listeners.
     */
    private fun initToolbar() {
        with(binding.toolbar) {
            title = args.tierList.title

            setNavigationOnClickListener {
                setTierListResultAndFinishActivity()
            }

            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.item_zoom_in -> { viewModel.zoomIn(); true }
                    R.id.item_zoom_out -> { viewModel.zoomOut(); true }
                    R.id.item_add_image -> { launchImagePicker(); true }
                    R.id.item_add_tier -> { viewModel.addNewTier(); true }
                    // TODO: Migrate to the new API
                    else -> super.onOptionsItemSelected(item)
                }
            }
        }
    }

    /**
     * Launches [imagePickerLauncher] to get images from the device.
     */
    private fun launchImagePicker() {
        imagePickerLauncher.launch(FileManager.MIME_TYPE_IMAGE)
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
        requireActivity().setResultDataAndFinish(data)
    }
}