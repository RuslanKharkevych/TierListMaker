package me.khruslan.tierlistmaker.util.log.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.FragmentNavigator
import timber.log.Timber

/**
 * Logs fragment destination changes.
 *
 * To register the logger, use [NavController.addOnDestinationChangedListener] method.
 *
 * @constructor Creates a new fragment navigation logger.
 */
class FragmentNavigationLogger : NavController.OnDestinationChangedListener {

    /**
     * Returns the class name of the destination without package name.
     *
     * If the destination is not an instance of [FragmentNavigator.Destination], returns null.
     *
     * @receiver Any navigation destination.
     */
    private val NavDestination.simpleClassName: String?
        get() = if (this is FragmentNavigator.Destination) {
            className.substringAfterLast('.')
        } else {
            null
        }

    /**
     * Logs a new navigation destination with arguments.
     *
     * Invoked when the current destination or its arguments changes.
     *
     * @param controller The controller that navigated.
     * @param destination The new destination.
     * @param arguments The arguments passed to the destination.
     */
    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        destination.simpleClassName?.let { className ->
            Timber.i(
                buildString {
                    append("Navigated to $className")
                    if (arguments != null) append(" with arguments: $arguments")
                }
            )
        }
    }
}