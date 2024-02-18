//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.log.navigation](../index.md)/[DialogEventLogger](index.md)

# DialogEventLogger

private class [DialogEventLogger](index.md)(val logTag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [DialogInterface.OnShowListener](https://developer.android.com/reference/kotlin/android/content/DialogInterface.OnShowListener.html), [DialogInterface.OnDismissListener](https://developer.android.com/reference/kotlin/android/content/DialogInterface.OnDismissListener.html)

Logs dialog events (when dialog is shown or dismissed).

Use [setLogTag](../set-log-tag.md) function to bind it to the [AlertDialog](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AlertDialog.html).

## Constructors

| | |
|---|---|
| [DialogEventLogger](-dialog-event-logger.md) | constructor(logTag: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Creates a new logger with tag. |

## Properties

| Name | Summary |
|---|---|
| [logTag](log-tag.md) | private val [logTag](log-tag.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A name to differentiate the dialog in logs. |

## Functions

| Name | Summary |
|---|---|
| [onDismiss](on-dismiss.md) | open override fun [onDismiss](on-dismiss.md)(dialog: [DialogInterface](https://developer.android.com/reference/kotlin/android/content/DialogInterface.html)?)<br>Logs a message indicating that dialog has been dismissed. |
| [onShow](on-show.md) | open override fun [onShow](on-show.md)(dialog: [DialogInterface](https://developer.android.com/reference/kotlin/android/content/DialogInterface.html)?)<br>Logs a message indicating that dialog has been shown. |
