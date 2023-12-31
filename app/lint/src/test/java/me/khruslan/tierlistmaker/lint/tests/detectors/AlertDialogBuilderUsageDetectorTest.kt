package me.khruslan.tierlistmaker.lint.tests.detectors

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import me.khruslan.tierlistmaker.lint.ALERT_DIALOG_STUB
import me.khruslan.tierlistmaker.lint.detectors.AlertDialogBuilderUsageDetector
import org.junit.Test

class AlertDialogBuilderUsageDetectorTest {

    private companion object {
        private val COLLECTION_FRAGMENT = kotlin(
            """
                package me.khruslan.tierlistmaker

                import androidx.appcompat.app.AlertDialog

                class CollectionFragment: Fragment() {
                    private fun showRemoveTierListConfirmationAlert(tierListIndex: Int) {
                        val tierListTitle = viewModel.getTierListByPosition(tierListIndex).title
                        val alertTitle = getString(R.string.remove_tier_list_confirmation_title, tierListTitle)
                
                        AlertDialog.Builder(requireActivity())
                            .setTitle(alertTitle)
                            .setPositiveButton(R.string.btn_yes) { _, _ ->
                                previewsAdapter.removePreview(tierListIndex)
                                viewModel.removeTierList(tierListIndex)
                            }
                            .setNegativeButton(R.string.btn_no) { _, _ ->
                                previewsAdapter.notifyItemChanged(tierListIndex)
                            }
                            .setOnCancelListener {
                                previewsAdapter.notifyItemChanged(tierListIndex)
                            }
                            .create()
                            .setLogTag(REMOVE_TIER_LIST_CONFIRMATION_ALERT_LOG_TAG)
                            .show()
                    }
                }
                """
        ).indented()
    }

    @Test
    fun `Reports AlertDialogBuilderUsage issue`() {
        lint()
            .files(COLLECTION_FRAGMENT, ALERT_DIALOG_STUB)
            .issues(AlertDialogBuilderUsageDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expect(
                """
                    src/me/khruslan/tierlistmaker/CollectionFragment.kt:10: Warning: Consider using MaterialAlertDialogBuilder to match material design style [AlertDialogBuilderUsage]
                            AlertDialog.Builder(requireActivity())
                            ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    0 errors, 1 warnings
                """
            )
    }

    @Test
    fun `Fixes AlertDialogBuilderUsage issue`() {
        lint()
            .files(COLLECTION_FRAGMENT, ALERT_DIALOG_STUB)
            .issues(AlertDialogBuilderUsageDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expectFixDiffs(
                """
                    Fix for src/me/khruslan/tierlistmaker/CollectionFragment.kt line 10: Replace with MaterialAlertDialogBuilder:
                    @@ -10 +10
                    -         AlertDialog.Builder(requireActivity())
                    +         com.google.android.material.dialog.MaterialAlertDialogBuilder(requireActivity())
                """
            )
    }
}