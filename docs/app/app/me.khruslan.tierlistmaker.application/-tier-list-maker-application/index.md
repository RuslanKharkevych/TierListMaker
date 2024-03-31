//[app](../../../index.md)/[me.khruslan.tierlistmaker.application](../index.md)/[TierListMakerApplication](index.md)

# TierListMakerApplication

class [TierListMakerApplication](index.md) : [BaseTierListMakerApplication](../-base-tier-list-maker-application/index.md)

Custom application class required by [Hilt](https://dagger.dev/hilt) for generating [Dagger](https://dagger.dev) components.

This class contains only logic that is tied with dependency injection. All other startup configurations are extened from [BaseTierListMakerApplication](../-base-tier-list-maker-application/index.md). This decoupling is requied because it's not allowed to use HiltAndroidApp and [Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html)  annotations in instrumented tests.

## Constructors

| | |
|---|---|
| [TierListMakerApplication](-tier-list-maker-application.md) | constructor()<br>Default constructor called by Android system. |

## Properties

| Name | Summary |
|---|---|
| [themeManager](theme-manager.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>lateinit var [themeManager](theme-manager.md): [ThemeManager](../../me.khruslan.tierlistmaker.presentation.utils.theme/-theme-manager/index.md)<br>Manager used to set the application theme. |

## Functions

| Name | Summary |
|---|---|
| [onCreate](on-create.md) | open override fun [onCreate](on-create.md)()<br>Applies the application theme. |
