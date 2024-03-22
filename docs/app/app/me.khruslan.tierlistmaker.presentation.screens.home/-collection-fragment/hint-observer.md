//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.home](../index.md)/[CollectionFragment](index.md)/[hintObserver](hint-observer.md)

# hintObserver

private val [hintObserver](hint-observer.md): [Observer](https://developer.android.com/reference/kotlin/androidx/lifecycle/Observer.html)&lt;[HintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.core/-hint-step/index.md)&gt;

Observer of the hint events.

Depending on the hint type, either shows a hint from [CollectionHintGroup](../../me.khruslan.tierlistmaker.presentation.utils.hints.collection/-collection-hint-group/index.md) or navigates to [TierListActivity](../../me.khruslan.tierlistmaker.presentation.screens.tierlist/-tier-list-activity/index.md) to show [TierListHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.tierlist/-tier-list-hint-step/index.md).

#### Throws

| | |
|---|---|
| [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html) | If observed step is neither [CollectionHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.collection/-collection-hint-step/index.md) nor [TierListHintStep](../../me.khruslan.tierlistmaker.presentation.utils.hints.tierlist/-tier-list-hint-step/index.md). |
