package me.khruslan.tierlistmaker.presentation.screens.common

import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import me.khruslan.tierlistmaker.R
import me.khruslan.tierlistmaker.util.log.navigation.setLogTag
import timber.log.Timber

/**
 * Dialog with input field that asks user to enter tier list title.
 *
 * Essentially a wrapper on [AlertDialog] with custom view. Use [EnterTierListTitleDialog.Builder]
 * to create a dialog instance.
 *
 * @property params Parameters constructed by the builder.
 * @constructor Creates a new dialog instance from the builder params.
 */
class EnterTierListTitleDialog private constructor(private val params: Params) {

    /**
     * Constants for internal usage.
     */
    private companion object Constants {

        /**
         * A name of the dialog in logs.
         */
        private const val LOG_TAG = "EnterTierListTitleDialog"
    }

    /**
     * Wrapped alert dialog instance.
     *
     * Must be initialized before showing the dialog.
     */
    private lateinit var dialog: AlertDialog

    /**
     * Instance of the edit text view.
     *
     * Can be initialized only after [dialog] is shown.
     */
    private var editText: EditText? = null

    /**
     * Current input of [editText].
     *
     * Returns empty string if edit text wasn't initialized or its text has not been set yet.
     */
    private val editTextInput
        get() = editText?.text?.toString().orEmpty()

    /**
     * Whether tier list name can be saved.
     *
     * In order for an input to be savable, it must not be blank and it must be different from
     * initial tier list name.
     */
    private val isInputSavable
        get() = editTextInput.isNotBlank() && editTextInput != params.tierListTitle

    /**
     * Creates, configures and shows the dialog.
     *
     * @param context Activity context.
     */
    fun show(context: Context) {
        createDialog(context)
        prepareWindow()
        dialog.show()
        setupEditText()
    }

    /**
     * Constructs and instantiates [dialog].
     *
     * Dialog title is obtained from [params].
     *
     * @param context Activity context.
     */
    private fun createDialog(context: Context) {
        dialog = MaterialAlertDialogBuilder(context, R.style.ThemeOverlay_AlertDialog_EnterTierList)
            .setTitle(params.getDialogTitle(context))
            .setView(R.layout.view_tier_list_title_input)
            .setPositiveButton(R.string.btn_confirm) { _, _ -> confirmInput() }
            .setNegativeButton(R.string.btn_cancel) { _, _ ->
                Timber.i("Cancelled tier list title input")
            }
            .setOnKeyListener { _, keyCode, _ -> handleKeyCode(keyCode) }
            .setCancelable(false)
            .create()
            .setLogTag(LOG_TAG)
    }

    /**
     * Prepares window before showing the dialog.
     *
     * Aligns dialog to the bottom of the window and makes soft keyboard show up right after the
     * dialog is shown. Logs error if window is null.
     */
    private fun prepareWindow() {
        dialog.window?.run {
            setGravity(Gravity.BOTTOM)
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        } ?: logError("window is not accessible")
    }

    /**
     * Configures edit text.
     *
     * Adds text watcher to validate input and editor action listener to handle IME action clicks.
     * Prefills initial tier list name taken from [params] and requests focus.
     */
    private fun setupEditText() {
        setupEditText {
            doAfterTextChanged { validateInput() }
            setOnEditorActionListener { v, actionId, _ -> handleEditorAction(v, actionId) }
            setText(params.tierListTitle)
            requestFocus()
        }
    }

    /**
     * Initializes edit text and provides a builder interface to configure it.
     *
     * Logs error if edit text is not found in dialog.
     *
     * @param builder A lambda that allows to configure edit text after it was initialized.
     */
    private fun setupEditText(builder: EditText.() -> Unit) {
        editText = dialog.findViewById(R.id.edit_text)
        editText?.run(builder) ?: logError("editText was not found")
    }

    /**
     * Invokes [OnConfirmListener.onConfirm] callback to notify observers that user has confirmed
     * the new tier list name.
     *
     * Does nothing if dialog was built without the confirm listener. Though, this is not an
     * expected scenario.
     */
    private fun confirmInput() {
        Timber.i("Confirmed tier list title input: $editTextInput")
        params.onConfirmListener?.onConfirm(editTextInput)
    }

