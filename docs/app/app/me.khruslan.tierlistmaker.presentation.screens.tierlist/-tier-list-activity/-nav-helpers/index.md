//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.tierlist](../../index.md)/[TierListActivity](../index.md)/[NavHelpers](index.md)

# NavHelpers

object [NavHelpers](index.md)

Navigation helpers and constants.

Used to create the intent for launching [TierListActivity](../index.md).

## Properties

| Name | Summary |
|---|---|
| [EXTRA_HINT_STEP](-e-x-t-r-a_-h-i-n-t_-s-t-e-p.md) | private const val [EXTRA_HINT_STEP](-e-x-t-r-a_-h-i-n-t_-s-t-e-p.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Name of the intent extra for hint step. |
| [EXTRA_TIER_LIST](-e-x-t-r-a_-t-i-e-r_-l-i-s-t.md) | private const val [EXTRA_TIER_LIST](-e-x-t-r-a_-t-i-e-r_-l-i-s-t.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Name of the intent extra for tier list. |
| [KEY_HINT_STEP_NAME](-k-e-y_-h-i-n-t_-s-t-e-p_-n-a-m-e.md) | private const val [KEY_HINT_STEP_NAME](-k-e-y_-h-i-n-t_-s-t-e-p_-n-a-m-e.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Key of the fragment argument for the hint step name. |
| [KEY_TIER_LIST](-k-e-y_-t-i-e-r_-l-i-s-t.md) | private const val [KEY_TIER_LIST](-k-e-y_-t-i-e-r_-l-i-s-t.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Key of the fragment argument for the tier list. |

## Functions

| Name | Summary |
|---|---|
| [newIntent](new-intent.md) | fun [newIntent](new-intent.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), tierList: [TierList](../../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md), hintStep: [TierListHintStep](../../../me.khruslan.tierlistmaker.presentation.utils.hints.tierlist/-tier-list-hint-step/index.md)? = null): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)<br>Creates the intent for launching [TierListActivity](../index.md). |
