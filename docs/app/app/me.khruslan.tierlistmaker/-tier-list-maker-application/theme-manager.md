//[app](../../../index.md)/[me.khruslan.tierlistmaker](../index.md)/[TierListMakerApplication](index.md)/[themeManager](theme-manager.md)

# themeManager

@Inject 

lateinit var [themeManager](theme-manager.md): [ThemeManager](../../me.khruslan.tierlistmaker.presentation.utils.theme/-theme-manager/index.md)

Manager used to set the application theme.

Needs to be injected inside the application because updating the theme leads to configuration change. Therefore, it must be done before any activity is created to avoid UI glitches.