    /**
     * Handles code received from dialog key event.
     *
     * Dismisses dialog when user clicks on a system back button (it's not automatically dismissed
     * because the dialog is not cancelable on outside touch).
     *
     * @param keyCode The code of the physical key that was pressed.
     * @return True if event was handled, false otherwise.
     */
    private fun handleKeyCode(keyCode: Int): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            dialog.dismiss()
            true
        } else {
            false
        }
    }

    /**
     * Handles editor action if it has [EditorInfo.IME_ACTION_DONE] identifier.
     *
     * Does nothing if editor action is different. Though, this is not an expected scenario.
     *
     * @param view View associated with action.
     * @param actionId Identifier of the action.
     * @return True if action was handled, False otherwise.
     */
    private fun handleEditorAction(view: TextView, actionId: Int): Boolean {
        return if (actionId == EditorInfo.IME_ACTION_DONE) {
            handleDoneAction(view)
            true
        } else {
            false
        }
    }

    /**
     * Handles "Done" IME action click.
     *
     * If input is savable, dismisses the dialog and confirms input. Otherwise hides the keyboard.
     *
     * @param view View associated with action.
     */
    private fun handleDoneAction(view: TextView) {
        if (isInputSavable) {
            dialog.dismiss()
            confirmInput()
        } else {
            hideKeyboard(view)
        }
    }

    /**
     * Requests to hide the soft input window.
     *
     * @param view View attached to the window that is currently accepting input.
     */
    private fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Enables "Confirm" button if input is savable and disables it otherwise.
     */
    private fun validateInput() {
        val positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
        positiveButton.isEnabled = isInputSavable
    }

    /**
     * Logs error that occurred during showing the dialog.
     *
     * @param message Error message.
     */
    private fun logError(message: String) {
        val exception = EditTierListTitleDialogException(message)
        Timber.e(exception, "Error during dialog creation")
    }

    /**
     * Exception for various errors that may occur during showing the dialog.
     *
     * Note that it is never thrown. Used for logging purpose only.
     *
     * @param message Error message.
     * @constructor Creates an exception with error message.
     */
    private class EditTierListTitleDialogException(message: String) : Exception(message)

    /**
     * [EnterTierListTitleDialog] parameters.
     *
     * This class is mutable. Configured by [EnterTierListTitleDialog.Builder].
     *
     * @property dialogTitleResId String resource of the dialog title.
     * @property tierListTitle Initial tier list title.
     * @property onConfirmListener Title confirmation callback.
     * @constructor Creates new params.
     */
    private data class Params(
        @StringRes private var dialogTitleResId: Int? = null,
        var tierListTitle: String? = null,
        var onConfirmListener: OnConfirmListener? = null
    ) {

        /**
         * Resolves localized dialog title.
         *
         * @param context Activity context needed to resolve string resource.
         * @return Dialog title string or null if it wasn't set by builder.
         */
        fun getDialogTitle(context: Context): String? {
            return dialogTitleResId?.let { id ->
                context.getString(id)
            }
        }

        /**
         * Setter of [dialogTitleResId].
         *
         * @param titleResId String resource of the dialog title.
         */
        fun setDialogTitle(@StringRes titleResId: Int) {
            dialogTitleResId = titleResId
        }
    }

    /**
     * Callback to be invoked on tier list title confirmation.
     */
    fun interface OnConfirmListener {

        /**
         * Called when tier list title is confirmed by user.
         *
         * @param title New tier list title.
         */
        fun onConfirm(title: String)
    }

    /**
     * Builder of [EnterTierListTitleDialog].
     *
     * Dialog title, tier list title and confirm listener can be customized.
     *
     * @constructor Creates a new builder with empty params.
     */
    class Builder {

        /**
         * Dialog parameters.
         *
         * Initially all parameters are empty.
         */
        private val params = Params()

        /**
         * Set the dialog title to display using the given resource id.
         *
         * @param titleResId String resource of the dialog title.
         * @return Builder instance to allow chaining methods.
         */
        fun setDialogTitle(@StringRes titleResId: Int): Builder {
            params.setDialogTitle(titleResId)
            return this
        }

        /**
         * Set the initial tier list title.
         *
         * @param title Initial tier list title.
         * @return Builder instance to allow chaining methods.
         */
        fun setTierListTitle(title: String): Builder {
            params.tierListTitle = title
            return this
        }

        /**
         * Set a callback to be invoked on tier list title confirmation.
         *
         * @param listener The listener to use.
         * @return Builder instance to allow chaining methods.
         */
        fun setOnConfirmListener(listener: OnConfirmListener): Builder {
            params.onConfirmListener = listener
            return this
        }

        /**
         * Builds [EnterTierListTitleDialog] with [params].
         *
         * @return Created dialog.
         */
        fun build(): EnterTierListTitleDialog {
            return EnterTierListTitleDialog(params)
        }
    }
}