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
| [default](default.md) | abstract val [default](default.md): CoroutineDispatcher<br>Returns Dispatchers.Default in production code. |
| [io](io.md) | abstract val [io](io.md): CoroutineDispatcher<br>Returns Dispatchers.IO in production code. |
| [main](main.md) | abstract val [main](main.md): CoroutineDispatcher<br>Returns Dispatchers.Main in production code. |
| [unconfined](unconfined.md) | abstract val [unconfined](unconfined.md): CoroutineDispatcher<br>Returns Dispatchers.Unconfined in production code. |
