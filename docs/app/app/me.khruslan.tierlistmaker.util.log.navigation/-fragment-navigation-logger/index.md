//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.log.navigation](../index.md)/[FragmentNavigationLogger](index.md)

# FragmentNavigationLogger

class [FragmentNavigationLogger](index.md) : [NavController.OnDestinationChangedListener](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.OnDestinationChangedListener.html)

Logs fragment destination changes.

To register the logger, use [NavController.addOnDestinationChangedListener](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html#addondestinationchangedlistener) method.

## Constructors

| | |
|---|---|
| [FragmentNavigationLogger](-fragment-navigation-logger.md) | constructor()<br>Creates a new fragment navigation logger. |

## Properties

| Name | Summary |
|---|---|
| [simpleClassName](simple-class-name.md) | private val [NavDestination](https://developer.android.com/reference/kotlin/androidx/navigation/NavDestination.html).[simpleClassName](simple-class-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>Returns the class name of the destination without package name. |

## Functions

| Name | Summary |
|---|---|
| [onDestinationChanged](on-destination-changed.md) | open override fun [onDestinationChanged](on-destination-changed.md)(controller: [NavController](https://developer.android.com/reference/kotlin/androidx/navigation/NavController.html), destination: [NavDestination](https://developer.android.com/reference/kotlin/androidx/navigation/NavDestination.html), arguments: [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)?)<br>Logs a new navigation destination with arguments. |
