//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[TierListActivityViewModel](index.md)

# TierListActivityViewModel

class [TierListActivityViewModel](index.md)@Injectconstructor(savedStateHandle: [SavedStateHandle](https://developer.android.com/reference/kotlin/androidx/lifecycle/SavedStateHandle.html), val databaseHelper: [DatabaseHelper](../../me.khruslan.tierlistmaker.data.providers.database/-database-helper/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

View model for [TierListActivity](../../me.khruslan.tierlistmaker.presentation.screens.tierlist/-tier-list-activity/index.md).

Obtains tier list from the navigation arguments and saves it in the database on need.

#### Parameters

| | |
|---|---|
| savedStateHandle | Provides navigation arguments. |

## Constructors

| | |
|---|---|
| [TierListActivityViewModel](-tier-list-activity-view-model.md) | @Inject<br>constructor(savedStateHandle: [SavedStateHandle](https://developer.android.com/reference/kotlin/androidx/lifecycle/SavedStateHandle.html), databaseHelper: [DatabaseHelper](../../me.khruslan.tierlistmaker.data.providers.database/-database-helper/index.md))<br>Creates view model with all dependencies. |

## Types

| Name | Summary |
|---|---|
| [NavArgKeys](-nav-arg-keys/index.md) | private object [NavArgKeys](-nav-arg-keys/index.md)<br>Navigation argument keys. |

## Properties

| Name | Summary |
|---|---|
| [databaseHelper](database-helper.md) | private val [databaseHelper](database-helper.md): [DatabaseHelper](../../me.khruslan.tierlistmaker.data.providers.database/-database-helper/index.md)<br>Saves tier list in the database. |
| [tierList](tier-list.md) | private val [tierList](tier-list.md): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)<br>Initial tier list instance obtained from navigation arguments. |

## Functions

| Name | Summary |
|---|---|
| [onCleared](on-cleared.md) | protected open override fun [onCleared](on-cleared.md)()<br>Logs the onCleared lifecycle event. |
| [saveTierList](save-tier-list.md) | fun [saveTierList](save-tier-list.md)()<br>Synchronously persists tier list in the local storage. |
