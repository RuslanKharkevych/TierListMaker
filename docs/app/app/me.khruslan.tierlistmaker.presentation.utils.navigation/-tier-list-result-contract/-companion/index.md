//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.navigation](../../index.md)/[TierListResultContract](../index.md)/[Companion](index.md)

# Companion

object [Companion](index.md)

Companion object used for creating data [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html).

## Properties

| Name | Summary |
|---|---|
| [EXTRA_TIER_LIST](-e-x-t-r-a_-t-i-e-r_-l-i-s-t.md) | private const val [EXTRA_TIER_LIST](-e-x-t-r-a_-t-i-e-r_-l-i-s-t.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [newData](new-data.md) | fun [newData](new-data.md)(tierList: [TierList](../../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)<br>Creates data [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html). This data should be passed to [Activity.setResult](https://developer.android.com/reference/kotlin/android/app/Activity.html#setresult) function in the activity that was started with [TierListResultContract](../index.md). |
