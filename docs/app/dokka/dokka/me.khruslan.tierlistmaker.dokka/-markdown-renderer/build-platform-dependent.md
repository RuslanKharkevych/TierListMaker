//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownRenderer](index.md)/[buildPlatformDependent](build-platform-dependent.md)

# buildPlatformDependent

open override fun [StringBuilder](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-string-builder/index.html).[buildPlatformDependent](build-platform-dependent.md)(content: PlatformHintedContent, pageContext: ContentPage, sourceSetRestriction: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;DisplaySourceSet&gt;?)

Builds platform dependent without source set tags.

Source set tags are not necessary because all code is written for the same platform.

#### Receiver

Current string builder.

#### Parameters

| | |
|---|---|
| content | Content to render. |
| pageContext | Context of the page. |
| sourceSetRestriction | Available source sets. |
