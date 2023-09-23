package me.khruslan.tierlistmaker.lint.tests

import com.android.tools.lint.client.api.LintClient
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import me.khruslan.tierlistmaker.lint.LintIssueRegistry
import me.khruslan.tierlistmaker.lint.detectors.AlertDialogBuilderUsageDetector
import me.khruslan.tierlistmaker.lint.detectors.AlertDialogMissingLogTagDetector
import me.khruslan.tierlistmaker.lint.detectors.FlexboxLayoutManagerUsageDetector
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LintIssueRegistryTest {

    private lateinit var registry: LintIssueRegistry

    private companion object {
        private const val VENDOR_NAME = "TierListMaker"
    }

    @Before
    fun init() {
        LintClient.clientName = LintClient.CLIENT_UNIT_TESTS
        registry = LintIssueRegistry()
    }

    @Test
    fun `Verify current API is used`() {
        val expectedApi = CURRENT_API
        val actualApi = registry.api
        assertEquals(expectedApi, actualApi)
    }

    @Test
    fun `Verify TierListMakerVendor is used`() {
        val expectedVendor = Vendor(VENDOR_NAME)
        val actualVendor = registry.vendor
        assertEquals(expectedVendor, actualVendor)
    }

    @Test
    fun `Verify all issues are enabled`() {
        val expectedIssues = listOf(
            AlertDialogBuilderUsageDetector.ISSUE,
            AlertDialogMissingLogTagDetector.ISSUE,
            FlexboxLayoutManagerUsageDetector.ISSUE
        )
        val actualIssues = registry.issues
        assertEquals(expectedIssues, actualIssues)
    }
}