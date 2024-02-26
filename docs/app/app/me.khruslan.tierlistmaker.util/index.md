//[app](../../index.md)/[me.khruslan.tierlistmaker.util](index.md)

# Package-level declarations

Utilities for general purpose.

## Types

| Name | Summary |
|---|---|
| [ConfigUtils](-config-utils/index.md) | object [ConfigUtils](-config-utils/index.md)<br>Utilities that provide information about device configuration. |

## Properties

| Name | Summary |
|---|---|
| [BACKLOG_TIER_POSITION](-b-a-c-k-l-o-g_-t-i-e-r_-p-o-s-i-t-i-o-n.md) | const val [BACKLOG_TIER_POSITION](-b-a-c-k-l-o-g_-t-i-e-r_-p-o-s-i-t-i-o-n.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Used to differentiate the backlog from other tiers. |
| [displayWidthPixels](display-width-pixels.md) | val [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).[displayWidthPixels](display-width-pixels.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Display width of the device in pixels. |
| [TIER_IMAGE_WIDTH_FRACTION](-t-i-e-r_-i-m-a-g-e_-w-i-d-t-h_-f-r-a-c-t-i-o-n.md) | const val [TIER_IMAGE_WIDTH_FRACTION](-t-i-e-r_-i-m-a-g-e_-w-i-d-t-h_-f-r-a-c-t-i-o-n.md): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)<br>A proportion of the tier list image width to the display width. |

## Functions

| Name | Summary |
|---|---|
| [capitalized](capitalized.md) | fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[capitalized](capitalized.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Returns a copy of the string with its first letter as a capital letter. |
| [dpToPx](dp-to-px.md) | fun [Context](https://developer.android.com/reference/kotlin/android/content/Context.html).[dpToPx](dp-to-px.md)(value: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)<br>Converts value from dp to pixels. |
| [findNavHostFragmentById](find-nav-host-fragment-by-id.md) | fun [FragmentActivity](https://developer.android.com/reference/kotlin/androidx/fragment/app/FragmentActivity.html).[findNavHostFragmentById](find-nav-host-fragment-by-id.md)(@[IdRes](https://developer.android.com/reference/kotlin/androidx/annotation/IdRes.html) id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [NavHostFragment](https://developer.android.com/reference/kotlin/androidx/navigation/fragment/NavHostFragment.html)<br>Finds NavHostFragment by id. |
| [getParcelableExtraCompat](get-parcelable-extra-compat.md) | inline fun &lt;[T](get-parcelable-extra-compat.md) : [Parcelable](https://developer.android.com/reference/kotlin/android/os/Parcelable.html)&gt; [Intent](https://developer.android.com/reference/kotlin/android/content/Intent.html).[getParcelableExtraCompat](get-parcelable-extra-compat.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [T](get-parcelable-extra-compat.md)?<br>A helper to get parcelable extra from intent that supports all versions. |
| [requireString](require-string.md) | fun [Parcel](https://developer.android.com/reference/kotlin/android/os/Parcel.html).[requireString](require-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Reads string from the parcel. |
| [swap](swap.md) | fun &lt;[T](swap.md)&gt; [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[T](swap.md)&gt;.[swap](swap.md)(a: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), b: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Swaps two items in a mutable list. |
| [updateLast](update-last.md) | fun &lt;[T](update-last.md)&gt; [MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[T](update-last.md)&gt;.[updateLast](update-last.md)(value: [T](update-last.md))<br>Sets the value to the last item in a mutable list. |
