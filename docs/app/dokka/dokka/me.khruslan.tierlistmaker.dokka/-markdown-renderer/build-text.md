//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownRenderer](index.md)/[buildText](build-text.md)

# buildText

open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildText](build-text.md)(textNode: ContentText)

Builds text, making sure annotations are rendered with leading space when necessary.

Fixes the respective issue in CommonmarkRenderer.

#### Receiver

Current string builder.

#### Parameters

| | |
|---|---|
| textNode | A text node to render. |
