//[lint](../../../index.md)/[me.khruslan.tierlistmaker.lint.detectors](../index.md)/[AlertDialogMissingLogTagDetector](index.md)/[visitMethodCall](visit-method-call.md)

# visitMethodCall

open override fun [visitMethodCall](visit-method-call.md)(context: JavaContext, node: UCallExpression, method: PsiMethod)

Reports the issue if it's applicable to a given AST node.

This method is invoked for any method calls found that matches any names returned by [getApplicableMethodNames](get-applicable-method-names.md). The issue is reported if both of the following conditions are true:

- Containing class of the method call is either AlertDialog.Builder or MaterialAlertDialogBuilder.
- Data flow doesn't reach the setLogTag function.

#### Parameters

| | |
|---|---|
| context | The context of the lint request. |
| node | The node for the invoked method. |
| method | The method being called. |
