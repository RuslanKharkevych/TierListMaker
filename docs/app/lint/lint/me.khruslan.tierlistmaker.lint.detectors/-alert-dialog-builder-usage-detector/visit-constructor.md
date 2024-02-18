//[lint](../../../index.md)/[me.khruslan.tierlistmaker.lint.detectors](../index.md)/[AlertDialogBuilderUsageDetector](index.md)/[visitConstructor](visit-constructor.md)

# visitConstructor

open override fun [visitConstructor](visit-constructor.md)(context: JavaContext, node: UCallExpression, constructor: PsiMethod)

Reports the issue applicable to a given AST node.

This method is invoked for any constructor calls found that matches any names returned by [getApplicableConstructorTypes](get-applicable-constructor-types.md).

#### Parameters

| | |
|---|---|
| context | The context of the lint request. |
| node | The node for the invoked method. |
| constructor | The called constructor method. |
