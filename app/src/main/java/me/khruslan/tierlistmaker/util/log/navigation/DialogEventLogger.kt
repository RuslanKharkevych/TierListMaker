package me.khruslan.tierlistmaker.util.log.navigation

import android.content.DialogInterface
import android.content.DialogInterface.OnDismissListener
import android.content.DialogInterface.OnShowListener
import androidx.appcompat.app.AlertDialog
import timber.log.Timber

/**
 * Logs dialog events (when dialog is shown or dismissed).
 *
 * Use [setLogTag] function to bind it to the [AlertDialog].
 *
 * @property logTag A name to differentiate the dialog in logs.
 * @constructor Creates a new logger with tag.
 */
private class DialogEventLogger(private val logTag: String) : OnShowListener, OnDismissListener {

    /**
     * Logs a message indicating that dialog has been shown.
     *
     * This method will be invoked when the dialog is shown.
     *
     * @param dialog The dialog that was shown will be passed into the method.
     */
    override fun onShow(dialog: DialogInterface?) {
        Timber.i("$logTag shown")
    }

    /**
     * Logs a message indicating that dialog has been dismissed.
     *
     * This method will be invoked when the dialog is dismissed.
     *
     * @param dialog The dialog that was dismissed will be passed into the method.
     */
    override fun onDismiss(dialog: DialogInterface?) {
        Timber.i("$logTag dismissed")
    }
}

/**
 * Binds [DialogEventLogger] to the alert dialog.
 *
 * @receiver Alert dialog instance.
 * @param tag A name to differentiate the dialog in logs.
 * @return Alert dialog instance to chain more calls.
 */
fun AlertDialog.setLogTag(tag: String): AlertDialog {
    val logger = DialogEventLogger(tag)
    setOnShowListener(logger)
    setOnDismissListener(logger)
    return this
}