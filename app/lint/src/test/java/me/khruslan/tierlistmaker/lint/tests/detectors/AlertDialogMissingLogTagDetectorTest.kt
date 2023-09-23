package me.khruslan.tierlistmaker.lint.tests.detectors

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin
import com.android.tools.lint.checks.infrastructure.TestLintTask
import me.khruslan.tierlistmaker.lint.MATERIAL_ALERT_DIALOG_STUB
import me.khruslan.tierlistmaker.lint.detectors.AlertDialogMissingLogTagDetector
import org.junit.Test

class AlertDialogMissingLogTagDetectorTest {

    private companion object {
        private val FEEDBACK_UTILS = kotlin(
            """
                package me.khruslan.tierlistmaker

                import com.google.android.material.dialog.MaterialAlertDialogBuilder

                object FeedbackUtils {
                    private fun showSendUsEmailAlert(context: Context) {
                        MaterialAlertDialogBuilder(context)
                            .setTitle(context.getString(R.string.send_us_email_alert_title, RECIPIENT_EMAIL))
                            .setPositiveButton(R.string.btn_copy_email) { _, _ ->
                                copyTextToClipboard(context, RECIPIENT_EMAIL)
                            }
                            .setNegativeButton(R.string.btn_cancel, null)
                            .create()
                            .show()
                    }
                }
                """
        ).indented()
    }

    @Test
    fun `Reports AlertDialogMissingLogTag issue`() {
        TestLintTask.lint()
            .files(FEEDBACK_UTILS, MATERIAL_ALERT_DIALOG_STUB)
            .issues(AlertDialogMissingLogTagDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expect(
                """
                    src/me/khruslan/tierlistmaker/FeedbackUtils.kt:7: Warning: Consider setting log tag to enable logging dialog events [AlertDialogMissingLogTag]
                            MaterialAlertDialogBuilder(context)
                            ^
                    0 errors, 1 warnings
                """
            )
    }

    @Test
    fun `Fixes AlertDialogMissingLogTag issue`() {
        TestLintTask.lint()
            .files(FEEDBACK_UTILS, MATERIAL_ALERT_DIALOG_STUB)
            .issues(AlertDialogMissingLogTagDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expectFixDiffs(
                """
                    Fix for src/me/khruslan/tierlistmaker/FeedbackUtils.kt line 7: Set log tag:
                    @@ -3 +3
                    + import me.khruslan.tierlistmaker.utils.log.setLogTag
                    @@ -14 +15
                    + .setLogTag([""]|)
                """
            )
    }
}