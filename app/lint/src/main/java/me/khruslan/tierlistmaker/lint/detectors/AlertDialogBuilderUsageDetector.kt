package me.khruslan.tierlistmaker.lint.detectors

import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.LintFix
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.intellij.psi.PsiMethod
import me.khruslan.tierlistmaker.lint.utils.CATEGORY_STYLING
import me.khruslan.tierlistmaker.lint.utils.PRIORITY_MEDIUM
import org.jetbrains.uast.UCallExpression

/**
 * Detects usages of the **AlertDialog.Builder** constructor and shows a warning with a suggestion
 * to replace it with **MaterialAlertDialogBuilder**.
 */
class AlertDialogBuilderUsageDetector : Detector(), SourceCodeScanner {

    /**
     * Companion object of [AlertDialogBuilderUsageDetector] that contains corresponding [Issue],
     * [LintFix] and other constants.
     */
    companion object {
        private const val ALERT_DIALOG_BUILDER_CLASS_NAME =
            "androidx.appcompat.app.AlertDialog.Builder"
        private const val MATERIAL_ALERT_DIALOG_BUILDER_CLASS_NAME =
            "com.google.android.material.dialog.MaterialAlertDialogBuilder"

        private const val REPORT_MESSAGE =
            "Consider using MaterialAlertDialogBuilder to match material design style"

        val ISSUE = Issue.create(
            id = "AlertDialogBuilderUsage",
            briefDescription = "AlertDialogBuilder usage detected",
            explanation = """
                Consider using MaterialAlertDialogBuilder, which offers the same functionality as
                AlertDialogBuilder but is designed specifically for use with a Material theme.
            """,
            category = CATEGORY_STYLING,
            priority = PRIORITY_MEDIUM,
            severity = Severity.WARNING,
            implementation = Implementation(
                AlertDialogBuilderUsageDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )

        private val QUICKFIX = LintFix.create()
            .name("Replace with MaterialAlertDialogBuilder")
            .replace()
            .pattern("^(.*?)\\(")
            .with(MATERIAL_ALERT_DIALOG_BUILDER_CLASS_NAME)
            .shortenNames()
            .reformat(true)
            .build()
    }

    override fun getApplicableConstructorTypes(): List<String> {
        return listOf(ALERT_DIALOG_BUILDER_CLASS_NAME)
    }

    override fun visitConstructor(
        context: JavaContext,
        node: UCallExpression,
        constructor: PsiMethod
    ) {
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