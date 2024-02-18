//[lint](../../../index.md)/[me.khruslan.tierlistmaker.lint.detectors](../index.md)/[AlertDialogMissingLogTagDetector](index.md)

# AlertDialogMissingLogTagDetector

class [AlertDialogMissingLogTagDetector](index.md) : Detector, SourceCodeScanner

Detects [MaterialAlertDialogBuilder](https://developer.android.com/reference/com/google/android/material/dialog/MaterialAlertDialogBuilder) or [AlertDialog.Builder](https://developer.android.com/reference/android/app/AlertDialog.Builder) usages that are created without setting a log tag with a suggestion to add it.

The motivation of this detector is to make sure all dialog events are logged.

## Constructors

| | |
|---|---|
| [AlertDialogMissingLogTagDetector](-alert-dialog-missing-log-tag-detector.md) | constructor()<br>Default constructor. Should not be called from the library code. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | object [Constants](-constants/index.md)<br>Contains [ISSUE](-constants/-i-s-s-u-e.md) and other constants of this detector. |

## Functions

| Name | Summary |
|---|---|
| [getApplicableMethodNames](get-applicable-method-names.md) | open override fun [getApplicableMethodNames](get-applicable-method-names.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Returns AlertDialog.create method name. |
| [visitMethodCall](visit-method-call.md) | open override fun [visitMethodCall](visit-method-call.md)(context: JavaContext, node: UCallExpression, method: PsiMethod)<br>Reports the issue if it's applicable to a given AST node. |
