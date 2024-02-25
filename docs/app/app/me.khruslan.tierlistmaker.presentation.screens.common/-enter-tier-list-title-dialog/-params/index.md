//[app](../../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.common](../../index.md)/[EnterTierListTitleDialog](../index.md)/[Params](index.md)

# Params

private data class [Params](index.md)(@[StringRes ](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)var dialogTitleResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, var tierListTitle: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, var onConfirmListener: [EnterTierListTitleDialog.OnConfirmListener](../-on-confirm-listener/index.md)? = null)

[EnterTierListTitleDialog](../index.md) parameters.

This class is mutable. Configured by [EnterTierListTitleDialog.Builder](../-builder/index.md).

## Constructors

| | |
|---|---|
| [Params](-params.md) | constructor(@[StringRes ](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)dialogTitleResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)? = null, tierListTitle: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, onConfirmListener: [EnterTierListTitleDialog.OnConfirmListener](../-on-confirm-listener/index.md)? = null)<br>Creates new params. |

## Properties

| Name | Summary |
|---|---|
| [dialogTitleResId](dialog-title-res-id.md) | private var [dialogTitleResId](dialog-title-res-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)?<br>String resource of the dialog title. |
| [onConfirmListener](on-confirm-listener.md) | var [onConfirmListener](on-confirm-listener.md): [EnterTierListTitleDialog.OnConfirmListener](../-on-confirm-listener/index.md)?<br>Title confirmation callback. |
| [tierListTitle](tier-list-title.md) | var [tierListTitle](tier-list-title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>Initial tier list title. |

## Functions

| Name | Summary |
|---|---|
| [getDialogTitle](get-dialog-title.md) | fun [getDialogTitle](get-dialog-title.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html)): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>Resolves localized dialog title. |
| [setDialogTitle](set-dialog-title.md) | fun [setDialogTitle](set-dialog-title.md)(@[StringRes ](https://developer.android.com/reference/kotlin/androidx/annotation/StringRes.html)titleResId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Setter of [dialogTitleResId](dialog-title-res-id.md). |
