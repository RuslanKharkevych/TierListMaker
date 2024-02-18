//[lint](../../../index.md)/[me.khruslan.tierlistmaker.lint.detectors](../index.md)/[AlertDialogMissingLogTagDetector](index.md)/[getApplicableMethodNames](get-applicable-method-names.md)

# getApplicableMethodNames

open override fun [getApplicableMethodNames](get-applicable-method-names.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;

Returns AlertDialog.create method name.

Any AST nodes that match the method call will be passed to the [visitMethodCall](visit-method-call.md) method for processing.

#### Return

A list of applicable method names.
