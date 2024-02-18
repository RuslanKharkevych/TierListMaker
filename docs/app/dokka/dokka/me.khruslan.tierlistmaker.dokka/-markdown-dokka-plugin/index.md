//[dokka](../../../index.md)/[me.khruslan.tierlistmaker.dokka](../index.md)/[MarkdownDokkaPlugin](index.md)

# MarkdownDokkaPlugin

class [MarkdownDokkaPlugin](index.md) : DokkaPlugin

Customized Dokka plugin for rendering docs in markdown format.

The motivation behind this plugin is to improve quality of the documentation in GFM markdown format and customize it to the project needs.

## Constructors

| | |
|---|---|
| [MarkdownDokkaPlugin](-markdown-dokka-plugin.md) | constructor()<br>Should not be called from code. All plugins are automatically loaded during Dokka setup. |

## Properties

| Name | Summary |
|---|---|
| [renderer](renderer.md) | val [renderer](renderer.md): Extension&lt;Renderer, *, *&gt;<br>Overrides CommonmarkRenderer with [MarkdownRenderer](../-markdown-renderer/index.md). |

## Functions

| Name | Summary |
|---|---|
| [pluginApiPreviewAcknowledgement](plugin-api-preview-acknowledgement.md) | protected open override fun [pluginApiPreviewAcknowledgement](plugin-api-preview-acknowledgement.md)(): PluginApiPreviewAcknowledgement<br>Acknowledgement for empty methods that inform users about DokkaPluginApiPreview. |
