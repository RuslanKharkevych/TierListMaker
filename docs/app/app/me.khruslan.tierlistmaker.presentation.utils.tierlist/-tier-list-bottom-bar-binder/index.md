//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.tierlist](../index.md)/[TierListBottomBarBinder](index.md)

# TierListBottomBarBinder

class [TierListBottomBarBinder](index.md)(val binding: GroupTierListBottomBarBinding, val tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md))

Binder that enables / disables buttons in the bottom bar depending on the tier list state.

## Constructors

| | |
|---|---|
| [TierListBottomBarBinder](-tier-list-bottom-bar-binder.md) | constructor(binding: GroupTierListBottomBarBinding, tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md))<br>Creates a new binder for provided tier list. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Constants for private use. |

## Properties

| Name | Summary |
|---|---|
| [binding](binding.md) | private val [binding](binding.md): GroupTierListBottomBarBinding<br>Bottom bar binding. |
| [resources](resources.md) | private val [resources](resources.md): [Resources](https://developer.android.com/reference/kotlin/android/content/res/Resources.html)<br>Resources associated with the root view. |
| [tierList](tier-list.md) | private val [tierList](tier-list.md): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)<br>Tier list to bind. |

## Functions

| Name | Summary |
|---|---|
| [invalidateAddNewTierButton](invalidate-add-new-tier-button.md) | fun [invalidateAddNewTierButton](invalidate-add-new-tier-button.md)()<br>Disables &quot;Add new tier&quot; button if maximum number of tiers has been reached. |
| [invalidateZoomButtons](invalidate-zoom-buttons.md) | fun [invalidateZoomButtons](invalidate-zoom-buttons.md)()<br>Invalidates zoom buttons. |
| [setIntentButtonsEnabled](set-intent-buttons-enabled.md) | fun [setIntentButtonsEnabled](set-intent-buttons-enabled.md)(enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Requests enabling or disabling of &quot;intent&quot; buttons. |
| [setIntentButtonsEnabledInternal](set-intent-buttons-enabled-internal.md) | private fun [setIntentButtonsEnabledInternal](set-intent-buttons-enabled-internal.md)(enabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Immediately enables / disables &quot;intent&quot; buttons. |
