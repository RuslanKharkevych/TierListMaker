//[app](../../../index.md)/[me.khruslan.tierlistmaker.injection](../index.md)/[TierListModule](index.md)

# TierListModule

@Module

abstract class [TierListModule](index.md)

Hilt module that contains bindings related to tier lists.

This class must be used only by Hilt codegen.

## Constructors

| | |
|---|---|
| [TierListModule](-tier-list-module.md) | constructor()<br>Default empty constructor. |

## Functions

| Name | Summary |
|---|---|
| [bindDragPocket](bind-drag-pocket.md) | @Binds<br>abstract fun [bindDragPocket](bind-drag-pocket.md)(dragPocketImpl: [DragPocketImpl](../../me.khruslan.tierlistmaker.data.providers.drag/-drag-pocket-impl/index.md)): [DragPocket](../../me.khruslan.tierlistmaker.data.providers.drag/-drag-pocket/index.md)<br>Binds drag pocket interface  to its implementation. |
| [bindTierColorProvider](bind-tier-color-provider.md) | @Binds<br>abstract fun [bindTierColorProvider](bind-tier-color-provider.md)(tierColorProviderImpl: [TierColorProviderImpl](../../me.khruslan.tierlistmaker.data.providers.tierlist.tier/-tier-color-provider-impl/index.md)): [TierColorProvider](../../me.khruslan.tierlistmaker.data.providers.tierlist.tier/-tier-color-provider/index.md)<br>Binds tier color provider interface to its implementation. |
| [bindTierListBitmapGenerator](bind-tier-list-bitmap-generator.md) | @Binds<br>abstract fun [bindTierListBitmapGenerator](bind-tier-list-bitmap-generator.md)(tierListBitmapGeneratorImpl: [TierListBitmapGeneratorImpl](../../me.khruslan.tierlistmaker.presentation.utils.tierlist/-tier-list-bitmap-generator-impl/index.md)): [TierListBitmapGenerator](../../me.khruslan.tierlistmaker.presentation.utils.tierlist/-tier-list-bitmap-generator/index.md)<br>Binds tier list bitmap generator interface to its implementation. |
| [bindTierListCreator](bind-tier-list-creator.md) | @Binds<br>abstract fun [bindTierListCreator](bind-tier-list-creator.md)(tierListCreatorImpl: [TierListCreatorImpl](../../me.khruslan.tierlistmaker.data.providers.tierlist/-tier-list-creator-impl/index.md)): [TierListCreator](../../me.khruslan.tierlistmaker.data.providers.tierlist/-tier-list-creator/index.md)<br>Binds tier list creator interface to its implementation. |
| [bindTierListProcessor](bind-tier-list-processor.md) | @Binds<br>abstract fun [bindTierListProcessor](bind-tier-list-processor.md)(tierListProcessorImpl: [TierListProcessorImpl](../../me.khruslan.tierlistmaker.data.providers.tierlist/-tier-list-processor-impl/index.md)): [TierListProcessor](../../me.khruslan.tierlistmaker.data.providers.tierlist/-tier-list-processor/index.md)<br>Binds tier list processor interface to its implementation. |
| [bindTierNameProvider](bind-tier-name-provider.md) | @Binds<br>abstract fun [bindTierNameProvider](bind-tier-name-provider.md)(tierNameProviderImpl: [TierNameProviderImpl](../../me.khruslan.tierlistmaker.data.providers.tierlist.tier/-tier-name-provider-impl/index.md)): [TierNameProvider](../../me.khruslan.tierlistmaker.data.providers.tierlist.tier/-tier-name-provider/index.md)<br>Binds tier name provider interface to its implementation. |
| [bindTierStyleProvider](bind-tier-style-provider.md) | @Binds<br>abstract fun [bindTierStyleProvider](bind-tier-style-provider.md)(tierStyleProviderImpl: [TierStyleProviderImpl](../../me.khruslan.tierlistmaker.data.providers.tierlist.tier/-tier-style-provider-impl/index.md)): [TierStyleProvider](../../me.khruslan.tierlistmaker.data.providers.tierlist.tier/-tier-style-provider/index.md)<br>Binds tier style provider interface to its implementation. |
