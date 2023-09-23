package me.khruslan.tierlistmaker.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import me.khruslan.tierlistmaker.lint.detectors.AlertDialogBuilderUsageDetector
import me.khruslan.tierlistmaker.lint.detectors.FlexboxLayoutManagerUsageDetector
import me.khruslan.tierlistmaker.lint.detectors.AlertDialogMissingLogTagDetector

/**
 * Registry of the custom lint checks.
 */
class LintIssueRegistry : IssueRegistry() {

    /**
     * Companion object of the [LintIssueRegistry] used for storing vendor name.
     */
    private companion object {
        private const val VENDOR_NAME = "TierListMaker"
    }

    override val api = CURRENT_API
    override val vendor = Vendor(VENDOR_NAME)

    override val issues = listOf(
        AlertDialogBuilderUsageDetector.ISSUE,
        AlertDialogMissingLogTagDetector.ISSUE,
        FlexboxLayoutManagerUsageDetector.ISSUE
    )
}