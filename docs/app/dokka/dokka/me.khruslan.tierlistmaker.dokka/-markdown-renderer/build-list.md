//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownRenderer](index.md)/[buildList](build-list.md)

# buildList

open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildList](build-list.md)(node: ContentList, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)

Builds list, making sure nested lists are ordered correctly.

Fixes the issue in the CommonmarkRenderer, where nested lists were not ordered as expected.

#### Receiver

Current string builder.

#### Parameters

| | |
|---|---|
| node | A list node to render. |
| pageContext | Context of the page. |
| sourceSetRestriction | Available source sets. |
