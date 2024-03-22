//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.tierlist](../../index.md)/[TierListActivity](../index.md)/[NavHelpers](index.md)/[newIntent](new-intent.md)

# newIntent

fun [newIntent](new-intent.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), tierList: [TierList](../../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md), hintStep: [TierListHintStep](../../../me.khruslan.tierlistmaker.presentation.utils.hints.tierlist/-tier-list-hint-step/index.md)? = null): [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html)

Creates the intent for launching [TierListActivity](../index.md).

Always prefer this function over manually constructing the intent.

#### Return

Created intent that can be used to start activity.

#### Parameters

| | |
|---|---|
| context | Activity context. |
| tierList | Required tier list argument. |
| hintStep | Optional hint step argument. |
