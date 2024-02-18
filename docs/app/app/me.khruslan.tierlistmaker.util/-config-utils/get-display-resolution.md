//[app](../../../index.md)/[me.khruslan.tierlistmaker.util](../index.md)/[ConfigUtils](index.md)/[getDisplayResolution](get-display-resolution.md)

# getDisplayResolution

fun [getDisplayResolution](get-display-resolution.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Get display resolution in pixels.

On Android 11+ returns bounds of the maximum window metrics. On older versions returns real size of the default display.

#### Return

Display resolution string in {width}x{height} format, or &quot;unknown&quot; if unable to access window metrics.

#### Parameters

| | |
|---|---|
| context | Context to obtain window manager. |
