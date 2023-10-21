package me.khruslan.tierlistmaker.ui.screens.common

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
import me.khruslan.tierlistmaker.utils.log.navigation.setLogTag
import timber.log.Timber

/**
 * Dialog with input field that asks user to enter tier list title. Essentially a wrapper on
 * [AlertDialog] with custom view. Use [EnterTierListTitleDialog.Builder] to create a dialog
 * instance.
 *
 * @property params parameters constructed by [EnterTierListTitleDialog.Builder].
 */
class EnterTierListTitleDialog private constructor(private val params: Params) {

    /**
     * Companion object of the [EnterTierListTitleDialog] used for storing constants.
     */
    private companion object {
        private const val LOG_TAG = "EnterTierListTitleDialog"
    }

    /**
     * Wrapped alert dialog instance.
     */
    private lateinit var dialog: AlertDialog

    /**
     * Instance of edit text view. Can be initialized only after [dialog] is shown.
     */
    private var editText: EditText? = null

    /**
     * Current input of [editText]. Returns empty string if edit text wasn't initialized or its
     * text has not been set yet.
     */
    private val editTextInput
        get() = editText?.text?.toString().orEmpty()

    /**
     * Whether tier list name can be saved. In order for an input to be savable, it must not be
     * blank and it must be different from initial tier list name.
     */
    private val isInputSavable
        get() = editTextInput.isNotBlank() && editTextInput != params.tierListTitle

    /**
     * Creates, configures and shows the dialog.
     *
     * @param context activity context.
     */
    fun show(context: Context) {
        createDialog(context)
        prepareWindow()
        dialog.show()
        setupEditText()
    }

    /**
     * Constructs and instantiates [dialog]. Dialog title is obtained from [params].
     *
     * @param context activity context.
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
     * Prepares window before showing the dialog. Aligns dialog to the bottom of the window and
     * makes soft keyboard show up right after the dialog is shown. Logs error if window is **null**.
     */
    private fun prepareWindow() {
        dialog.window?.run {
            setGravity(Gravity.BOTTOM)
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        } ?: logError("window is not accessible")
    }

    /**
     * Configures edit text. Adds text watcher to validate input and editor action listener to
     * handle IME action clicks. Prefills initial tier list name taken from [params] and requests
     * focus.
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
     * Initializes edit text and provides a builder interface to configure it. Logs error if edit
     * text is not found in dialog.
     *
     * @param builder a lambda that allows to configure edit text after it was initialized.
     */
    private fun setupEditText(builder: EditText.() -> Unit) {
        editText = dialog.findViewById(R.id.edit_text)
        editText?.run(builder) ?: logError("editText was not found")
    }

    /**
     * Invokes [OnConfirmListener.onConfirm] callback to notify observers that user has confirmed
     * a new tier list name.
     */
    private fun confirmInput() {
        Timber.i("Confirmed tier list title input: $editTextInput")
        params.onConfirmListener?.onConfirm(editTextInput)
    }

    /**
     * Handles code received from dialog key event. Dismisses dialog when user clicks on system
     * back button (it's not automatically dismissed because it's not cancelable).
     *
     * @param keyCode the code of the physical key that was pressed.
     * @return **true** if event was handled, **false** otherwise.
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
     * @param view view associated with action.
     * @param actionId identifier of the action.
     * @return **true** if action was handled, **false** otherwise.
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
     * Handles "Done" IME action click. If input is savable, dismisses the dialog and confirms
     * input. Otherwise hides the keyboard.
     *
     * @param view view associated with action.
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
     * @param view view attached to the window that is currently accepting input.
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
     * @param message error message.
     */
    private fun logError(message: String) {
        val exception = EditTierListTitleDialogException(message)
        Timber.e(exception, "Error during dialog creation")
    }

    /**
     * Exception that may occur during showing the dialog. Used for logging.
     *
     * @param message error message.
     */
    private class EditTierListTitleDialogException(message: String) : Exception(message)

    /**
     * [EnterTierListTitleDialog] parameters. Configured by [EnterTierListTitleDialog.Builder].
     *
     * @property dialogTitleResId string resource of the dialog title.
     * @property tierListTitle initial tier list title.
     * @property onConfirmListener title confirmation callback.
     */
    private data class Params(
        @StringRes private var dialogTitleResId: Int? = null,
        var tierListTitle: String? = null,
        var onConfirmListener: OnConfirmListener? = null
    ) {

        /**
         * Resolves localized dialog title.
         *
         * @param context activity context needed to resolve string resource.
         * @return dialog title string or **null** if it wasn't set by builder.
         */
        fun getDialogTitle(context: Context): String? {
            return dialogTitleResId?.let { id ->
                context.getString(id)
            }
        }

        /**
         * Setter for [dialogTitleResId].
         *
         * @param titleResId string resource of the dialog title.
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
         * @param title new tier list title.
         */
        fun onConfirm(title: String)
    }

    /**
     * Builder for [EnterTierListTitleDialog]. Dialog title, tier list title and confirm listener
     * can be customized.
     */
    class Builder {

        /**
         * [EnterTierListTitleDialog] parameters. Empty by default.
         */
        private val params = Params()

        /**
         * Set the dialog title to display using the given resource id.
         *
         * @param titleResId string resource of the dialog title.
         * @return builder instance to allow chaining methods.
         */
        fun setDialogTitle(@StringRes titleResId: Int): Builder {
            params.setDialogTitle(titleResId)
            return this
        }

        /**
         * Set the initial tier list title.
         *
         * @param title initial tier list title.
         * @return builder instance to allow chaining methods.
         */
        fun setTierListTitle(title: String): Builder {
            params.tierListTitle = title
            return this
        }

        /**
         * Set a callback to be invoked on tier list title confirmation.
         *
         * @param listener the [OnConfirmListener] to use.
         * @return builder instance to allow chaining methods.
         */
        fun setOnConfirmListener(listener: OnConfirmListener): Builder {
            params.onConfirmListener = listener
            return this
        }

        /**
         * Builds [EnterTierListTitleDialog] with [params].
         *
         * @return created dialog.
         */
        fun build(): EnterTierListTitleDialog {
            return EnterTierListTitleDialog(params)
        }
    }
}