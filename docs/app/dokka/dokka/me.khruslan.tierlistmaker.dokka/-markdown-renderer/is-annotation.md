//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownRenderer](index.md)/[isAnnotation](is-annotation.md)

# isAnnotation

private fun ContentText.[isAnnotation](is-annotation.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Checks whether this text node is an annotation.

Compares node text for the exact matching. The list of annotations to compare is hardcoded. To support correct rendering of new annotations, they must be listed here.

#### Receiver

Text node.

#### Return

Whether this text node is among supported annotations.
