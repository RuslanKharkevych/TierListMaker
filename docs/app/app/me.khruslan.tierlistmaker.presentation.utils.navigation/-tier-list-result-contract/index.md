//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.navigation](../index.md)/[TierListResultContract](index.md)

# TierListResultContract

class [TierListResultContract](index.md) : [ActivityResultContract](https://developer.android.com/reference/kotlin/androidx/activity/result/contract/ActivityResultContract.html)&lt;[TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md), [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)?&gt; 

Activity result contract used for getting tier list result from an activity.

Tier list is used as both input and output of this contract. No modifications are done to the input. Output is nullable in case parsing fails due to unexpected error.

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
| [createIntent](create-intent.md) | open override fun [createIntent](create-intent.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), input: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)<br>Creates an intent that can be used for [Activity.onActivityResult](https://developer.android.com/reference/kotlin/android/app/Activity.html#onactivityresult). |
| [parseResult](parse-result.md) | open override fun [parseResult](parse-result.md)(resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)?<br>Converts result obtained from [Activity.onActivityResult](https://developer.android.com/reference/kotlin/android/app/Activity.html#onactivityresult) to the tier list. |
| [parseTierList](parse-tier-list.md) | private fun [parseTierList](parse-tier-list.md)(resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)<br>Returns tier list obtained as an extra from the intent. |
