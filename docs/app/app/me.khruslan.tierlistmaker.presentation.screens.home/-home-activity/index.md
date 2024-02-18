//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.home](../index.md)/[HomeActivity](index.md)

# HomeActivity

class [HomeActivity](index.md) : [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html)

Activity that represents home task.

It is a launch activity. Displays a splash screen when it's created. Hosts fragments from the home navigation graph.

## Constructors

| | |
|---|---|
| [HomeActivity](-home-activity.md) | constructor()<br>Default no-arg constructor. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | object [Constants](-constants/index.md)<br>Keys for saving and restoring view state and other constants. |

## Properties

| Name | Summary |
|---|---|
| [binding](binding.md) | private lateinit var [binding](binding.md): ActivityHomeBinding<br>View binding of the activity. |
| [viewModel](view-model.md) | private val [viewModel](view-model.md): [HomeActivityViewModel](../../me.khruslan.tierlistmaker.presentation.viewmodels/-home-activity-view-model/index.md)<br>View model of the activity. |

## Functions

| Name | Summary |
|---|---|
| [onCreate](on-create.md) | protected open override fun [onCreate](on-create.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)<br>Called when the activity is starting. |
| [onRestoreInstanceState](on-restore-instance-state.md) | protected open override fun [onRestoreInstanceState](on-restore-instance-state.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html))<br>Restores navigation drawer open/closed state and opens drawer if needed. |
| [onSaveInstanceState](on-save-instance-state.md) | protected open override fun [onSaveInstanceState](on-save-instance-state.md)(outState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html))<br>Saves navigation drawer opened/closed state. |
| [setContentView](set-content-view.md) | private fun [setContentView](set-content-view.md)()<br>Inflates [binding](binding.md) and sets its root as a content view. |
| [setupExitAnimation](setup-exit-animation.md) | private fun [SplashScreen](https://developer.android.com/reference/kotlin/androidx/core/splashscreen/SplashScreen.html).[setupExitAnimation](setup-exit-animation.md)()<br>Initializes and starts circular conceal animation on splash screen exit. |
| [setupNavigation](setup-navigation.md) | private fun [setupNavigation](setup-navigation.md)()<br>Connects app bar and navigation drawer with nav controller. |
| [setupThemeSwitcher](setup-theme-switcher.md) | private fun [setupThemeSwitcher](setup-theme-switcher.md)()<br>Initializes logic for toggling theme when user clicks on &quot;Change theme&quot; image button in the navigation drawer. |
