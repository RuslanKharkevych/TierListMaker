package me.khruslan.tierlistmaker.util.log.navigation

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import timber.log.Timber

/**
 * Logs drawer state events (opened / closed).
 */
class DrawerStateLogger : DrawerLayout.DrawerListener {
    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

    override fun onDrawerOpened(drawerView: View) {
        Timber.i("Navigation drawer opened")
    }

    override fun onDrawerClosed(drawerView: View) {
        Timber.i("Navigation drawer closed")
    }

    override fun onDrawerStateChanged(newState: Int) {}
}