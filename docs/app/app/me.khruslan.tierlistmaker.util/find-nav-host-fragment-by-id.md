//[app](../../index.md)/[me.khruslan.tierlistmaker.util](index.md)/[findNavHostFragmentById](find-nav-host-fragment-by-id.md)

# findNavHostFragmentById

fun [FragmentActivity](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentActivity.html).[findNavHostFragmentById](find-nav-host-fragment-by-id.md)(@[IdRes ](https://developer.android.com/reference/kotlin/androidx/annotation/IdRes.html)id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [NavHostFragment](https://developer.android.com/reference/kotlin/androidx/navigation/fragment/NavHostFragment.html)

Finds NavHostFragment by id.

The fragment must be added to the fragment manager, otherwise the function will crash.

#### Receiver

FragmentActivity that hosts NavHostFragment.

#### Return

The found NavHostFragment.

#### Parameters

| | |
|---|---|
| id | Id of the HavHostFragment. |
