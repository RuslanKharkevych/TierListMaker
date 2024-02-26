//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.theme](../index.md)/[ThemeManagerImpl](index.md)

# ThemeManagerImpl

class [ThemeManagerImpl](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor(val preferencesHelper: [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md), val dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)) : [ThemeManager](../-theme-manager/index.md)

[ThemeManager](../-theme-manager/index.md) implementation.

## Constructors

| | |
|---|---|
| [ThemeManagerImpl](-theme-manager-impl.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor(preferencesHelper: [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md), dispatcherProvider: [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md))<br>Creates theme manager with injected dependencies. |

## Properties

| Name | Summary |
|---|---|
| [dispatcherProvider](dispatcher-provider.md) | private val [dispatcherProvider](dispatcher-provider.md): [DispatcherProvider](../../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md)<br>Provides coroutine dispatchers. |
| [preferencesHelper](preferences-helper.md) | private val [preferencesHelper](preferences-helper.md): [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md)<br>Saves user theme preference. |

## Functions

| Name | Summary |
|---|---|
| [applyTheme](apply-theme.md) | private fun [applyTheme](apply-theme.md)(nightModeEnabled: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Applies light or dark theme based on [nightModeEnabled](apply-theme.md) flag. |
| [setDefaultTheme](set-default-theme.md) | open override fun [setDefaultTheme](set-default-theme.md)()<br>Synchronously applies default application's theme. |
| [toggleTheme](toggle-theme.md) | open suspend override fun [toggleTheme](toggle-theme.md)()<br>Asynchronously toggles light/dark theme. |
