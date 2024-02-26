//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[PreferencesHelperImpl](index.md)

# PreferencesHelperImpl

class [PreferencesHelperImpl](index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)) : [PreferencesHelper](../-preferences-helper/index.md)

[PreferencesHelper](../-preferences-helper/index.md) implementation.

Implemented with [SharedPreferences](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.html). To avoid memory leaks, this class should be injected as a singleton.

## Constructors

| | |
|---|---|
| [PreferencesHelperImpl](-preferences-helper-impl.md) | @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) <br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html))<br>Creates a new preferences helper instance. |

## Types

| Name | Summary |
|---|---|
| [Keys](-keys/index.md) | private object [Keys](-keys/index.md)<br>Preference keys. |

## Properties

| Name | Summary |
|---|---|
| [context](context.md) | private val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)<br>Application context. |
| [defaultTierListCollectionProvided](default-tier-list-collection-provided.md) | open override val [defaultTierListCollectionProvided](default-tier-list-collection-provided.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether the default tier list collection has already been provided. |
| [imageQuality](image-quality.md) | open override val [imageQuality](image-quality.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The quality in percents of the new tier list images. |
| [listener](listener.md) | private val [listener](listener.md): [SharedPreferences.OnSharedPreferenceChangeListener](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.OnSharedPreferenceChangeListener.html)<br>Shared preference change listener that logs all updates. |
| [nightModeEnabled](night-mode-enabled.md) | open override var [nightModeEnabled](night-mode-enabled.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether dark theme is preferred by user. If not - user opted for light theme. |
| [preferences](preferences.md) | private val [preferences](preferences.md): [SharedPreferences](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.html)<br>Singleton instance of [SharedPreferences](https://developer.android.com/reference/kotlin/android/content/SharedPreferences.html). |
| [scale](scale.md) | open override val [scale](scale.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>How many images will fit in a row inside a tier in a new tier list. |
| [tiersCount](tiers-count.md) | open override val [tiersCount](tiers-count.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>How many tiers will be added when a new tier list is created. |

## Functions

| Name | Summary |
|---|---|
| [markDefaultTierListCollectionAsProvided](mark-default-tier-list-collection-as-provided.md) | open override fun [markDefaultTierListCollectionAsProvided](mark-default-tier-list-collection-as-provided.md)()<br>Marks default tier list collection as provided. |
| [registerOnSharedPreferenceChangeListener](register-on-shared-preference-change-listener.md) | private fun [registerOnSharedPreferenceChangeListener](register-on-shared-preference-change-listener.md)()<br>Registers [listener](listener.md) for logging. |
