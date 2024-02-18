//[app](../../../index.md)/[me.khruslan.tierlistmaker](../index.md)/[TierListMakerApplication](index.md)/[getTrimMemoryLevelName](get-trim-memory-level-name.md)

# getTrimMemoryLevelName

private fun [getTrimMemoryLevelName](get-trim-memory-level-name.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Converts trim memory level value to a readable format.

If level is unknown, simply converts it to string as is.

#### Return

Level name string, e.g. &quot;RUNNING_LOW&quot;.

#### Parameters

| | |
|---|---|
| level | Trim memory level obtained from [onTrimMemory](on-trim-memory.md) callback. |
