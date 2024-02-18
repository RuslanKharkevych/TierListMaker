//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.theme](../index.md)/[ThemeManager](index.md)

# ThemeManager

interface [ThemeManager](index.md)

Manager for changing application's theme at runtime.

Supports two themes: light and dark. Allows to toggle between them in runtime. Saves user theme preference.

#### Inheritors

| |
|---|
| [ThemeManagerImpl](../-theme-manager-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [setDefaultTheme](set-default-theme.md) | abstract fun [setDefaultTheme](set-default-theme.md)()<br>Synchronously default application's theme. |
| [toggleTheme](toggle-theme.md) | abstract suspend fun [toggleTheme](toggle-theme.md)()<br>Asynchronously toggles light/dark theme. |
