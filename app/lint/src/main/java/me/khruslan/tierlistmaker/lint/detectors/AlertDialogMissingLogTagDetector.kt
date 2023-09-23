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
 * Detects **MaterialAlertDialogBuilder** or **AlertDialog.Builder** usages that are created
 * without setting a log tag with a suggestion to add it.
 */
class AlertDialogMissingLogTagDetector : Detector(), SourceCodeScanner {

    /**
     * Companion object of [AlertDialogMissingLogTagDetector] that contains corresponding [Issue],
     * [LintFix] and other constants.
     */
    companion object {
        private const val METHOD_CREATE = "create"
        private const val METHOD_SET_LOG_TAG = "setLogTag"
        private const val METHOD_SET_LOG_TAG_IMPORT =
            "me.khruslan.tierlistmaker.utils.log.setLogTag"
        private val ALERT_DIALOG_BUILDER_CLASS_NAMES = listOf(
            "androidx.appcompat.app.AlertDialog.Builder",
            "com.google.android.material.dialog.MaterialAlertDialogBuilder"
        )

        private const val REPORT_MESSAGE =
            "Consider setting log tag to enable logging dialog events"

        val ISSUE = Issue.create(
            id = "AlertDialogMissingLogTag",
            briefDescription = "AlertDialog created without log tag",
            explanation = """
                For debugging purposes, log tag should be set for each dialog shown in the
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

    override fun getApplicableMethodNames(): List<String> {
        return listOf(METHOD_CREATE)
    }

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