//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.navigation](../../index.md)/[TierListResultContract](../index.md)/[IntentCreator](index.md)/[newData](new-data.md)

# newData

fun [newData](new-data.md)(tierList: [TierList](../../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)

Creates data intent.

This data should be passed to [Activity.setResult](https://developer.android.com/reference/kotlin/android/app/Activity.html#setresult) function in the activity that was started with [TierListResultContract](../index.md).

#### Return

Created data intent.

#### Parameters

| | |
|---|---|
| tierList | Tier list passed as activity result. |
