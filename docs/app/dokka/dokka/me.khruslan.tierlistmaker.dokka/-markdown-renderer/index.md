//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownRenderer](index.md)

# MarkdownRenderer

class [MarkdownRenderer](index.md)(context: DokkaContext) : CommonmarkRenderer

Renders documentation in markdown format.

Customizes the default GFM markdown renderer and fixes some of its issues.

#### Parameters

| | |
|---|---|
| context | Dokka context. |

## Constructors

| | |
|---|---|
| [MarkdownRenderer](-markdown-renderer.md) | constructor(context: DokkaContext)<br>Creates a new markdown renderer in a provided context. |

## Functions

| Name | Summary |
|---|---|
| [buildDivergent](build-divergent.md) | open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildDivergent](build-divergent.md)(node: ContentDivergentGroup, pageContext: ContentPage)<br>Builds divergent without source set tags. |
| [buildDivider](build-divider.md) | private fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildDivider](build-divider.md)()<br>Builds a divider surrounded by line breaks. |
| [buildDRILink](build-d-r-i-link.md) | open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildDRILink](build-d-r-i-link.md)(node: ContentDRILink, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)<br>Builds DRI link, excluding generated code. |
| [buildList](build-list.md) | open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildList](build-list.md)(node: ContentList, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)<br>Builds list, making sure nested lists are ordered correctly. |
| [buildParagraph](build-paragraph.md) | private fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildParagraph](build-paragraph.md)()<br>Builds a paragraph that consists of the two blank lines. |
| [buildPlatformDependent](build-platform-dependent.md) | open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildPlatformDependent](build-platform-dependent.md)(content: PlatformHintedContent, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)<br>Builds platform dependent without source set tags. |
| [buildTable](build-table.md) | open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildTable](build-table.md)(node: ContentTable, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)<br>Builds table, making sure that all available contents are shown. |
| [buildTableCells](build-table-cells.md) | private fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildTableCells](build-table-cells.md)(table: ContentTable, context: ContentPage)<br>Renders table cells. |
| [buildTableHeader](build-table-header.md) | private fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildTableHeader](build-table-header.md)(table: ContentTable, context: ContentPage)<br>Renders table header. |
| [buildText](build-text.md) | open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildText](build-text.md)(textNode: ContentText)<br>Builds text, making sure annotations are rendered with leading space when necessary. |
| [buildTextWithDecorators](build-text-with-decorators.md) | private fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildTextWithDecorators](build-text-with-decorators.md)(textNode: ContentText)<br>Renders text with decorators. |
| [decorators](decorators.md) | private fun [decorators](decorators.md)(styles: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;Style&gt;): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Renders text style decorators. |
| [documentables](documentables.md) | private fun ContentPage.[documentables](documentables.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;Documentable&gt;<br>Returns documentables of this page. |
| [getInstanceAndSourceSets](get-instance-and-source-sets.md) | private fun [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;ContentDivergentInstance, DisplaySourceSet&gt;&gt;.[getInstanceAndSourceSets](get-instance-and-source-sets.md)(): [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;ContentDivergentInstance, [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;&gt;<br>An exact copy of CommonmarkRenderer.getInstanceAndSourceSets. |
| [htmlEscape](html-escape.md) | private fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[htmlEscape](html-escape.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Converts special characters to their corresponding HTML entities. |
| [insertBreakBetweenOverloads](insert-break-between-overloads.md) | private fun [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html).[insertBreakBetweenOverloads](insert-break-between-overloads.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Inserts an extra line break between overloaded entries. |
| [isAnnotation](is-annotation.md) | private fun ContentDRILink.[isAnnotation](is-annotation.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Checks whether this node links to an annotation. |
