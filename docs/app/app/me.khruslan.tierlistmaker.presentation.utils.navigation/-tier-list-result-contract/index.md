//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.navigation](../index.md)/[TierListResultContract](index.md)

# TierListResultContract

class [TierListResultContract](index.md) : [ActivityResultContract](https://developer.android.com/reference/kotlin/androidx/activity/result/contract/ActivityResultContract.html)&lt;[TierListNavArgs](../-tier-list-nav-args/index.md), [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)?&gt; 

Activity result contract used for getting tier list result from an activity.

Receives tier list navigation arguments as an input, which includes a tier list and optionally a hint step. Produces tier list as an output, the same that was provided as an input. Note that output is nullable in case parsing fails due to unexpected error.

## Constructors

| | |
|---|---|
| [TierListResultContract](-tier-list-result-contract.md) | constructor() |

## Types

| Name | Summary |
|---|---|
| [IntentCreator](-intent-creator/index.md) | object [IntentCreator](-intent-creator/index.md)<br>Creator of the data [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html). |

## Functions

| Name | Summary |
|---|---|
| [createIntent](create-intent.md) | open override fun [createIntent](create-intent.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), input: [TierListNavArgs](../-tier-list-nav-args/index.md)): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)<br>Creates an intent that can be used for [Activity.onActivityResult](https://developer.android.com/reference/kotlin/android/app/Activity.html#onactivityresult). |
| [parseResult](parse-result.md) | open override fun [parseResult](parse-result.md)(resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)?<br>Converts result obtained from [Activity.onActivityResult](https://developer.android.com/reference/kotlin/android/app/Activity.html#onactivityresult) to the tier list. |
| [parseTierList](parse-tier-list.md) | private fun [parseTierList](parse-tier-list.md)(resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)<br>Returns tier list obtained as an extra from the intent. |
