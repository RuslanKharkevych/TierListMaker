//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.navigation](../index.md)/[TierListResultContract](index.md)/[parseResult](parse-result.md)

# parseResult

open override fun [parseResult](parse-result.md)(resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)?

Converts result obtained from [Activity.onActivityResult](https://developer.android.com/reference/kotlin/android/app/Activity.html#onactivityresult) to the tier list.

#### Return

Resolved tier list or null in case of parsing error.

#### Parameters

| | |
|---|---|
| resultCode | The integer result code returned by the child activity through its [Activity.setResult](https://developer.android.com/reference/kotlin/android/app/Activity.html#setresult). |
| intent | An intent, which can return result data to the caller (various data can be attached to intent &quot;extras&quot;). |
