//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.common](../index.md)/[EnterTierListTitleDialog](index.md)/[handleEditorAction](handle-editor-action.md)

# handleEditorAction

private fun [handleEditorAction](handle-editor-action.md)(view: [TextView](https://developer.android.com/reference/kotlin/android/widget/TextView.html), actionId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Handles editor action if it has [EditorInfo.IME_ACTION_DONE](https://developer.android.com/reference/kotlin/android/view/inputmethod/EditorInfo.html#ime_action_done) identifier.

Does nothing if editor action is different. Though, this is not an expected scenario.

#### Return

True if action was handled, False otherwise.

#### Parameters

| | |
|---|---|
| view | View associated with action. |
| actionId | Identifier of the action. |
