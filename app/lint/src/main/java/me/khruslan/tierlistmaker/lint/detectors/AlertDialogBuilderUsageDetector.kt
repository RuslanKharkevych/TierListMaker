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
 * Detects usages of the [AlertDialog.Builder](https://developer.android.com/reference/android/app/AlertDialog.Builder)
 * constructor and shows a warning with a suggestion to replace it with
 * [MaterialAlertDialogBuilder](https://developer.android.com/reference/com/google/android/material/dialog/MaterialAlertDialogBuilder).
 *
 * The motivation of this detector is to maintain consistency and to make sure alert dialogs are
 * styled properly.
 *
 * @constructor Default constructor. Should not be called from the library code.
 */
class AlertDialogBuilderUsageDetector : Detector(), SourceCodeScanner {

    /**
     * Contains [ISSUE] and other constants of this detector.
     */
    companion object Constants {

        /**
         * A fully qualified AlertDialog.Builder class name.
         */
        private const val ALERT_DIALOG_BUILDER_CLASS_NAME =
            "androidx.appcompat.app.AlertDialog.Builder"

        /**
         * A fully qualified MaterialAlertDialogBuilder class name.
         */
        private const val MATERIAL_ALERT_DIALOG_BUILDER_CLASS_NAME =
            "com.google.android.material.dialog.MaterialAlertDialogBuilder"

        /**
         * The message of the reported warning.
         */
        private const val REPORT_MESSAGE =
            "Consider using `MaterialAlertDialogBuilder` to match material design style"

        /**
         * The issue reported by [AlertDialogMissingLogTagDetector].
         *
         * Reported when AlertDialog.Builder constructor is found. Has medium priority.
         */
        val ISSUE = Issue.create(
            id = "AlertDialogBuilderUsage",
            briefDescription = "AlertDialogBuilder usage detected",
            explanation = """
                Consider using `MaterialAlertDialogBuilder`, which offers the same functionality \
                as `AlertDialogBuilder` but is designed specifically for use with a Material theme.
            """,
            category = CATEGORY_STYLING,
            priority = PRIORITY_MEDIUM,
            severity = Severity.WARNING,
            implementation = Implementation(
                AlertDialogBuilderUsageDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )

        /**
         * Quickfix of the [ISSUE].
         *
         * Replaces AlertDialog.Builder with MaterialAlertDialogBuilder.
         */
        private val QUICKFIX = LintFix.create()
            .name("Replace with MaterialAlertDialogBuilder")
            .replace()
            .pattern("^(.*?)\\(")
            .with(MATERIAL_ALERT_DIALOG_BUILDER_CLASS_NAME)
            .shortenNames()
            .reformat(true)
            .build()
    }

    /**
     * Returns AlertDialog.Builder constructor type.
     *
     * Any AST nodes that match the constructor call will be passed to the [visitConstructor] method
     * for processing.
     *
     * @return A list of applicable fully qualified types.
     */
    override fun getApplicableConstructorTypes(): List<String> {
        return listOf(ALERT_DIALOG_BUILDER_CLASS_NAME)
    }

    /**
     * Reports the issue applicable to a given AST node.
     *
     * This method is invoked for any constructor calls found that matches any names returned by
     * [getApplicableConstructorTypes].
     *
     * @param context The context of the lint request.
     * @param node The node for the invoked method.
     * @param constructor The called constructor method.
     */
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