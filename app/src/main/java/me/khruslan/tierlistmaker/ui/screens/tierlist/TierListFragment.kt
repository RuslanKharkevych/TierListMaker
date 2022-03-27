package me.khruslan.tierlistmaker.ui.screens.tierlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.LoadingProgress
import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.tierlist.*
import me.khruslan.tierlistmaker.databinding.FragmentTierListBinding
import me.khruslan.tierlistmaker.navigation.contracts.TierListResultContract
import me.khruslan.tierlistmaker.repository.file.FileManager
import me.khruslan.tierlistmaker.ui.adapters.TierAdapter
import me.khruslan.tierlistmaker.ui.adapters.TierListImageAdapter
import me.khruslan.tierlistmaker.ui.adapters.reorderable.ReorderableCallback
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION
import me.khruslan.tierlistmaker.utils.drag.TierListDragListener
import me.khruslan.tierlistmaker.utils.extensions.setResultDataAndFinish
import me.khruslan.tierlistmaker.viewmodels.TierListViewModel

@AndroidEntryPoint
class TierListFragment : Fragment() {
    private var _binding: FragmentTierListBinding? = null
    private val binding get() = _binding!!
    private val args: TierListFragmentArgs by navArgs()
    private val viewModel: TierListViewModel by viewModels()
    private lateinit var tiersAdapter: TierAdapter
    private lateinit var backlogAdapter: TierListImageAdapter

    private val dragListener = object : TierListDragListener() {
        override fun onDragStarted(dragData: ImageDragData) = viewModel.startDrag(dragData)

        override fun onDragTargetChanged(targetData: DragData?) {
            viewModel.updateDragTarget(targetData)
        }

        override fun onDrop(dragData: ImageDragData) = viewModel.dropImage(dragData)

        override fun onDragEnded() = viewModel.endDrag()
    }

    private val tiersDataObserver = object : RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            binding.listTiers.smoothScrollToPosition(positionStart)
        }
    }

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

        if (!requireActivity().isFinishing) {
            viewModel.enqueueSaveTierListWork()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        tiersAdapter.unregisterAdapterDataObserver(tiersDataObserver)
        _binding = null
    }

    private fun initObservers() {
        viewModel.tierListEventLiveData.observe(viewLifecycleOwner, tierListEventObserver)
        viewModel.loadingProgressLiveData.observe(viewLifecycleOwner, loadingProgressObserver)
    }

    private fun initTierList() {
        val imageSize = viewModel.imageSize
        initTiersAdapter(tiers = args.tierList.tiers, imageSize = imageSize)
        initBacklogAdapter(images = args.tierList.backlogImages, imageSize = imageSize)
    }

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
        }
    }

    private fun initView() {
        initToolbar()
        initTierList()
        binding.progressLoading.setVisibilityAfterHide(View.GONE)
    }

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
                    else -> super.onOptionsItemSelected(item)
                }
            }
        }
    }

    private fun launchImagePicker() = imagePickerLauncher.launch(FileManager.MIME_TYPE_IMAGE)

    private fun addOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            setTierListResultAndFinishActivity()
        }
    }

    private fun setTierListResultAndFinishActivity() {
        val data = TierListResultContract.newData(viewModel.tierList)
        requireActivity().setResultDataAndFinish(data)
    }
}