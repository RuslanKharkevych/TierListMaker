//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.common](../index.md)/[EnterTierListTitleDialog](index.md)/[handleKeyCode](handle-key-code.md)

# handleKeyCode

private fun [handleKeyCode](handle-key-code.md)(keyCode: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Handles code received from dialog key event.

Dismisses dialog when user clicks on a system back button (it's not automatically dismissed because the dialog is not cancelable on outside touch).

#### Return

True if event was handled, false otherwise.

#### Parameters

| | |
|---|---|
| keyCode | The code of the physical key that was pressed. |
