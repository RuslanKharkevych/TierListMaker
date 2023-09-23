package me.khruslan.tierlistmaker.lint

import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestFiles.java

val ALERT_DIALOG_STUB: TestFile = java(
    """
        package androidx.appcompat.app;
        
        public class AlertDialog extends AppCompatDialog implements DialogInterface {
            public static class Builder {
                public Builder(@NonNull Context context) {}
            }
        }
    """
).indented()

val MATERIAL_ALERT_DIALOG_STUB: TestFile = java(
    """
        package com.google.android.material.dialog;

        public class MaterialAlertDialogBuilder extends AlertDialog.Builder {
            public MaterialAlertDialogBuilder(@NonNull Context context) {}
            public MaterialAlertDialogBuilder setTitle(@Nullable CharSequence title) {}
            public MaterialAlertDialogBuilder setPositiveButton(
                    @StringRes int textId, @Nullable final OnClickListener listener) {}
            public MaterialAlertDialogBuilder setNegativeButton(
                    @StringRes int textId, @Nullable final OnClickListener listener) {}
            public AlertDialog create() {}
        }
    """
).indented()

val FLEXBOX_LAYOUT_MANAGER_STUB: TestFile = java(
    """
        package com.google.android.flexbox;
        
        public class FlexboxLayoutManager extends RecyclerView.LayoutManager implements FlexContainer,
                RecyclerView.SmoothScroller.ScrollVectorProvider {
            public FlexboxLayoutManager(Context context) {}
        }
    """
)