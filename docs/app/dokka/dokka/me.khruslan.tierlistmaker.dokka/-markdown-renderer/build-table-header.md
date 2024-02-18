//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownRenderer](index.md)/[buildTableHeader](build-table-header.md)

# buildTableHeader

private fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildTableHeader](build-table-header.md)(table: ContentTable, context: ContentPage)

Renders table header.

If there are less header nodes than children nodes in the table, inserts additional empty headers.

#### Receiver

Current string builder.

#### Parameters

| | |
|---|---|
| table | Table node to render. |
| context | Context of the page. |
