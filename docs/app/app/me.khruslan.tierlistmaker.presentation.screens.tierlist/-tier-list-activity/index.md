//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.tierlist](../index.md)/[TierListActivity](index.md)

# TierListActivity

class [TierListActivity](index.md) : [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html)

Activity that represents tier list task.

Can be launched from the home task. Hosts fragments from the tier list navigation graph.

## Constructors

| | |
|---|---|
| [TierListActivity](-tier-list-activity.md) | constructor()<br>Default no-arg constructor. |

## Types

| Name | Summary |
|---|---|
| [NavHelpers](-nav-helpers/index.md) | object [NavHelpers](-nav-helpers/index.md)<br>Navigation helpers and constants. |

## Properties

| Name | Summary |
|---|---|
| [analyticsService](analytics-service.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>lateinit var [analyticsService](analytics-service.md): [AnalyticsService](../../me.khruslan.tierlistmaker.util.analytics/-analytics-service/index.md)<br>Service for logging analytic events. |
| [navGraphBundle](nav-graph-bundle.md) | private val [navGraphBundle](nav-graph-bundle.md): [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)<br>Bundle to set in navigation graph. |
| [viewModel](view-model.md) | private val [viewModel](view-model.md): [TierListActivityViewModel](../../me.khruslan.tierlistmaker.presentation.viewmodels/-tier-list-activity-view-model/index.md)<br>View model of the activity. |

## Functions

| Name | Summary |
|---|---|
| [onCreate](on-create.md) | protected open override fun [onCreate](on-create.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)<br>Inflates activity view from XML and configures navigation graph. |
| [onPause](on-pause.md) | protected open override fun [onPause](on-pause.md)()<br>Synchronously saves tier list to the database when app goes to background. |
| [setContentView](set-content-view.md) | private fun [setContentView](set-content-view.md)()<br>Inflates ActivityTierListBinding and sets its root as a content view. |
| [setNavigationGraph](set-navigation-graph.md) | private fun [setNavigationGraph](set-navigation-graph.md)()<br>Initializes navigation graph with [navGraphBundle](nav-graph-bundle.md). |
