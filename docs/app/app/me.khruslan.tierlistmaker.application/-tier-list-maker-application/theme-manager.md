//[app](../../../index.md)/[me.khruslan.tierlistmaker.application](../index.md)/[TierListMakerApplication](index.md)/[themeManager](theme-manager.md)

# themeManager

@[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) 

lateinit var [themeManager](theme-manager.md): [ThemeManager](../../me.khruslan.tierlistmaker.presentation.utils.theme/-theme-manager/index.md)

Manager used to set the application theme.

Needs to be injected inside the application because updating the theme leads to configuration change. Therefore, it must be done before any activity is created to avoid UI glitches.
