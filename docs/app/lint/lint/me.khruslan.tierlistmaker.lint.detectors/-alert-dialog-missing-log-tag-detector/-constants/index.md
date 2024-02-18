//[lint](../../../../index.md)/[me.khruslan.tierlistmaker.lint.detectors](../../index.md)/[AlertDialogMissingLogTagDetector](../index.md)/[Constants](index.md)

# Constants

object [Constants](index.md)

Contains [ISSUE](-i-s-s-u-e.md) and other constants of this detector.

## Properties

| Name | Summary |
|---|---|
| [ALERT_DIALOG_BUILDER_CLASS_NAMES](-a-l-e-r-t_-d-i-a-l-o-g_-b-u-i-l-d-e-r_-c-l-a-s-s_-n-a-m-e-s.md) | private val [ALERT_DIALOG_BUILDER_CLASS_NAMES](-a-l-e-r-t_-d-i-a-l-o-g_-b-u-i-l-d-e-r_-c-l-a-s-s_-n-a-m-e-s.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Fully qualified class names of AlertDialog.Builder and MaterialAlertDialogBuilder. |
| [ISSUE](-i-s-s-u-e.md) | val [ISSUE](-i-s-s-u-e.md): Issue<br>The issue reported by [AlertDialogMissingLogTagDetector](../index.md). |
| [METHOD_CREATE](-m-e-t-h-o-d_-c-r-e-a-t-e.md) | private const val [METHOD_CREATE](-m-e-t-h-o-d_-c-r-e-a-t-e.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the AlertDialog.create method. |
| [METHOD_SET_LOG_TAG](-m-e-t-h-o-d_-s-e-t_-l-o-g_-t-a-g.md) | private const val [METHOD_SET_LOG_TAG](-m-e-t-h-o-d_-s-e-t_-l-o-g_-t-a-g.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name of the AlertDialog.setLogTag extension function. |
| [METHOD_SET_LOG_TAG_IMPORT](-m-e-t-h-o-d_-s-e-t_-l-o-g_-t-a-g_-i-m-p-o-r-t.md) | private const val [METHOD_SET_LOG_TAG_IMPORT](-m-e-t-h-o-d_-s-e-t_-l-o-g_-t-a-g_-i-m-p-o-r-t.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The import statement of the AlertDialog.setLogTag extension function. |
| [QUICKFIX](-q-u-i-c-k-f-i-x.md) | private val [QUICKFIX](-q-u-i-c-k-f-i-x.md): LintFix<br>Quickfix of the [ISSUE](-i-s-s-u-e.md). |
| [REPORT_MESSAGE](-r-e-p-o-r-t_-m-e-s-s-a-g-e.md) | private const val [REPORT_MESSAGE](-r-e-p-o-r-t_-m-e-s-s-a-g-e.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The message of the reporting warning. |
