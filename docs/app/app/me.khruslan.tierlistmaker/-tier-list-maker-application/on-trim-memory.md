//[app](../../../index.md)/[me.khruslan.tierlistmaker](../index.md)/[TierListMakerApplication](index.md)/[onTrimMemory](on-trim-memory.md)

# onTrimMemory

open override fun [onTrimMemory](on-trim-memory.md)(level: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))

Logs trim memory events.

Called when the operating system has determined that it is a good time for a process to trim unneeded memory from its process.

#### Parameters

| | |
|---|---|
| level | The context of the trim, giving a hint of the amount of trimming the application may like to perform. |
