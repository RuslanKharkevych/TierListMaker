//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[PreferencesHelper](index.md)

# PreferencesHelper

interface [PreferencesHelper](index.md)

Helper that simplifies persisting user preferences.

All functions are synchronous. No error handling is required. Some properties are read-only because they are not changed directly from code.

#### Inheritors

| |
|---|
| [PreferencesHelperImpl](../-preferences-helper-impl/index.md) |

## Properties

| Name | Summary |
|---|---|
| [defaultTierListCollectionProvided](default-tier-list-collection-provided.md) | abstract val [defaultTierListCollectionProvided](default-tier-list-collection-provided.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether the default tier list collection has already been provided. |
| [imageQuality](image-quality.md) | abstract val [imageQuality](image-quality.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The quality in percents of the new tier list images. |
| [nightModeEnabled](night-mode-enabled.md) | abstract var [nightModeEnabled](night-mode-enabled.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Whether dark theme is preferred by user. If not - user opted for light theme. |
| [scale](scale.md) | abstract val [scale](scale.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>How many images will fit in a row inside a tier in a new tier list. |
| [tiersCount](tiers-count.md) | abstract val [tiersCount](tiers-count.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>How many tiers will be added when a new tier list is created. |

## Functions

| Name | Summary |
|---|---|
| [markDefaultTierListCollectionAsProvided](mark-default-tier-list-collection-as-provided.md) | abstract fun [markDefaultTierListCollectionAsProvided](mark-default-tier-list-collection-as-provided.md)()<br>Marks default tier list collection as provided. |
