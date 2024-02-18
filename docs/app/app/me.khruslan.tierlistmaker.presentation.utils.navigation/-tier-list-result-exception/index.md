//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.navigation](../index.md)/[TierListResultException](index.md)

# TierListResultException

class [TierListResultException](index.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [Exception](https://developer.android.com/reference/kotlin/java/lang/Exception.html)

Indicates that tier list navigation result can't be resolved.

Can be thrown during parsing of the result in the contract, or at a later stage when attempting to obtain the tier list inside the activity or fragment.

#### Parameters

| | |
|---|---|
| message | Error message of the exception. |

## Constructors

| | |
|---|---|
| [TierListResultException](-tier-list-result-exception.md) | constructor(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Creates exception with error message. |
