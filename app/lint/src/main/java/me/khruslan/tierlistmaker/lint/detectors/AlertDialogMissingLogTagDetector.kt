package me.khruslan.tierlistmaker.lint.detectors

import com.android.tools.lint.checks.TargetMethodDataFlowAnalyzer
import com.android.tools.lint.checks.isMissingTarget
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.LintFix
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.intellij.psi.PsiMethod
import me.khruslan.tierlistmaker.lint.utils.CATEGORY_LOGGING
import me.khruslan.tierlistmaker.lint.utils.PRIORITY_MEDIUM
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.getParentOfType

/**
 * Detects [MaterialAlertDialogBuilder](https://developer.android.com/reference/com/google/android/material/dialog/MaterialAlertDialogBuilder)
 * or [AlertDialog.Builder](https://developer.android.com/reference/android/app/AlertDialog.Builder)
 * usages that are created without setting a log tag with a suggestion to add it.
 *
 * The motivation of this detector is to make sure all dialog events are logged.
 *
 * @constructor Default constructor. Should not be called from the library code.
 */
class AlertDialogMissingLogTagDetector : Detector(), SourceCodeScanner {

    /**
     * Contains [ISSUE] and other constants of this detector.
     */
    companion object Constants {

        /**
         * The name of the AlertDialog.create method.
         */
        private const val METHOD_CREATE = "create"

        /**
         * The name of the AlertDialog.setLogTag extension function.
         */
        private const val METHOD_SET_LOG_TAG = "setLogTag"

        /**
         * The import statement of the AlertDialog.setLogTag extension function.
         */
        private const val METHOD_SET_LOG_TAG_IMPORT =
            "me.khruslan.tierlistmaker.utils.log.setLogTag"

        /**
         * Fully qualified class names of AlertDialog.Builder and MaterialAlertDialogBuilder.
         */
        private val ALERT_DIALOG_BUILDER_CLASS_NAMES = listOf(
            "androidx.appcompat.app.AlertDialog.Builder",
            "com.google.android.material.dialog.MaterialAlertDialogBuilder"
        )

        /**
         * The message of the reporting warning.
         */
        private const val REPORT_MESSAGE =
            "Consider setting log tag to enable logging dialog events"

        /**
         * The issue reported by [AlertDialogMissingLogTagDetector].
         *
         * Reported when alert dialog is created but log tag is not set. Has medium priority.
         */
        val ISSUE = Issue.create(
            id = "AlertDialogMissingLogTag",
            briefDescription = "AlertDialog created without log tag",
            explanation = """
                For debugging purposes, log tag should be set for each dialog shown in the \
                application. Once it is set, dialog events will be automatically logged.
            """,
            category = CATEGORY_LOGGING,
            priority = PRIORITY_MEDIUM,
            severity = Severity.WARNING,
            implementation = Implementation(
                AlertDialogMissingLogTagDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )

        /**
         * Quickfix of the [ISSUE].
         *
         * Inserts .setLogTag("") function call after create().
         */
        private val QUICKFIX = LintFix.create()
            .name("Set log tag")
            .replace()
            .pattern("[\\s\\S]*")
            .with("\\k<1>\n.$METHOD_SET_LOG_TAG(\"\")")
            .imports(METHOD_SET_LOG_TAG_IMPORT)
            .select("\\s*\"\"(?!.*\\s*\"\".*$)")
            .reformat(true)
            .build()
    }

    /**
     * Returns AlertDialog.create method name.
     *
     * Any AST nodes that match the method call will be passed to the [visitMethodCall] method for
     * processing.
     *
     * @return A list of applicable method names.
     */
    override fun getApplicableMethodNames(): List<String> {
        return listOf(METHOD_CREATE)
    }

    /**
     * Reports the issue if it's applicable to a given AST node.
     *
     * This method is invoked for any method calls found that matches any names returned by
     * [getApplicableMethodNames]. The issue is reported if both of the following conditions are
     * true:
     * - Containing class of the method call is either AlertDialog.Builder or
     * MaterialAlertDialogBuilder.
     * - Data flow doesn't reach the setLogTag function.
     *
     * @param context The context of the lint request.
     * @param node The node for the invoked method.
     * @param method The method being called.
     */
    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        val containingClass = method.containingClass ?: return
        if (containingClass.qualifiedName !in ALERT_DIALOG_BUILDER_CLASS_NAMES) return
        val uMethod = node.getParentOfType(UMethod::class.java) ?: return
        val analyzer = TargetMethodDataFlowAnalyzer.create(node, METHOD_SET_LOG_TAG, null)
        if (!uMethod.isMissingTarget(analyzer)) return
        val location = context.getLocation(node)

        context.report(
            issue = ISSUE,
            scope = node,
            location = location,
            message = REPORT_MESSAGE,
            quickfixData = QUICKFIX
        )
    }
}