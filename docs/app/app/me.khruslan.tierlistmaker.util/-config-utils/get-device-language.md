//[app](../../../index.md)/[me.khruslan.tierlistmaker.util](../index.md)/[ConfigUtils](index.md)/[getDeviceLanguage](get-device-language.md)

# getDeviceLanguage

fun [getDeviceLanguage](get-device-language.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Get the preferred language of the device.

On Android 7+ returns the first language from the locale list. On older versions returns the language of the primary locale.

#### Return

The language code.
