//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownRenderer](index.md)/[isAnnotation](is-annotation.md)

# isAnnotation

private fun ContentDRILink.[isAnnotation](is-annotation.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Checks whether this node links to an annotation.

Compares node's fully-qualified class name for the exact match. The list of annotations to compare with is hardcoded. To support correct rendering of new annotations, they must be listed here.

#### Receiver

Link node.

#### Return

Whether this node links to a supported annotation.
