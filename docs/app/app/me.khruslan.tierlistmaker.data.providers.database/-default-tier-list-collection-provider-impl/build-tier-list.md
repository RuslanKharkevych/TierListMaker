//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.database](../index.md)/[DefaultTierListCollectionProviderImpl](index.md)/[buildTierList](build-tier-list.md)

# buildTierList

private fun [buildTierList](build-tier-list.md)(params: [DefaultTierListCollectionProviderImpl.TierListParams](-tier-list-params/index.md)): [TierList](../../me.khruslan.tierlistmaker.data.models.tierlist/-tier-list/index.md)?

Builds tier list from params.

If unable to read tier list images from assets, logs error and returns null.

#### Return

Created tier list or null in case of error.

#### Parameters

| | |
|---|---|
| params | Params needed for creating tier list. |
