//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.tierlist](../index.md)/[TierListFragment](index.md)

# TierListFragment

class [TierListFragment](index.md) : [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html)

Fragment that represents a tier list.

It is a start destination for the tier list navigation graph.

## Constructors

| | |
|---|---|
| [TierListFragment](-tier-list-fragment.md) | constructor()<br>Default no-arg constructor. |

## Properties

| Name | Summary |
|---|---|
| [_binding](_binding.md) | private var [_binding](_binding.md): FragmentTierListBinding?<br>Nullable reference of [binding](binding.md). |
| [args](args.md) | private val [args](args.md): TierListFragmentArgs<br>Navigation arguments of the fragment. |
| [autoScrollManager](auto-scroll-manager.md) | private lateinit var [autoScrollManager](auto-scroll-manager.md): [AutoScrollManager](../../me.khruslan.tierlistmaker.presentation.utils.recyclerview.scroll/-auto-scroll-manager/index.md)<br>Manager that performs automatic scrolling withing the tiers recycler view. |
| [backlogAdapter](backlog-adapter.md) | private lateinit var [backlogAdapter](backlog-adapter.md): [TierListImageAdapter](../../me.khruslan.tierlistmaker.presentation.adapters/-tier-list-image-adapter/index.md)<br>Recycler view adapter for the backlog images. |
| [binding](binding.md) | private val [binding](binding.md): FragmentTierListBinding<br>View binding of the fragment. |
| [bottomBarBinder](bottom-bar-binder.md) | private lateinit var [bottomBarBinder](bottom-bar-binder.md): [TierListBottomBarBinder](../../me.khruslan.tierlistmaker.presentation.utils.tierlist/-tier-list-bottom-bar-binder/index.md)<br>Binder that enables / disables buttons in the bottom bar depending on the tier list state. |
| [dragListener](drag-listener.md) | private val [dragListener](drag-listener.md): [TierListDragListener](../../me.khruslan.tierlistmaker.presentation.utils.drag/-tier-list-drag-listener/index.md)<br>Listener of the tier list drag events. |
| [imagePickerLauncher](image-picker-launcher.md) | private val [imagePickerLauncher](image-picker-launcher.md): [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Activity result launcher of the [ActivityResultContracts.GetMultipleContents](https://developer.android.com/reference/kotlin/androidx/activity/result/contract/ActivityResultContracts.GetMultipleContents.html). |
| [imageRemovedSnackbar](image-removed-snackbar.md) | private var [imageRemovedSnackbar](image-removed-snackbar.md): Snackbar?<br>Snackbar that allows to restore removed image. |
| [loadingProgressObserver](loading-progress-observer.md) | private val [loadingProgressObserver](loading-progress-observer.md): [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;[LoadingProgress](../../me.khruslan.tierlistmaker.presentation.models/-loading-progress/index.md)?&gt;<br>Observer of the loading progress. |
| [tierListEventObserver](tier-list-event-observer.md) | private val [tierListEventObserver](tier-list-event-observer.md): [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;[TierListEvent](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list-event/index.md)&gt;<br>Observer of the tier list events. |
| [tiersAdapter](tiers-adapter.md) | private lateinit var [tiersAdapter](tiers-adapter.md): [TierAdapter](../../me.khruslan.tierlistmaker.presentation.adapters/-tier-adapter/index.md)<br>Recycler view adapter for the tiers. |
| [tiersDataObserver](tiers-data-observer.md) | private val [tiersDataObserver](tiers-data-observer.md): [RecyclerView.AdapterDataObserver](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/RecyclerView.AdapterDataObserver.html)<br>Observer of data changes in the [tiersAdapter](tiers-adapter.md). |
| [viewModel](view-model.md) | private val [viewModel](view-model.md): [TierListViewModel](../../me.khruslan.tierlistmaker.presentation.viewmodels/-tier-list-view-model/index.md)<br>View model of the fragment. |

## Functions

| Name | Summary |
|---|---|
| [addOnBackPressedCallback](add-on-back-pressed-callback.md) | private fun [addOnBackPressedCallback](add-on-back-pressed-callback.md)()<br>Overrides default back pressed listener. |
| [findToolbarMenuItem](find-toolbar-menu-item.md) | private fun [findToolbarMenuItem](find-toolbar-menu-item.md)(@[IdRes ](https://developer.android.com/reference/kotlin/androidx/annotation/IdRes.html)itemId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [MenuItem](https://developer.android.com/reference/kotlin/android/view/MenuItem.html)<br>Returns the toolbar menu item with a particular identifier. |
| [hideLoadingProgress](hide-loading-progress.md) | private fun [hideLoadingProgress](hide-loading-progress.md)()<br>Hides determinate / indeterminate loading progress on the top bar. |
| [initBacklogAdapter](init-backlog-adapter.md) | private fun [initBacklogAdapter](init-backlog-adapter.md)(images: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Image](../../me.khruslan.tierlistmaker.data.models.tierlist.image/-image/index.md)&gt;, imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Initializes [backlogAdapter](backlog-adapter.md) and attaches it to the recycler view. |
| [initBottomBar](init-bottom-bar.md) | private fun [initBottomBar](init-bottom-bar.md)()<br>Initializes bottom bar layout. |
| [initObservers](init-observers.md) | private fun [initObservers](init-observers.md)()<br>Initializes all live data observers. |
| [initTierList](init-tier-list.md) | private fun [initTierList](init-tier-list.md)()<br>Initializes [tiersAdapter](tiers-adapter.md) and [backlogAdapter](backlog-adapter.md). |
| [initTiersAdapter](init-tiers-adapter.md) | private fun [initTiersAdapter](init-tiers-adapter.md)(tiers: [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[Tier](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier/index.md)&gt;, imageSize: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Initializes [tiersAdapter](tiers-adapter.md) and attaches it to the recycler view. |
| [initToolbar](init-toolbar.md) | private fun [initToolbar](init-toolbar.md)()<br>Initializes toolbar title, navigation action and menu click listeners. |
| [initView](init-view.md) | private fun [initView](init-view.md)()<br>Initializes toolbar, bottom bar, adapters and loading indicator. |
| [launchImagePicker](launch-image-picker.md) | private fun [launchImagePicker](launch-image-picker.md)()<br>Launches [imagePickerLauncher](image-picker-launcher.md) to get images from the device. |
| [onCreate](on-create.md) | open override fun [onCreate](on-create.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)<br>Adds on back pressed callback. |
| [onCreateView](on-create-view.md) | open override fun [onCreateView](on-create-view.md)(inflater: [LayoutInflater](https://developer.android.com/reference/kotlin/android/view/LayoutInflater.html), container: [ViewGroup](https://developer.android.com/reference/kotlin/android/view/ViewGroup.html)?, savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?): [View](https://developer.android.com/reference/kotlin/android/view/View.html)<br>Inflates [binding](binding.md) and returns its root. |
| [onDestroyView](on-destroy-view.md) | open override fun [onDestroyView](on-destroy-view.md)()<br>Unregisters [tiersDataObserver](tiers-data-observer.md) and deinitializes [binding](binding.md). |
| [onViewCreated](on-view-created.md) | open override fun [onViewCreated](on-view-created.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html), savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)<br>Initializes view and live data observers. |
| [presentImageRemovedSnackbar](present-image-removed-snackbar.md) | private fun [presentImageRemovedSnackbar](present-image-removed-snackbar.md)()<br>Presents Snackbar to inform user that image was removed with an action button that allows to restore removed image. |
| [presentTierListErrorSnackbar](present-tier-list-error-snackbar.md) | private fun [presentTierListErrorSnackbar](present-tier-list-error-snackbar.md)(@[StringRes ](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)errorMessageResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Presents Snackbar to inform user that an error has occurred. |
| [setRemoveImageHintVisible](set-remove-image-hint-visible.md) | private fun [setRemoveImageHintVisible](set-remove-image-hint-visible.md)(visible: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Shows or hides hint (as a toolbar subtitle) about how to remove image. |
| [setTierListResultAndFinishActivity](set-tier-list-result-and-finish-activity.md) | private fun [setTierListResultAndFinishActivity](set-tier-list-result-and-finish-activity.md)()<br>Sets tier list as activity result and finishes it. |
| [shareTierList](share-tier-list.md) | private fun [shareTierList](share-tier-list.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html))<br>Launches intent to share tier list. |
| [showDeterminateLoadingProgress](show-determinate-loading-progress.md) | private fun [showDeterminateLoadingProgress](show-determinate-loading-progress.md)(progress: [LoadingProgress.Determinate](../../me.khruslan.tierlistmaker.presentation.models/-loading-progress/-determinate/index.md))<br>Shows / updates a determinate loading progress as a subtitle with a linear progress indicator on the top bar. |
| [showEnterTierListTitleDialog](show-enter-tier-list-title-dialog.md) | private fun [showEnterTierListTitleDialog](show-enter-tier-list-title-dialog.md)()<br>Shows dialog with input field that asks user to enter new tier list title. |
| [showIndeterminateLoadingProgress](show-indeterminate-loading-progress.md) | private fun [showIndeterminateLoadingProgress](show-indeterminate-loading-progress.md)()<br>Shows an indeterminate loading progress as a subtitle with a linear progress indicator on the top bar. |
| [viewTierList](view-tier-list.md) | private fun [viewTierList](view-tier-list.md)(uri: [Uri](https://developer.android.com/reference/kotlin/android/net/Uri.html))<br>Launches intent to view tier list. |
