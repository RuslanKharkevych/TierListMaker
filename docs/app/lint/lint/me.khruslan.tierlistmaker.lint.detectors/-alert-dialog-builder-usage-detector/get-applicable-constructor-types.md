//[lint](../../../index.md)/[me.khruslan.tierlistmaker.lint.detectors](../index.md)/[AlertDialogBuilderUsageDetector](index.md)/[getApplicableConstructorTypes](get-applicable-constructor-types.md)

# getApplicableConstructorTypes

open override fun [getApplicableConstructorTypes](get-applicable-constructor-types.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;

Returns AlertDialog.Builder constructor type.

Any AST nodes that match the constructor call will be passed to the [visitConstructor](visit-constructor.md) method for processing.

#### Return

A list of applicable fully qualified types.
