//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.tierlist](../index.md)/[TierListFragment](index.md)/[findToolbarMenuItem](find-toolbar-menu-item.md)

# findToolbarMenuItem

private fun [findToolbarMenuItem](find-toolbar-menu-item.md)(@[IdRes](https://developer.android.com/reference/kotlin/androidx/annotation/IdRes.html)itemId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [MenuItem](https://developer.android.com/reference/kotlin/android/view/MenuItem.html)

Returns the toolbar menu item with a particular identifier.

To avoid null pointer errors, make sure that provided ID is guaranteed to exist.

#### Return

The menu item object.

#### Parameters

| | |
|---|---|
| itemId | Identifier of the item to find. |
