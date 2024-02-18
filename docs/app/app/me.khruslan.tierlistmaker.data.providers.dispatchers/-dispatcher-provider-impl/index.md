//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.dispatchers](../index.md)/[DispatcherProviderImpl](index.md)

# DispatcherProviderImpl

class [DispatcherProviderImpl](index.md)@Injectconstructor : [DispatcherProvider](../-dispatcher-provider/index.md)

[DispatcherProvider](../-dispatcher-provider/index.md) implementation.

Can be injected in production code.

## Constructors

| | |
|---|---|
| [DispatcherProviderImpl](-dispatcher-provider-impl.md) | @Inject<br>constructor()<br>Creates a new dispatcher provider instance. |

## Properties

| Name | Summary |
|---|---|
| [default](default.md) | open override val [default](default.md): CoroutineDispatcher<br>Returns Dispatchers.Default. |
| [io](io.md) | open override val [io](io.md): CoroutineDispatcher<br>Returns Dispatchers.IO. |
| [main](main.md) | open override val [main](main.md): MainCoroutineDispatcher<br>Returns Dispatchers.Main. |
| [unconfined](unconfined.md) | open override val [unconfined](unconfined.md): CoroutineDispatcher<br>Returns Dispatchers.Unconfined. |
