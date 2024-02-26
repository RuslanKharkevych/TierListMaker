//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.dispatchers](../index.md)/[DispatcherProvider](index.md)

# DispatcherProvider

interface [DispatcherProvider](index.md)

Contract for providing coroutine dispatchers.

Used for injecting test dispatcher in unit tests.

#### Inheritors

| |
|---|
| [DispatcherProviderImpl](../-dispatcher-provider-impl/index.md) |

## Properties

| Name | Summary |
|---|---|
| [default](default.md) | abstract val [default](default.md): [CoroutineDispatcher](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html)<br>Returns [Dispatchers.Default](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-default.html) in production code. |
| [io](io.md) | abstract val [io](io.md): [CoroutineDispatcher](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html)<br>Returns [Dispatchers.IO](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-i-o.html) in production code. |
| [main](main.md) | abstract val [main](main.md): [CoroutineDispatcher](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html)<br>Returns [Dispatchers.Main](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-main.html) in production code. |
| [unconfined](unconfined.md) | abstract val [unconfined](unconfined.md): [CoroutineDispatcher](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html)<br>Returns [Dispatchers.Unconfined](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-unconfined.html) in production code. |
