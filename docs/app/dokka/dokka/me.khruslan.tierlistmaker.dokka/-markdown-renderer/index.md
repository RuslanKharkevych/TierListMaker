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
| [buildDRILink](build-d-r-i-link.md) | open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildDRILink](build-d-r-i-link.md)(node: ContentDRILink, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)<br>Builds DRI link, excluding generated code. |
| [buildList](build-list.md) | open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildList](build-list.md)(node: ContentList, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)<br>Builds list, making sure nested lists are ordered correctly. |
| [buildParagraph](build-paragraph.md) | private fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildParagraph](build-paragraph.md)()<br>Builds a paragraph that consists of the two blank lines. |
| [buildPlatformDependent](build-platform-dependent.md) | open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildPlatformDependent](build-platform-dependent.md)(content: PlatformHintedContent, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)<br>Builds platform dependent without source set tags. |
| [buildTable](build-table.md) | open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildTable](build-table.md)(node: ContentTable, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)<br>Builds table, making sure that all available contents are shown. |
| [buildTableCells](build-table-cells.md) | private fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildTableCells](build-table-cells.md)(table: ContentTable, context: ContentPage)<br>Renders table cells. |
| [buildTableHeader](build-table-header.md) | private fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildTableHeader](build-table-header.md)(table: ContentTable, context: ContentPage)<br>Renders table header. |
| [getInstanceAndSourceSets](get-instance-and-source-sets.md) | private fun [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;ContentDivergentInstance, DisplaySourceSet&gt;&gt;.[getInstanceAndSourceSets](get-instance-and-source-sets.md)(): [Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;ContentDivergentInstance, [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;&gt;<br>An exact copy of CommonmarkRenderer.getInstanceAndSourceSets. |
