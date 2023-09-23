package me.khruslan.tierlistmaker.lint.detectors

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.LintFix
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.intellij.psi.PsiMethod
import me.khruslan.tierlistmaker.lint.utils.PRIORITY_HIGH
import org.jetbrains.uast.UCallExpression

/**
 * Detects usages of the **FlexboxLayoutManager** constructor and shows a warning with a suggestion
 * to replace it with **FlexLayoutManager**.
 */
class FlexboxLayoutManagerUsageDetector : Detector(), SourceCodeScanner {

    /**
     * A set of nodes that are already visited used to avoid reporting the same issue twice.
     */
    private val visitedNodes = mutableSetOf<UCallExpression>()

    /**
     * Companion object of [FlexboxLayoutManagerUsageDetector] that contains corresponding [Issue],
     * [LintFix] and other constants.
     */
    companion object {
        private const val FLEXBOX_LAYOUT_MANAGER_CLASS_NAME =
            "com.google.android.flexbox.FlexboxLayoutManager"
        private const val FLEX_LAYOUT_MANAGER_CLASS_NAME =
            "me.khruslan.tierlistmaker.utils.view.FlexLayoutManager"
        private const val FLEX_LAYOUT_MANAGER_FILE_PATH =
            "src/main/java/me/khruslan/tierlistmaker/utils/view/FlexLayoutManager.kt"

        private const val REPORT_MESSAGE =
            "FlexboxLayoutManager usage is prohibited. Use FlexLayoutManager"

        val ISSUE = Issue.create(
            id = "FlexBoxLayoutManagerUsage",
            briefDescription = "FlexboxLayoutManager usage detected",
            explanation = """
                The problem with FlexboxLayoutManager is that it overrides only 
                generateLayoutParams(Context c, AttributeSet attrs) but does not override 
                generateLayoutParams(ViewGroup.LayoutParams lp), which may lead to unexpected crashes. 
                FlexLayoutManager fixes this problem while retaining the same functionality.
            """,
            category = Category.CORRECTNESS,
            priority = PRIORITY_HIGH,
            severity = Severity.ERROR,
            implementation = Implementation(
                FlexboxLayoutManagerUsageDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )

        private val QUICKFIX = LintFix.create()
            .name("Replace with FlexLayoutManager")
            .replace()
            .pattern("^(.*?)\\(")
            .with(FLEX_LAYOUT_MANAGER_CLASS_NAME)
            .shortenNames()
            .reformat(true)
            .build()
    }

    /**
     * A path of the [JavaContext.file] relative to the project directory.
     * @receiver context from the [visitConstructor] method.
     */
    private val JavaContext.relativeFilePath: String
        get() = file.relativeTo(project.dir).path

    override fun getApplicableConstructorTypes(): List<String> {
        return listOf(FLEXBOX_LAYOUT_MANAGER_CLASS_NAME)
    }

    override fun visitConstructor(
        context: JavaContext,
        node: UCallExpression,
        constructor: PsiMethod
    ) {
        if (node in visitedNodes) return
        visitedNodes += node
        if (context.relativeFilePath == FLEX_LAYOUT_MANAGER_FILE_PATH) return
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