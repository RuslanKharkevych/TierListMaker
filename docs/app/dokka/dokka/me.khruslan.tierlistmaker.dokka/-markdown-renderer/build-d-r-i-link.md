//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownRenderer](index.md)/[buildDRILink](build-d-r-i-link.md)

# buildDRILink

open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildDRILink](build-d-r-i-link.md)(node: ContentDRILink, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)

Builds DRI link, excluding generated code.

View binding classes are generated and should not be documented.

#### Receiver

Current string builder.

#### Parameters

| | |
|---|---|
| node | DRI link node to render. |
| pageContext | Context of the page. |
| sourceSetRestriction | Available source sets. |
