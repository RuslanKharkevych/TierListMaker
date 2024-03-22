//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[CollectionViewModel](index.md)

# CollectionViewModel

class [CollectionViewModel](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor(application: [Application](https://developer.android.com/reference/kotlin/android/app/Application.html), val databaseHelper: [DatabaseHelper](../../me.khruslan.tierlistmaker.data.providers.database/-database-helper/index.md), val dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), val tierListCreator: [TierListCreator](../../me.khruslan.tierlistmaker.data.providers.tierlist/-tier-list-creator/index.md)) : [AndroidViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/AndroidViewModel.html)

View model for [CollectionFragment](../../me.khruslan.tierlistmaker.presentation.screens.home/-collection-fragment/index.md).

Manages tier list previews.

## Constructors

| | |
|---|---|
| [CollectionViewModel](-collection-view-model.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor(application: [Application](https://developer.android.com/reference/kotlin/android/app/Application.html), databaseHelper: [DatabaseHelper](../../me.khruslan.tierlistmaker.data.providers.database/-database-helper/index.md), dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), tierListCreator: [TierListCreator](../../me.khruslan.tierlistmaker.data.providers.tierlist/-tier-list-creator/index.md))<br>Creates view model with all dependencies. |

## Properties

| Name | Summary |
|---|---|
| [_addPreviewEvent](_add-preview-event.md) | private val [_addPreviewEvent](_add-preview-event.md): LiveEvent&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)&gt;<br>Mutable reference to [addPreviewEvent](add-preview-event.md). |
| [_errorEvent](_error-event.md) | private val [_errorEvent](_error-event.md): LiveEvent&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;<br>Mutable reference to [errorEvent](error-event.md). |
| [_listStateLiveData](_list-state-live-data.md) | private val [_listStateLiveData](_list-state-live-data.md): [MutableLiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)&lt;[ListState](../../me.khruslan.tierlistmaker.presentation.models/-list-state/index.md)&gt;<br>Mutable reference to [listStateLiveData](list-state-live-data.md). |
| [_tierListCreatedEvent](_tier-list-created-event.md) | private val [_tierListCreatedEvent](_tier-list-created-event.md): LiveEvent&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;<br>Mutable reference to [tierListCreatedEvent](tier-list-created-event.md). |
| [_tierListPreviewsLiveData](_tier-list-previews-live-data.md) | private val [_tierListPreviewsLiveData](_tier-list-previews-live-data.md): [MutableLiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/MutableLiveData.html)&lt;[MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList.Preview](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/-preview/index.md)&gt;&gt;<br>Mutable reference to [tierListPreviewsLiveData](tier-list-previews-live-data.md). |
| [_updatePreviewEvent](_update-preview-event.md) | private val [_updatePreviewEvent](_update-preview-event.md): LiveEvent&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;<br>Mutable reference to [updatePreviewEvent](update-preview-event.md). |
| [addPreviewEvent](add-preview-event.md) | val [addPreviewEvent](add-preview-event.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)&gt;<br>Live data that notifies observers about the newly added preview. |
| [databaseHelper](database-helper.md) | private val [databaseHelper](database-helper.md): [DatabaseHelper](../../me.khruslan.tierlistmaker.data.providers.database/-database-helper/index.md)<br>Manages tier lists in the database. |
| [dispatcherProvider](dispatcher-provider.md) | private val [dispatcherProvider](dispatcher-provider.md): [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)<br>Provides default dispatcher. |
| [errorEvent](error-event.md) | val [errorEvent](error-event.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;<br>Live data that notifies observer about errors. |
| [listStateLiveData](list-state-live-data.md) | val [listStateLiveData](list-state-live-data.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[ListState](../../me.khruslan.tierlistmaker.presentation.models/-list-state/index.md)&gt;<br>Live data that notifies observer about the state of the list of previews. |
| [tierListCreatedEvent](tier-list-created-event.md) | val [tierListCreatedEvent](tier-list-created-event.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;<br>Live data that notifies observers that the new tier list has been created. |
| [tierListCreator](tier-list-creator.md) | private val [tierListCreator](tier-list-creator.md): [TierListCreator](../../me.khruslan.tierlistmaker.data.providers.tierlist/-tier-list-creator/index.md)<br>Creates new tier lists. |
| [tierListPreviews](tier-list-previews.md) | private lateinit var [tierListPreviews](tier-list-previews.md): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList.Preview](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/-preview/index.md)&gt;<br>Tier list previews that are displayed on UI. |
| [tierListPreviewsLiveData](tier-list-previews-live-data.md) | val [tierListPreviewsLiveData](tier-list-previews-live-data.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList.Preview](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/-preview/index.md)&gt;&gt;<br>Live data that notifies observers about the tier list previews. |
| [tierLists](tier-lists.md) | private lateinit var [tierLists](tier-lists.md): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;<br>Tier lists that are used for passing to the next screen. |
| [updatePreviewEvent](update-preview-event.md) | val [updatePreviewEvent](update-preview-event.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;<br>Live data that notifies observers about the position of the updated preview. |

## Functions

| Name | Summary |
|---|---|
| [addOrUpdateTierList](add-or-update-tier-list.md) | private fun [addOrUpdateTierList](add-or-update-tier-list.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md))<br>Asynchronously updates tier lists with added or updated tier list. |
| [createNewTierList](create-new-tier-list.md) | fun [createNewTierList](create-new-tier-list.md)(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Asynchronously creates an empty tier list. |
| [getFirstTierListOrNull](get-first-tier-list-or-null.md) | fun [getFirstTierListOrNull](get-first-tier-list-or-null.md)(): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)?<br>Obtains the first tier list if exists. |
| [getTierListAtPosition](get-tier-list-at-position.md) | fun [getTierListAtPosition](get-tier-list-at-position.md)(position: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)<br>Obtains tier list at position. |
| [handleTierListResult](handle-tier-list-result.md) | fun [handleTierListResult](handle-tier-list-result.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)?)<br>Handles tier list that was either added or updated. |
| [loadTierListPreviews](load-tier-list-previews.md) | private fun [loadTierListPreviews](load-tier-list-previews.md)()<br>Loads [tierLists](tier-lists.md) from [databaseHelper](database-helper.md), maps [tierListPreviews](tier-list-previews.md) and updates [tierListPreviewsLiveData](tier-list-previews-live-data.md). |
| [loadTierLists](load-tier-lists.md) | private suspend fun [loadTierLists](load-tier-lists.md)(): [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)&gt;<br>Asynchronously loads tier lists from database and returns the result. |
| [onCleared](on-cleared.md) | protected open override fun [onCleared](on-cleared.md)()<br>Logs the onCleared lifecycle event. |
| [refreshPreviews](refresh-previews.md) | fun [refreshPreviews](refresh-previews.md)()<br>Re-loads tier list previews. |
| [removeTierList](remove-tier-list.md) | fun [removeTierList](remove-tier-list.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Asynchronously removes tier list from the local storage. |
| [saveTierList](save-tier-list.md) | private fun [saveTierList](save-tier-list.md)(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md))<br>Asynchronously saves added or updated tier list in the local storage. |
| [swapTierLists](swap-tier-lists.md) | fun [swapTierLists](swap-tier-lists.md)(firstIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), secondIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Swaps order of two tier lists. |
| [updateTierLists](update-tier-lists.md) | private fun [updateTierLists](update-tier-lists.md)()<br>Asynchronously updates tier lists in the local storage. |
