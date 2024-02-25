//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.common](../index.md)/[EnterTierListTitleDialog](index.md)/[setupEditText](setup-edit-text.md)

# setupEditText

private fun [setupEditText](setup-edit-text.md)()

Configures edit text.

Adds text watcher to validate input and editor action listener to handle IME action clicks. Prefills initial tier list name taken from [params](params.md) and requests focus.

<br>
---
<br>

private fun [setupEditText](setup-edit-text.md)(builder: [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Initializes edit text and provides a builder interface to configure it.

Logs error if edit text is not found in dialog.

#### Parameters

| | |
|---|---|
| builder | A lambda that allows to configure edit text after it was initialized. |
