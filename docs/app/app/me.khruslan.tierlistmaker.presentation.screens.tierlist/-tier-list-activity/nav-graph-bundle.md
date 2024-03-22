//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.tierlist](../index.md)/[TierListActivity](index.md)/[navGraphBundle](nav-graph-bundle.md)

# navGraphBundle

private val [navGraphBundle](nav-graph-bundle.md): [Bundle](https://developer.android.com/reference/kotlin/android/os/Bundle.html)

Bundle to set in navigation graph.

Created from intent extras. The arguments from this bundle can be accessed in the fragment, which is a start destination of the tier list navigation graph. Note that [EXTRA_HINT_STEP](-nav-helpers/-e-x-t-r-a_-h-i-n-t_-s-t-e-p.md) is mapped to [KEY_HINT_STEP_NAME](-nav-helpers/-k-e-y_-h-i-n-t_-s-t-e-p_-n-a-m-e.md) because nullable enums are not supported by the Safe Args plugin.
