//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownRenderer](index.md)/[insertBreakBetweenOverloads](insert-break-between-overloads.md)

# insertBreakBetweenOverloads

private fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[insertBreakBetweenOverloads](insert-break-between-overloads.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Inserts an extra line break between overloaded entries.

The algorithm steps:

1. Split the input string into lines.
2. Exclude lines with annotations.
3. If there are less than 4 lines, return input string.
4. Add an extra break after each description.
5. Remove the trailing break.

#### Receiver

Input string containing method (constructor) signatures and descriptions.

#### Return

Output string with divided overloaded entries.
