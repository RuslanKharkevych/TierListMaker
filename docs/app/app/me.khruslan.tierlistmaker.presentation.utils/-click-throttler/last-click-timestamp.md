//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils](../index.md)/[ClickThrottler](index.md)/[lastClickTimestamp](last-click-timestamp.md)

# lastClickTimestamp

private var [lastClickTimestamp](last-click-timestamp.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)

The timestamp of the last click.

Used to calculate elapsed time for the next click. Note that if click is blocked, this field does not get updated.
