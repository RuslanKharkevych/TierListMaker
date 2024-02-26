//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.common](../index.md)/[EnterTierListTitleDialog](index.md)

# EnterTierListTitleDialog

class [EnterTierListTitleDialog](index.md)(val params: [EnterTierListTitleDialog.Params](-params/index.md))

Dialog with input field that asks user to enter tier list title.

Essentially a wrapper on [AlertDialog](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AlertDialog.html) with custom view. Use [EnterTierListTitleDialog.Builder](-builder/index.md) to create a dialog instance.

## Constructors

| | |
|---|---|
| [EnterTierListTitleDialog](-enter-tier-list-title-dialog.md) | private constructor(params: [EnterTierListTitleDialog.Params](-params/index.md))<br>Creates a new dialog instance from the builder params. |

## Types

| Name | Summary |
|---|---|
| [Builder](-builder/index.md) | class [Builder](-builder/index.md)<br>Builder of [EnterTierListTitleDialog](index.md). |
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Constants for internal usage. |
| [EditTierListTitleDialogException](-edit-tier-list-title-dialog-exception/index.md) | private class [EditTierListTitleDialogException](-edit-tier-list-title-dialog-exception/index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)<br>Exception for various errors that may occur during showing the dialog. |
| [OnConfirmListener](-on-confirm-listener/index.md) | fun interface [OnConfirmListener](-on-confirm-listener/index.md)<br>Callback to be invoked on tier list title confirmation. |
| [Params](-params/index.md) | private data class [Params](-params/index.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html) var dialogTitleResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, var tierListTitle: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, var onConfirmListener: [EnterTierListTitleDialog.OnConfirmListener](-on-confirm-listener/index.md)? = null)<br>[EnterTierListTitleDialog](index.md) parameters. |

## Properties

| Name | Summary |
|---|---|
| [dialog](dialog.md) | private lateinit var [dialog](dialog.md): [AlertDialog](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AlertDialog.html)<br>Wrapped alert dialog instance. |
| [editText](edit-text.md) | private var [editText](edit-text.md): [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html)?<br>Instance of the edit text view. |
| [editTextInput](edit-text-input.md) | private val [editTextInput](edit-text-input.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Current input of [editText](edit-text.md). |
| [isInputSavable](is-input-savable.md) | private val [isInputSavable](is-input-savable.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether tier list name can be saved. |
| [params](params.md) | private val [params](params.md): [EnterTierListTitleDialog.Params](-params/index.md)<br>Parameters constructed by the builder. |

## Functions

| Name | Summary |
|---|---|
| [confirmInput](confirm-input.md) | private fun [confirmInput](confirm-input.md)()<br>Invokes [OnConfirmListener.onConfirm](-on-confirm-listener/on-confirm.md) callback to notify observers that user has confirmed the new tier list name. |
| [createDialog](create-dialog.md) | private fun [createDialog](create-dialog.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Constructs and instantiates [dialog](dialog.md). |
| [handleDoneAction](handle-done-action.md) | private fun [handleDoneAction](handle-done-action.md)(view: [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html))<br>Handles &quot;Done&quot; IME action click. |
| [handleEditorAction](handle-editor-action.md) | private fun [handleEditorAction](handle-editor-action.md)(view: [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html), actionId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Handles editor action if it has [EditorInfo.IME_ACTION_DONE](https://developer.android.com/reference/kotlin/android/view/inputmethod/EditorInfo.html#ime_action_done) identifier. |
| [handleKeyCode](handle-key-code.md) | private fun [handleKeyCode](handle-key-code.md)(keyCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Handles code received from dialog key event. |
| [hideKeyboard](hide-keyboard.md) | private fun [hideKeyboard](hide-keyboard.md)(view: [View](https://developer.android.com/reference/kotlin/android/view/View.html))<br>Requests to hide the soft input window. |
| [logError](log-error.md) | private fun [logError](log-error.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Logs error that occurred during showing the dialog. |
| [prepareWindow](prepare-window.md) | private fun [prepareWindow](prepare-window.md)()<br>Prepares window before showing the dialog. |
| [setupEditText](setup-edit-text.md) | private fun [setupEditText](setup-edit-text.md)()<br>Configures edit text.<br><br>private fun [setupEditText](setup-edit-text.md)(builder: [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Initializes edit text and provides a builder interface to configure it. |
| [show](show.md) | fun [show](show.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Creates, configures and shows the dialog. |
| [validateInput](validate-input.md) | private fun [validateInput](validate-input.md)()<br>Enables &quot;Confirm&quot; button if input is savable and disables it otherwise. |
