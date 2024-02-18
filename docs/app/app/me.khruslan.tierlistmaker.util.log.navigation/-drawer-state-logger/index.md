//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.log.navigation](../index.md)/[DrawerStateLogger](index.md)

# DrawerStateLogger

class [DrawerStateLogger](index.md) : [DrawerLayout.DrawerListener](https://developer.android.com/reference/kotlin/androidx/drawerlayout/widget/DrawerLayout.DrawerListener.html)

Logs drawer state events (opened / closed).

To register the logger, use [DrawerLayout.addDrawerListener](https://developer.android.com/reference/kotlin/androidx/drawerlayout/widget/DrawerLayout.html#adddrawerlistener) method.

## Constructors

| | |
|---|---|
| [DrawerStateLogger](-drawer-state-logger.md) | constructor()<br>Creates a new drawer state logger. |

## Functions

| Name | Summary |
|---|---|
| [onDrawerClosed](on-drawer-closed.md) | open override fun [onDrawerClosed](on-drawer-closed.md)(drawerView: [View](https://developer.android.com/reference/kotlin/android/view/View.html))<br>Logs a message indicating that navigation drawer has been closed. |
| [onDrawerOpened](on-drawer-opened.md) | open override fun [onDrawerOpened](on-drawer-opened.md)(drawerView: [View](https://developer.android.com/reference/kotlin/android/view/View.html))<br>Logs a message indicating that navigation drawer has been opened. |
| [onDrawerSlide](on-drawer-slide.md) | open override fun [onDrawerSlide](on-drawer-slide.md)(drawerView: [View](https://developer.android.com/reference/kotlin/android/view/View.html), slideOffset: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html))<br>No-op override. |
| [onDrawerStateChanged](on-drawer-state-changed.md) | open override fun [onDrawerStateChanged](on-drawer-state-changed.md)(newState: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>No-op override. |
