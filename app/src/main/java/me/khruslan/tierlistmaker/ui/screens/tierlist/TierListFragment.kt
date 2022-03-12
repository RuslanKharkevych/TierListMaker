package me.khruslan.tierlistmaker.ui.screens.tierlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.drag.actions.*
import me.khruslan.tierlistmaker.data.tierlist.Tier
import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.databinding.FragmentTierListBinding
import me.khruslan.tierlistmaker.ui.adapters.TierAdapter
import me.khruslan.tierlistmaker.ui.adapters.TierListImageAdapter
import me.khruslan.tierlistmaker.ui.adapters.reorderable.ReorderableCallback
import me.khruslan.tierlistmaker.utils.BACKLOG_TIER_POSITION
import me.khruslan.tierlistmaker.utils.RandomImageUrl
import me.khruslan.tierlistmaker.utils.drag.TierListDragListener
import me.khruslan.tierlistmaker.utils.extensions.displayWidthPixels
import me.khruslan.tierlistmaker.viewmodels.TierListViewModel
import timber.log.Timber

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
        initToolbar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        viewModel.tierListLiveData.observe(viewLifecycleOwner, tierListObserver)
        viewModel.zoomLiveData.observe(viewLifecycleOwner, zoomObserver)
        viewModel.dragActionLiveData.observe(viewLifecycleOwner, dragActionObserver)
    }

    private val tierListObserver = Observer<TierList> { tierList ->
        val imageSize = calculateImageSize(tierList.zoomValue)
        initTiersAdapter(tiers = tierList.tiers, imageSize = imageSize)
        initBacklogAdapter(imageUrls = tierList.backlogImageUrls, imageSize = imageSize)
    }

    private val zoomObserver = Observer<Int> { zoomValue ->
        val imageSize = calculateImageSize(zoomValue)
        tiersAdapter.updateImageSize(imageSize)
        backlogAdapter.updateImageSize(imageSize)
    }

    private val dragActionObserver = Observer<DragAction> { action ->
        when (action) {
            is HighlightAction -> handleHighlightAction(action)
            is InsertAction -> handleInsertAction(action)
            is RemoveAction -> handleRemoveAction(action)
            is UpdateAction -> handleUpdateAction(action)
        }
    }

    private fun initTiersAdapter(tiers: List<Tier>, imageSize: Int) {
        tiersAdapter = TierAdapter(
            tiers = tiers,
            dragListener = dragListener,
            imageSize = imageSize
        )

        with(binding.listTiers) {
            itemAnimator = null
            adapter = tiersAdapter
            layoutManager = LinearLayoutManager(activity)
            ItemTouchHelper(ReorderableCallback(tiersAdapter)).attachToRecyclerView(this)
        }
    }

    private fun initBacklogAdapter(imageUrls: List<String>, imageSize: Int) {
        backlogAdapter = TierListImageAdapter(
            imageUrls = imageUrls,
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
            title = args.title

            setNavigationOnClickListener {
                activity?.finish() ?: run {
                    Timber.e("setNavigationOnClickListener: activity is null")
                }
            }

            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.item_zoom_in -> {
                        viewModel.zoomIn()
                        true
                    }
                    R.id.item_zoom_out -> {
                        viewModel.zoomOut()
                        true
                    }
                    R.id.item_add_image -> {
                        backlogAdapter.insertImage(
                            url = RandomImageUrl.generate(),
                            position = 0
                        )
                        binding.listBacklogImages.scrollToPosition(0)
                        true
                    }
                    else -> super.onOptionsItemSelected(item)
                }
            }
        }
    }

    private fun calculateImageSize(zoomValue: Int) = displayWidthPixels / zoomValue

    private fun handleHighlightAction(action: HighlightAction) = when (action) {
        is HighlightInBacklog -> backlogAdapter.insertImage(
            position = action.itemPosition
        )
        is HighlightInTier -> tiersAdapter.insertImage(
            tierPosition = action.tierPosition,
            itemPosition = action.itemPosition
        )
        is HighlightLastInTier -> tiersAdapter.insertImage(
            tierPosition = action.tierPosition
        )
        is HighlightLastInBacklog -> backlogAdapter.insertImage()
        is HighlightTrashBin -> TODO("Highlight trash bin")
    }

    private fun handleInsertAction(action: InsertAction) = when (action) {
        is InsertToBacklog -> backlogAdapter.insertImage(
            url = action.data.imageUrl,
            position = action.data.itemPosition
        )
        is InsertToTier -> tiersAdapter.insertImage(
            url = action.data.imageUrl,
            tierPosition = action.data.tierPosition,
            itemPosition = action.data.itemPosition
        )
        is InsertToEndOfBacklog -> backlogAdapter.insertImage(action.imageUrl)
        is InsertToEndOfTier -> backlogAdapter.insertImage(
            url = action.imageUrl,
            position = action.tierPosition
        )
        is InsertToTrashBin -> TODO("Remove image")
    }

    private fun handleRemoveAction(action: RemoveAction) = when (action) {
        is RemoveFromBacklog -> backlogAdapter.removeImage(action.itemPosition)
        is RemoveFromTier -> tiersAdapter.removeImage(
            tierPosition = action.tierPosition,
            itemPosition = action.itemPosition
        )
        is RemoveLastFromBacklog -> backlogAdapter.removeLastImage()
        is RemoveLastFromTier -> tiersAdapter.removeLastImage(
            tierPosition = action.tierPosition
        )
        is UnhighlightTrashBin -> TODO("Unhighlight trash bin")
    }

    private fun handleUpdateAction(action: UpdateAction) = when (action) {
        is UpdateInBacklog -> backlogAdapter.updateImage(
            imageUrl = action.data.imageUrl,
            position = action.data.itemPosition
        )
        is UpdateInTier -> tiersAdapter.updateImage(
            imageUrl = action.data.imageUrl,
            tierPosition = action.data.tierPosition,
            itemPosition = action.data.itemPosition
        )
        is UpdateLastInBacklog -> backlogAdapter.updateLastImage(action.imageUrl)
        is UpdateLastInTier -> tiersAdapter.updateLastImage(
            imageUrl = action.imageUrl,
            tierPosition = action.tierPosition
        )
    }
}