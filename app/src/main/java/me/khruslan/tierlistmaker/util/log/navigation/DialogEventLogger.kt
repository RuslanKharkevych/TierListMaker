package me.khruslan.tierlistmaker.util.log.navigation

import android.content.DialogInterface
import android.content.DialogInterface.OnDismissListener
import android.content.DialogInterface.OnShowListener
import androidx.appcompat.app.AlertDialog
import timber.log.Timber

/**
 * Logs dialog events (when dialog is shown or dismissed). Use [setLogTag] function to bind it to
 * the [AlertDialog].
 *
 * @property logTag a name to differentiate the dialog in logs.
 */
private class DialogEventLogger(private val logTag: String) : OnShowListener, OnDismissListener {

    override fun onShow(dialog: DialogInterface?) {
        Timber.i("$logTag shown")
    }

    override fun onDismiss(dialog: DialogInterface?) {
        Timber.i("$logTag dismissed")
    }
}

/**
 * Binds [DialogEventLogger] to the alert dialog.
 *
 * @receiver alert dialog instance.
 * @param tag a name to differentiate the dialog in logs.
 * @return alert dialog instance to chain more calls.
 */
fun AlertDialog.setLogTag(tag: String): AlertDialog {
    val logger = DialogEventLogger(tag)
    setOnShowListener(logger)
    setOnDismissListener(logger)
    return this
}