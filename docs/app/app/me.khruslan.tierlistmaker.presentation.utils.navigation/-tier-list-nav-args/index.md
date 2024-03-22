//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.navigation](../index.md)/[TierListNavArgs](index.md)

# TierListNavArgs

data class [TierListNavArgs](index.md)(val tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md), val hintStep: [TierListHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.tierlist/-tier-list-hint-step/index.md)?) : [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)

Navigation arguments supplied to the [TierListResultContract](../-tier-list-result-contract/index.md).

## Constructors

| | |
|---|---|
| [TierListNavArgs](-tier-list-nav-args.md) | constructor(tierList: [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md), hintStep: [TierListHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.tierlist/-tier-list-hint-step/index.md)?) |

## Properties

| Name | Summary |
|---|---|
| [hintStep](hint-step.md) | val [hintStep](hint-step.md): [TierListHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.tierlist/-tier-list-hint-step/index.md)?<br>Optional hint step argument. |
| [tierList](tier-list.md) | val [tierList](tier-list.md): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)<br>Required tier list argument. |
