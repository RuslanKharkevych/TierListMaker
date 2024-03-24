//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.analytics](../index.md)/[TierListRenamed](index.md)

# TierListRenamed

class [TierListRenamed](index.md)(initialTitle: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), updatedTitle: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Event](../-event/index.md)

Logged when user renames a tier list.

This event helps to identify how often users rename tier lists and which ones they rename the most.

#### Parameters

| | |
|---|---|
| initialTitle | Tier list title before renaming |
| updatedTitle | Tier list title after renaming. |

## Constructors

| | |
|---|---|
| [TierListRenamed](-tier-list-renamed.md) | constructor(initialTitle: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), updatedTitle: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Creates event with initial title and updated title parameters. |
