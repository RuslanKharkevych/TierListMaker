//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.home](../index.md)/[CollectionFragment](index.md)

# CollectionFragment

class [CollectionFragment](index.md) : [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html)

Fragment that represents tier list collection screen.

It is a start destination for the home navigation graph.

## Constructors

| | |
|---|---|
| [CollectionFragment](-collection-fragment.md) | constructor()<br>Default no-arg constructor. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Collection fragment constants for internal use. |

## Properties

| Name | Summary |
|---|---|
| [_binding](_binding.md) | private var [_binding](_binding.md): FragmentCollectionBinding?<br>Nullable reference of [binding](binding.md). |
| [activityViewModel](activity-view-model.md) | private val [activityViewModel](activity-view-model.md): [HomeActivityViewModel](../../me.khruslan.tierlistmaker.presentation.viewmodels/-home-activity-view-model/index.md)<br>View model of the hosting activity. |
| [addPreviewObserver](add-preview-observer.md) | private val [addPreviewObserver](add-preview-observer.md): [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;<br>Observer of the added preview event. |
| [binding](binding.md) | private val [binding](binding.md): FragmentCollectionBinding<br>View binding of the fragment. |
| [errorObserver](error-observer.md) | private val [errorObserver](error-observer.md): [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;<br>Observer of error events. |
| [hintObserver](hint-observer.md) | private val [hintObserver](hint-observer.md): [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;[CollectionHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.collection/-collection-hint-step/index.md)&gt;<br>Observer of the hint events. |
| [listStateObserver](list-state-observer.md) | private val [listStateObserver](list-state-observer.md): [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;[ListState](../../me.khruslan.tierlistmaker.presentation.models/-list-state/index.md)&gt;<br>Observer of the state of the loaded list of previews. |
| [previewsAdapter](previews-adapter.md) | private lateinit var [previewsAdapter](previews-adapter.md): [TierListPreviewAdapter](../../me.khruslan.tierlistmaker.presentation.adapters/-tier-list-preview-adapter/index.md)<br>Recycler view adapter for the tier list previews. |
| [tierListCreatedObserver](tier-list-created-observer.md) | private val [tierListCreatedObserver](tier-list-created-observer.md): [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;<br>Observer of the created tier list event. |
| [tierListLauncher](tier-list-launcher.md) | private val [tierListLauncher](tier-list-launcher.md): [ActivityResultLauncher](https://developer.android.com/reference/kotlin/androidx/activity/result/ActivityResultLauncher.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;<br>Activity result launcher of the [TierListResultContract](../../me.khruslan.tierlistmaker.presentation.utils.navigation/-tier-list-result-contract/index.md). |
| [tierListPreviewsObserver](tier-list-previews-observer.md) | private val [tierListPreviewsObserver](tier-list-previews-observer.md): [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;[MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList.Preview](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/-preview/index.md)&gt;&gt;<br>Observer of the tier list previews. |
| [updatePreviewsObserver](update-previews-observer.md) | private val [updatePreviewsObserver](update-previews-observer.md): [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;<br>Observer of the updated preview event. |
| [viewModel](view-model.md) | private val [viewModel](view-model.md): [CollectionViewModel](../../me.khruslan.tierlistmaker.presentation.viewmodels/-collection-view-model/index.md)<br>View model of the fragment. |

## Functions

| Name | Summary |
|---|---|
| [initAddNewListButton](init-add-new-list-button.md) | private fun [initAddNewListButton](init-add-new-list-button.md)()<br>Sets click listener to the &quot;Add new list&quot; button. |
| [initObservers](init-observers.md) | private fun [initObservers](init-observers.md)()<br>Initializes all live data observers. |
| [initReportIssueButton](init-report-issue-button.md) | private fun [initReportIssueButton](init-report-issue-button.md)()<br>Sets click listener to the &quot;Report the issue&quot; button. |
| [onCreateView](on-create-view.md) | open override fun [onCreateView](on-create-view.md)(inflater: [LayoutInflater](https://developer.android.com/reference/kotlin/android/view/LayoutInflater.html), container: [ViewGroup](https://developer.android.com/reference/kotlin/android/view/ViewGroup.html)?, savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?): [View](https://developer.android.com/reference/kotlin/android/view/View.html)<br>Inflates [binding](binding.md) and returns its root. |
| [onDestroyView](on-destroy-view.md) | open override fun [onDestroyView](on-destroy-view.md)()<br>Deinitializes [binding](binding.md). |
| [onViewCreated](on-view-created.md) | open override fun [onViewCreated](on-view-created.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html), savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)<br>Initializes button click listeners and live data observers. |
| [presentErrorSnackbar](present-error-snackbar.md) | private fun [presentErrorSnackbar](present-error-snackbar.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)textResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Shows Snackbar with error message and &quot;Refresh&quot; action. |
| [showEnterTierListTitleDialog](show-enter-tier-list-title-dialog.md) | private fun [showEnterTierListTitleDialog](show-enter-tier-list-title-dialog.md)()<br>Shows dialog with input field that asks user to enter tier list title. |
| [showRemoveTierListConfirmationAlert](show-remove-tier-list-confirmation-alert.md) | private fun [showRemoveTierListConfirmationAlert](show-remove-tier-list-confirmation-alert.md)(tierListIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Shows confirmation alert for removing tier list when preview is swiped. |
