package me.khruslan.tierlistmaker.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import me.khruslan.tierlistmaker.lint.detectors.AlertDialogBuilderUsageDetector
import me.khruslan.tierlistmaker.lint.detectors.FlexboxLayoutManagerUsageDetector
import me.khruslan.tierlistmaker.lint.detectors.AlertDialogMissingLogTagDetector

/**
 * Registry of the custom lint checks.
 *
 * Make sure to add a com.android.tools.lint.client.api.IssueRegistry file in resources > META-INF >
 * services. The file should contain a single line that has the name of this class with the full
 * path.
 *
 * @constructor Default constructor. Should not be called from the library code.
 */
class LintIssueRegistry : IssueRegistry() {

    /**
     * Constants for internal usage.
     */
    private companion object Constants {

        /**
         * The vendor for issues returned by lint checks.
         *
         * It is shown after "Vendor:" in output reports.
         */
        private const val VENDOR_NAME = "TierListMaker"
    }

    /**
     * The Lint API version this issue registry's checks were compiled.
     */
    override val api = CURRENT_API

    /**
     * The vendor providing lint checks.
     *
     * Lint will include this in the reports.
     */
    override val vendor = Vendor(VENDOR_NAME)

    /**
     * The list of issues that can be found by all known detectors.
     */
    override val issues = listOf(
        AlertDialogBuilderUsageDetector.ISSUE,
        AlertDialogMissingLogTagDetector.ISSUE,
        FlexboxLayoutManagerUsageDetector.ISSUE
    )
}