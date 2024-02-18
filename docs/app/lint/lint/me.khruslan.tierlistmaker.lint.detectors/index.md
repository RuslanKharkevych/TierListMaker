//[lint](../../index.md)/[me.khruslan.tierlistmaker.lint.detectors](index.md)

# Package-level declarations

Custom issue detectors.

## Types

| Name | Summary |
|---|---|
| [AlertDialogBuilderUsageDetector](-alert-dialog-builder-usage-detector/index.md) | class [AlertDialogBuilderUsageDetector](-alert-dialog-builder-usage-detector/index.md) : Detector, SourceCodeScanner<br>Detects usages of the [AlertDialog.Builder](https://developer.android.com/reference/android/app/AlertDialog.Builder) constructor and shows a warning with a suggestion to replace it with [MaterialAlertDialogBuilder](https://developer.android.com/reference/com/google/android/material/dialog/MaterialAlertDialogBuilder). |
| [AlertDialogMissingLogTagDetector](-alert-dialog-missing-log-tag-detector/index.md) | class [AlertDialogMissingLogTagDetector](-alert-dialog-missing-log-tag-detector/index.md) : Detector, SourceCodeScanner<br>Detects [MaterialAlertDialogBuilder](https://developer.android.com/reference/com/google/android/material/dialog/MaterialAlertDialogBuilder) or [AlertDialog.Builder](https://developer.android.com/reference/android/app/AlertDialog.Builder) usages that are created without setting a log tag with a suggestion to add it. |
| [FlexboxLayoutManagerUsageDetector](-flexbox-layout-manager-usage-detector/index.md) | class [FlexboxLayoutManagerUsageDetector](-flexbox-layout-manager-usage-detector/index.md) : Detector, SourceCodeScanner<br>Detects usages of the [FlexboxLayoutManager](https://github.com/google/flexbox-layout) constructor and shows a warning with a suggestion to replace it with [FlexLayoutManager](https://github.com/RuslanKharkevych/TierListMaker/blob/master/app/src/main/java/me/khruslan/tierlistmaker/presentation/utils/recyclerview/FlexLayoutManager.kt). |
