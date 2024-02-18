//[lint](../../../index.md)/[me.khruslan.tierlistmaker.lint.detectors](../index.md)/[AlertDialogBuilderUsageDetector](index.md)

# AlertDialogBuilderUsageDetector

class [AlertDialogBuilderUsageDetector](index.md) : Detector, SourceCodeScanner

Detects usages of the [AlertDialog.Builder](https://developer.android.com/reference/android/app/AlertDialog.Builder) constructor and shows a warning with a suggestion to replace it with [MaterialAlertDialogBuilder](https://developer.android.com/reference/com/google/android/material/dialog/MaterialAlertDialogBuilder).

The motivation of this detector is to maintain consistency and to make sure alert dialogs are styled properly.

## Constructors

| | |
|---|---|
| [AlertDialogBuilderUsageDetector](-alert-dialog-builder-usage-detector.md) | constructor()<br>Default constructor. Should not be called from the library code. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | object [Constants](-constants/index.md)<br>Contains [ISSUE](-constants/-i-s-s-u-e.md) and other constants of this detector. |

## Functions

| Name | Summary |
|---|---|
| [getApplicableConstructorTypes](get-applicable-constructor-types.md) | open override fun [getApplicableConstructorTypes](get-applicable-constructor-types.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Returns AlertDialog.Builder constructor type. |
| [visitConstructor](visit-constructor.md) | open override fun [visitConstructor](visit-constructor.md)(context: JavaContext, node: UCallExpression, constructor: PsiMethod)<br>Reports the issue applicable to a given AST node. |
