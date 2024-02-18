//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.common](../../index.md)/[EnterTierListTitleDialog](../index.md)/[Builder](index.md)

# Builder

class [Builder](index.md)

Builder of [EnterTierListTitleDialog](../index.md).

Dialog title, tier list title and confirm listener can be customized.

## Constructors

| | |
|---|---|
| [Builder](-builder.md) | constructor()<br>Creates a new builder with empty params. |

## Properties

| Name | Summary |
|---|---|
| [params](params.md) | private val [params](params.md): [EnterTierListTitleDialog.Params](../-params/index.md)<br>Dialog parameters. |

## Functions

| Name | Summary |
|---|---|
| [build](build.md) | fun [build](build.md)(): [EnterTierListTitleDialog](../index.md)<br>Builds [EnterTierListTitleDialog](../index.md) with [params](params.md). |
| [setDialogTitle](set-dialog-title.md) | fun [setDialogTitle](set-dialog-title.md)(@[StringRes](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)titleResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): [EnterTierListTitleDialog.Builder](index.md)<br>Set the dialog title to display using the given resource id. |
| [setOnConfirmListener](set-on-confirm-listener.md) | fun [setOnConfirmListener](set-on-confirm-listener.md)(listener: [EnterTierListTitleDialog.OnConfirmListener](../-on-confirm-listener/index.md)): [EnterTierListTitleDialog.Builder](index.md)<br>Set a callback to be invoked on tier list title confirmation. |
| [setTierListTitle](set-tier-list-title.md) | fun [setTierListTitle](set-tier-list-title.md)(title: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [EnterTierListTitleDialog.Builder](index.md)<br>Set the initial tier list title. |
