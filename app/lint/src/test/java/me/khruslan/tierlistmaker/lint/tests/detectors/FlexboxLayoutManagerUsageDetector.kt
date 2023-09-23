package me.khruslan.tierlistmaker.lint.tests.detectors

import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin
import com.android.tools.lint.checks.infrastructure.TestLintTask
import me.khruslan.tierlistmaker.lint.FLEXBOX_LAYOUT_MANAGER_STUB
import me.khruslan.tierlistmaker.lint.detectors.FlexboxLayoutManagerUsageDetector
import org.junit.Test

class FlexboxLayoutManagerUsageDetector {

    private companion object {
        private val TIER_HOLDER = kotlin(
            """
                package me.khruslan.tierlistmaker

                import com.google.android.flexbox.FlexboxLayoutManager

                class TierHolder {
                    fun bind(tier: Tier, imageSize: Int, tag: TierDragData) {
                        binding.listImages.tag = tag
                
                        binding.textTier.text = tier.style.title
                        binding.textTier.background = ColorDrawable(tier.style.color)
                        binding.textTier.updateLayoutParams { width = imageSize }

                        binding.listImages.minimumHeight = imageSize
                        binding.listImages.adapter = TierListImageAdapter(
                            images = tier.images,
                            tierPosition = adapterPosition,
                            imageSize = imageSize,
                            dragListener = dragListener
                        )
                        binding.listImages.layoutManager = FlexboxLayoutManager(binding.root.context)
                    }
                }
            """
        ).indented()
    }

    @Test
    fun `Reports FlexboxLayoutManagerUsage issue`() {
        TestLintTask.lint()
            .files(TIER_HOLDER, FLEXBOX_LAYOUT_MANAGER_STUB)
            .issues(FlexboxLayoutManagerUsageDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expect(
                """
                    src/me/khruslan/tierlistmaker/TierHolder.kt:20: Error: FlexboxLayoutManager usage is prohibited. Use FlexLayoutManager [FlexBoxLayoutManagerUsage]
                            binding.listImages.layoutManager = FlexboxLayoutManager(binding.root.context)
                                                               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    1 errors, 0 warnings
                """
            )
    }

    @Test
    fun `Fixes FlexboxLayoutManagerUsage issue`() {
        TestLintTask.lint()
            .files(TIER_HOLDER, FLEXBOX_LAYOUT_MANAGER_STUB)
            .issues(FlexboxLayoutManagerUsageDetector.ISSUE)
            .allowMissingSdk()
            .run()
            .expectFixDiffs(
                """
                    Fix for src/me/khruslan/tierlistmaker/TierHolder.kt line 20: Replace with FlexLayoutManager:
                    @@ -20 +20
                    -         binding.listImages.layoutManager = FlexboxLayoutManager(binding.root.context)
                    +         binding.listImages.layoutManager = me.khruslan.tierlistmaker.utils.view.FlexLayoutManager(binding.root.context)
                """
            )
    }
}