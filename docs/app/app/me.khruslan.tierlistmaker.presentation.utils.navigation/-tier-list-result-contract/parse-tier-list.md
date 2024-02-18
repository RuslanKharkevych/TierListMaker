//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.navigation](../index.md)/[TierListResultContract](index.md)/[parseTierList](parse-tier-list.md)

# parseTierList

private fun [parseTierList](parse-tier-list.md)(resultCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), intent: [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)?): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)

Returns tier list obtained as an extra from the intent.

#### Return

Parsed tier list.

#### Parameters

| | |
|---|---|
| resultCode | Result code returned by the child activity. |
| intent | An intent that is expected to contain tier list extra. |

#### Throws

| | |
|---|---|
| [TierListResultException](../-tier-list-result-exception/index.md) | In case result code is not successful or intent doesn't contain such extra. |
