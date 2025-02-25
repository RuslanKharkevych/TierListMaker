//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.home](../index.md)/[HintsFragment](index.md)

# HintsFragment

class [HintsFragment](index.md) : [PreferenceFragmentCompat](https://developer.android.com/reference/kotlin/androidx/preference/PreferenceFragmentCompat.html)

Preference fragment that represents &quot;Hints&quot; option in the navigation drawer.

Contains &quot;Collection hints&quot; and &quot;Tier list hints&quot; sections.

## Constructors

| | |
|---|---|
| [HintsFragment](-hints-fragment.md) | constructor()<br>Default no-arg constructor. |

## Properties

| Name | Summary |
|---|---|
| [activityViewModel](activity-view-model.md) | private val [activityViewModel](activity-view-model.md): [HomeActivityViewModel](../../me.khruslan.tierlistmaker.presentation.viewmodels/-home-activity-view-model/index.md)<br>View model of the hosting activity. |

## Functions

| Name | Summary |
|---|---|
| [initClickListeners](init-click-listeners.md) | private fun [initClickListeners](init-click-listeners.md)()<br>Initializes click listeners for all hint preferences. |
| [initCollectionHintListeners](init-collection-hint-listeners.md) | private fun [initCollectionHintListeners](init-collection-hint-listeners.md)()<br>Initializes click listeners for collection hints. |
| [initTierListHintListeners](init-tier-list-hint-listeners.md) | private fun [initTierListHintListeners](init-tier-list-hint-listeners.md)()<br>Initializes click listeners for tier list hints. |
| [navigateBackAndShowHint](navigate-back-and-show-hint.md) | private fun [navigateBackAndShowHint](navigate-back-and-show-hint.md)(step: [HintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-step/index.md))<br>Navigates back and notifies [activityViewModel](activity-view-model.md) observers that hint needs to be shown. |
| [onCreatePreferences](on-create-preferences.md) | open override fun [onCreatePreferences](on-create-preferences.md)(savedInstanceState: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?, rootKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?)<br>Inflates preferences XML and initializes click listeners. |
