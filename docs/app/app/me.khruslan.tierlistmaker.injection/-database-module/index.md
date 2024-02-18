//[app](../../../index.md)/[me.khruslan.tierlistmaker.injection](../index.md)/[DatabaseModule](index.md)

# DatabaseModule

@Module

abstract class [DatabaseModule](index.md)

Hilt module that contains database-related bindings.

This class must be used only by Hilt codegen.

## Constructors

| | |
|---|---|
| [DatabaseModule](-database-module.md) | constructor()<br>Default empty constructor. |

## Functions

| Name | Summary |
|---|---|
| [bindDatabaseHelper](bind-database-helper.md) | @Binds<br>@Singleton<br>abstract fun [bindDatabaseHelper](bind-database-helper.md)(databaseHelperImpl: [DatabaseHelperImpl](../../me.khruslan.tierlistmaker.data.providers.database/-database-helper-impl/index.md)): [DatabaseHelper](../../me.khruslan.tierlistmaker.data.providers.database/-database-helper/index.md)<br>Binds database helper interface to its implementation. |
| [bindDefaultTierListCollectionProvider](bind-default-tier-list-collection-provider.md) | @Binds<br>@Singleton<br>abstract fun [bindDefaultTierListCollectionProvider](bind-default-tier-list-collection-provider.md)(defaultTierListCollectionProviderImpl: [DefaultTierListCollectionProviderImpl](../../me.khruslan.tierlistmaker.data.providers.database/-default-tier-list-collection-provider-impl/index.md)): [DefaultTierListCollectionProvider](../../me.khruslan.tierlistmaker.data.providers.database/-default-tier-list-collection-provider/index.md)<br>Binds default tier list collection provider interface to its implementation. |
| [bindPreferencesHelper](bind-preferences-helper.md) | @Binds<br>@Singleton<br>abstract fun [bindPreferencesHelper](bind-preferences-helper.md)(preferencesHelperImpl: [PreferencesHelperImpl](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper-impl/index.md)): [PreferencesHelper](../../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md)<br>Binds preferences helper interface to its implementation. |
