//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.navigation](index.md)

# Package-level declarations

UI utilities for navigation.

## Types

| Name | Summary |
|---|---|
| [TierListNavArgs](-tier-list-nav-args/index.md) | data class [TierListNavArgs](-tier-list-nav-args/index.md)(val tierList: [TierList](../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md), val hintStep: [TierListHintStep](../me.khruslan.tierlistmaker.presentation.utils.hints.tierlist/-tier-list-hint-step/index.md)?) : [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)<br>Navigation arguments supplied to the [TierListResultContract](-tier-list-result-contract/index.md). |
| [TierListResultContract](-tier-list-result-contract/index.md) | class [TierListResultContract](-tier-list-result-contract/index.md) : [ActivityResultContract](https://developer.android.com/reference/kotlin/androidx/activity/result/contract/ActivityResultContract.html)&lt;[TierListNavArgs](-tier-list-nav-args/index.md), [TierList](../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)?&gt; <br>Activity result contract used for getting tier list result from an activity. |
| [TierListResultException](-tier-list-result-exception/index.md) | class [TierListResultException](-tier-list-result-exception/index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)<br>Indicates that tier list navigation result can't be resolved. |
