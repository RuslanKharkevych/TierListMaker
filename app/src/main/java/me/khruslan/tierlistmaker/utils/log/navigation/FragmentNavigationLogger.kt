package me.khruslan.tierlistmaker.utils.log.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.FragmentNavigator
import timber.log.Timber

/**
 * Logs fragment destination changes.
 */
class FragmentNavigationLogger : NavController.OnDestinationChangedListener {

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

    /**
     * Returns the class name of the destination without package name. If the destination is not an
     * instance of [FragmentNavigator.Destination], returns null.
     * @receiver any navigation destination.
     */
    private val NavDestination.simpleClassName: String?
        get() = if (this is FragmentNavigator.Destination) {
            className.substringAfterLast('.')
        } else {
            null
        }
}