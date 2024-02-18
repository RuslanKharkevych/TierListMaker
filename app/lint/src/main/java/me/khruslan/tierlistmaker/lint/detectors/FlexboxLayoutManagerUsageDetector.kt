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
import me.khruslan.tierlistmaker.lint.utils.PRIORITY_MAX
import org.jetbrains.uast.UCallExpression

/**
 * Detects usages of the [FlexboxLayoutManager](https://github.com/google/flexbox-layout)
 * constructor and shows a warning with a suggestion to replace it with
 * [FlexLayoutManager](https://github.com/RuslanKharkevych/TierListMaker/blob/master/app/src/main/java/me/khruslan/tierlistmaker/presentation/utils/recyclerview/FlexLayoutManager.kt).
 *
 * The motivation of this detector is to prevent a crash caused by a
 * [bug](https://github.com/google/flexbox-layout/issues/568) in FlexboxLayoutManager.
 *
 * @constructor Default constructor. Should not be called from the library code.
 */
class FlexboxLayoutManagerUsageDetector : Detector(), SourceCodeScanner {

    /**
     * Contains [ISSUE] and other constants of this detector.
     */
    companion object Constants {

        /**
         * A fully qualified FlexboxLayoutManager class name.
         */
        private const val FLEXBOX_LAYOUT_MANAGER_CLASS_NAME =
            "com.google.android.flexbox.FlexboxLayoutManager"

        /**
         * A fully qualified FlexLayoutManager class name.
         */
        private const val FLEX_LAYOUT_MANAGER_CLASS_NAME =
            "me.khruslan.tierlistmaker.presentation.utils.recyclerview.FlexLayoutManager"

        /**
         * A path to the FlexLayoutManager.kt file relative to the project directory.
         */
        private const val FLEX_LAYOUT_MANAGER_FILE_PATH =
            "src/main/java/me/khruslan/tierlistmaker/presentation/utils/recyclerview/FlexLayoutManager.kt"

        /**
         * The message of the reported warning.
         */
        private const val REPORT_MESSAGE =
            "`FlexboxLayoutManager` usage is prohibited. Use `FlexLayoutManager`"

        /**
         * The issue reported by [FlexboxLayoutManagerUsageDetector].
         *
         * Reported when FlexboxLayoutManager constructor is found outside of the
         * FlexLayoutManager.kt file. Has maximum priority.
         */
        val ISSUE = Issue.create(
            id = "FlexBoxLayoutManagerUsage",
            briefDescription = "FlexboxLayoutManager usage detected",
            explanation = """
                The problem with `FlexboxLayoutManager` is that it overrides only \
                `generateLayoutParams(Context c, AttributeSet attrs)` but does not override \
                `generateLayoutParams(ViewGroup.LayoutParams lp)`, which may lead to unexpected \
                crashes. `FlexLayoutManager` fixes this problem while retaining the same \
                functionality.
            """,
            category = Category.CORRECTNESS,
            priority = PRIORITY_MAX,
            severity = Severity.ERROR,
            implementation = Implementation(
                FlexboxLayoutManagerUsageDetector::class.java,
                Scope.JAVA_FILE_SCOPE
            )
        )

        /**
         * Quickfix of the [ISSUE].
         *
         * Replaces FlexboxLayoutManager with FlexLayoutManager.
         */
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
     * A set of nodes that are already visited.
     *
     * A workaround to avoid reporting the same issue twice.
     */
    private val visitedNodes = mutableSetOf<UCallExpression>()

    /**
     * A path of the [JavaContext.file] relative to the project directory.
     *
     * @receiver Context from the [visitConstructor] method.
     */
    private val JavaContext.relativeFilePath: String
        get() = file.relativeTo(project.dir).path

    /**
     * Returns FlexboxLayoutManager constructor type.
     *
     * Any AST nodes that match the constructor call will be passed to the [visitConstructor] method
     * for processing.
     *
     * @return A list of applicable fully qualified types.
     */
    override fun getApplicableConstructorTypes(): List<String> {
        return listOf(FLEXBOX_LAYOUT_MANAGER_CLASS_NAME)
    }

    /**
     * Reports the issue if it's applicable to a given AST node.
     *
     * This method is invoked for any constructor calls found that matches any names returned by
     * [getApplicableConstructorTypes]. The issue is not reported if any of the following conditions
     * is true:
     * - The node has already been visited.
     * - The file of the context is FlexLayoutManager.kt.
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