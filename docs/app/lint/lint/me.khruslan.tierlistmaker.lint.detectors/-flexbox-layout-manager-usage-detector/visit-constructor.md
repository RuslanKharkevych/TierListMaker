//[lint](../../../index.md)/[me.khruslan.tierlistmaker.lint.detectors](../index.md)/[FlexboxLayoutManagerUsageDetector](index.md)/[visitConstructor](visit-constructor.md)

# visitConstructor

open override fun [visitConstructor](visit-constructor.md)(context: JavaContext, node: UCallExpression, constructor: PsiMethod)

Reports the issue if it's applicable to a given AST node.

This method is invoked for any constructor calls found that matches any names returned by [getApplicableConstructorTypes](get-applicable-constructor-types.md). The issue is not reported if any of the following conditions is true:

- The node has already been visited.
- The file of the context is FlexLayoutManager.kt.

#### Parameters

| | |
|---|---|
| context | The context of the lint request. |
| node | The node for the invoked method. |
| constructor | The called constructor method. |
