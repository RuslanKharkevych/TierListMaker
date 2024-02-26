//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.viewmodels](../index.md)/[HomeActivityViewModel](index.md)

# HomeActivityViewModel

class [HomeActivityViewModel](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor(val themeManager: [ThemeManager](../../me.khruslan.tierlistmaker.presentation.utils.theme/-theme-manager/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

View model for [HomeActivity](../../me.khruslan.tierlistmaker.presentation.screens.home/-home-activity/index.md).

Responsible for changing theme and showing hints.

## Constructors

| | |
|---|---|
| [HomeActivityViewModel](-home-activity-view-model.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor(themeManager: [ThemeManager](../../me.khruslan.tierlistmaker.presentation.utils.theme/-theme-manager/index.md))<br>Creates view model with all dependencies. |

## Properties

| Name | Summary |
|---|---|
| [_hintEvent](_hint-event.md) | private val [_hintEvent](_hint-event.md): LiveEvent&lt;[CollectionHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.collection/-collection-hint-step/index.md)&gt;<br>Mutable reference to [hintEvent](hint-event.md). |
| [hintEvent](hint-event.md) | val [hintEvent](hint-event.md): [LiveData](https://developer.android.com/reference/kotlin/androidx/lifecycle/LiveData.html)&lt;[CollectionHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.collection/-collection-hint-step/index.md)&gt;<br>Live data that is updated when the collection hint needs to be shown. |
| [themeManager](theme-manager.md) | private val [themeManager](theme-manager.md): [ThemeManager](../../me.khruslan.tierlistmaker.presentation.utils.theme/-theme-manager/index.md)<br>Toggles application theme. |

## Functions

| Name | Summary |
|---|---|
| [onCleared](on-cleared.md) | protected open override fun [onCleared](on-cleared.md)()<br>Logs the onCleared lifecycle event. |
| [showHint](show-hint.md) | fun [showHint](show-hint.md)(step: [CollectionHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.collection/-collection-hint-step/index.md))<br>Notifies the observers that hint needs to be shown. |
| [toggleTheme](toggle-theme.md) | fun [toggleTheme](toggle-theme.md)()<br>Toggles light/dark theme. |
