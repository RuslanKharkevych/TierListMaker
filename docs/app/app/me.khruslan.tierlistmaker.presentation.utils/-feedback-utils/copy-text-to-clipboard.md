//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](../index.md)/[FeedbackUtils](index.md)/[copyTextToClipboard](copy-text-to-clipboard.md)

# copyTextToClipboard

private fun [copyTextToClipboard](copy-text-to-clipboard.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), text: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Copies text to clipboard.

Starting from [Build.VERSION_CODES.TIRAMISU](https://developer.android.com/reference/kotlin/android/os/Build.VERSION_CODES.html#tiramisu) the system shows a default UI to users when text is copied. On older devices a custom toast is shown.

#### Parameters

| | |
|---|---|
| context | Activity context. |
| text | Text to copy. |
