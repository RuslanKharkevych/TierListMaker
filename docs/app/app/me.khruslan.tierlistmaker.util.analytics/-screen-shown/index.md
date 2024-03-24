//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.analytics](../index.md)/[ScreenShown](index.md)

# ScreenShown

class [ScreenShown](index.md)(className: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Event](../-event/index.md)

Logged when user navigates to a certain screen.

This event helps to identify which screens users visit the most often. Note theat this event should be used to log only fragments because activities are logged by default.

#### Parameters

| | |
|---|---|
| className | Name of the fragment class. |

## Constructors

| | |
|---|---|
| [ScreenShown](-screen-shown.md) | constructor(className: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Creates event with class name parameter. |
