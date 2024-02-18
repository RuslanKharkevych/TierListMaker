//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](index.md)

# Package-level declarations

UI utilities for general purpose.

## Types

| Name | Summary |
|---|---|
| [AnimatorUtils](-animator-utils/index.md) | object [AnimatorUtils](-animator-utils/index.md)<br>Set of utilities to simplify creation of complex [Animator](https://developer.android.com/reference/kotlin/android/animation/Animator.html) objects. |
| [ClickThrottler](-click-throttler/index.md) | private abstract class [ClickThrottler](-click-throttler/index.md)<br>Implements throttling mechanism to prevent duplicated click events. |
| [FeedbackUtils](-feedback-utils/index.md) | object [FeedbackUtils](-feedback-utils/index.md)<br>Utility that provides user interface for sending feedback. |
| [OnThrottledClickListener](-on-throttled-click-listener/index.md) | private abstract class [OnThrottledClickListener](-on-throttled-click-listener/index.md) : [ClickThrottler](-click-throttler/index.md), [View.OnClickListener](https://developer.android.com/reference/kotlin/android/view/View.OnClickListener.html)<br>Extension of [View.OnClickListener](https://developer.android.com/reference/kotlin/android/view/View.OnClickListener.html) that prevents duplicate clicks. |
| [OnThrottledPreferenceClickListener](-on-throttled-preference-click-listener/index.md) | private abstract class [OnThrottledPreferenceClickListener](-on-throttled-preference-click-listener/index.md) : [ClickThrottler](-click-throttler/index.md), [Preference.OnPreferenceClickListener](https://developer.android.com/reference/kotlin/androidx/preference/Preference.OnPreferenceClickListener.html)<br>Extension of [Preference.OnPreferenceClickListener](https://developer.android.com/reference/kotlin/androidx/preference/Preference.OnPreferenceClickListener.html) that prevents multiple clicks. |
| [PreferenceNotFoundException](-preference-not-found-exception/index.md) | private class [PreferenceNotFoundException](-preference-not-found-exception/index.md)(preferenceKey: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)<br>Thrown to indicate that the preference was not found. |

## Properties

| Name | Summary |
|---|---|
| [CrossfadeTransition](-crossfade-transition.md) | private val [CrossfadeTransition](-crossfade-transition.md): DrawableTransitionOptions<br>Enables crossfade animation when image is loaded into the view. |

## Functions

| Name | Summary |
|---|---|
| [findPreference](find-preference.md) | private fun [PreferenceFragmentCompat](https://developer.android.com/reference/kotlin/androidx/preference/PreferenceFragmentCompat.html).[findPreference](find-preference.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)keyResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Preference](https://developer.android.com/reference/kotlin/androidx/preference/Preference.html)<br>Finds preference by the key resolved from [keyResId](find-preference.md). |
| [getMaterialColor](get-material-color.md) | @[ColorInt](https://developer.android.com/reference/kotlin/androidx/annotation/ColorInt.html)<br>fun [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).[getMaterialColor](get-material-color.md)(@[AttrRes](https://developer.android.com/reference/kotlin/androidx/annotation/AttrRes.html)colorAttrRes: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Returns the color int for the provided theme color attribute. |
| [loadTierListImage](load-tier-list-image.md) | fun [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html).[loadTierListImage](load-tier-list-image.md)(filePath: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Loads the image from the device file system into the [ImageView](https://developer.android.com/reference/kotlin/android/widget/ImageView.html) in the tier list. |
| [setOnPreferenceClickListener](set-on-preference-click-listener.md) | fun [PreferenceFragmentCompat](https://developer.android.com/reference/kotlin/androidx/preference/PreferenceFragmentCompat.html).[setOnPreferenceClickListener](set-on-preference-click-listener.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)keyResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Sets click listener for the preference found by the key resolved from [keyResId](set-on-preference-click-listener.md). |
| [setOnThrottledClickListener](set-on-throttled-click-listener.md) | fun [View](https://developer.android.com/reference/kotlin/android/view/View.html).[setOnThrottledClickListener](set-on-throttled-click-listener.md)(onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Sets [OnThrottledClickListener](-on-throttled-click-listener/index.md) to the view. |
| [setOnThrottledPreferenceClickListener](set-on-throttled-preference-click-listener.md) | fun [Preference](https://developer.android.com/reference/kotlin/androidx/preference/Preference.html).[setOnThrottledPreferenceClickListener](set-on-throttled-preference-click-listener.md)(onClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Sets [OnThrottledPreferenceClickListener](-on-throttled-preference-click-listener/index.md) to the preference. |
| [setPreferenceSummary](set-preference-summary.md) | fun [PreferenceFragmentCompat](https://developer.android.com/reference/kotlin/androidx/preference/PreferenceFragmentCompat.html).[setPreferenceSummary](set-preference-summary.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)keyResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), summary: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Sets summary for the preference found by the key resolved from [keyResId](set-preference-summary.md). |
