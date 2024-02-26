//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.dispatchers](../index.md)/[DispatcherProviderImpl](index.md)

# DispatcherProviderImpl

class [DispatcherProviderImpl](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor : [DispatcherProvider](../-dispatcher-provider/index.md)

[DispatcherProvider](../-dispatcher-provider/index.md) implementation.

Can be injected in production code.

## Constructors

| | |
|---|---|
| [DispatcherProviderImpl](-dispatcher-provider-impl.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor()<br>Creates a new dispatcher provider instance. |

## Properties

| Name | Summary |
|---|---|
| [default](default.md) | open override val [default](default.md): [CoroutineDispatcher](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html)<br>Returns [Dispatchers.Default](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-default.html). |
| [io](io.md) | open override val [io](io.md): [CoroutineDispatcher](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html)<br>Returns [Dispatchers.IO](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-i-o.html). |
| [main](main.md) | open override val [main](main.md): [MainCoroutineDispatcher](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-main-coroutine-dispatcher/index.html)<br>Returns [Dispatchers.Main](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-main.html). |
| [unconfined](unconfined.md) | open override val [unconfined](unconfined.md): [CoroutineDispatcher](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-dispatcher/index.html)<br>Returns [Dispatchers.Unconfined](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-dispatchers/-unconfined.html). |
