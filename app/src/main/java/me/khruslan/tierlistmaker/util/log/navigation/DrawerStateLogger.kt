package me.khruslan.tierlistmaker.util.log.navigation

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import timber.log.Timber

/**
 * Logs drawer state events (opened / closed).
 *
 * To register the logger, use [DrawerLayout.addDrawerListener] method.
 *
 * @constructor Creates a new drawer state logger.
 */
class DrawerStateLogger : DrawerLayout.DrawerListener {

    /**
     * No-op override.
     *
     * Called when a drawer's position changes.
     *
     * @param drawerView The child view that was moved
     * @param slideOffset The new offset of this drawer within its range, from 0-1.
     */
    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

    /**
     * Logs a message indicating that navigation drawer has been opened.
     *
     * Called when a drawer has settled in a completely open state.
     *
     * @param drawerView Drawer view that is now open.
     */
    override fun onDrawerOpened(drawerView: View) {
        Timber.i("Navigation drawer opened")
    }

    /**
     * Logs a message indicating that navigation drawer has been closed.
     *
     * Called when a drawer has settled in a completely closed state.
     *
     * @param drawerView Drawer view that is now closed.
     */
    override fun onDrawerClosed(drawerView: View) {
        Timber.i("Navigation drawer closed")
    }

    /**
     * No-op override.
     *
     * Called when the drawer motion state changes.
     *
     * @param newState The new drawer motion state.
     */
    override fun onDrawerStateChanged(newState: Int) {}
}