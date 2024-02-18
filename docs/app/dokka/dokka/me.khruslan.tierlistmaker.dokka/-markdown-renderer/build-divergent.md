//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownRenderer](index.md)/[buildDivergent](build-divergent.md)

# buildDivergent

open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildDivergent](build-divergent.md)(node: ContentDivergentGroup, pageContext: ContentPage)

Builds divergent without source set tags.

Source set tags are not necessary because all code is written for the same platform.

#### Receiver

Current string builder.

#### Parameters

| | |
|---|---|
| node | A node to render. |
| pageContext | Context of the page. |
