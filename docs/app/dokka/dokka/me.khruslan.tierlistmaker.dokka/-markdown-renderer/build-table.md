//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownRenderer](index.md)/[buildTable](build-table.md)

# buildTable

open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildTable](build-table.md)(node: ContentTable, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)

Builds table, making sure that all available contents are shown.

Fixes the issue in the CommonmarkRenderer, where table cells are not populated if header is empty.

#### Receiver

Current string builder.

#### Parameters

| | |
|---|---|
| node | A table node to render. |
| pageContext | Context of the page. |
| sourceSetRestriction | Available source sets. |
