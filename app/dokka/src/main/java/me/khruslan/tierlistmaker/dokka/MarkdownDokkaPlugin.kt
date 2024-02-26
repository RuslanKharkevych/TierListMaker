package me.khruslan.tierlistmaker.dokka

import org.jetbrains.dokka.CoreExtensions
import org.jetbrains.dokka.gfm.GfmPlugin
import org.jetbrains.dokka.gfm.renderer.CommonmarkRenderer
import org.jetbrains.dokka.plugability.DokkaPlugin
import org.jetbrains.dokka.plugability.DokkaPluginApiPreview
import org.jetbrains.dokka.plugability.Extension
import org.jetbrains.dokka.plugability.PluginApiPreviewAcknowledgement
import org.jetbrains.dokka.renderers.Renderer

/**
 * Customized [Dokka](https://kotlinlang.org/docs/dokka-introduction.html) plugin for rendering docs
 * in markdown format.
 *
 * The motivation behind this plugin is to improve quality of the documentation in GFM markdown
 * format and customize it to the project needs.
 *
 * @constructor Should not be called from code. All plugins are automatically loaded during Dokka
 * setup.
 */
class MarkdownDokkaPlugin : DokkaPlugin() {

    /**
     * Overrides [CommonmarkRenderer] with [MarkdownRenderer].
     *
     * Renderer defines rules on what to do with pages and their content, which files to create and
     * how to display it properly.
     */
    @Suppress("unused")
    val renderer: Extension<Renderer, *, *> by extending {
        CoreExtensions.renderer providing ::MarkdownRenderer override plugin<GfmPlugin>().renderer
    }

    /**
     * Acknowledgement for empty methods that inform users about [DokkaPluginApiPreview].
     *
     * Also, it allows to not propagates the annotation in IDE by default when a user autogenerate
     * methods.
     *
     * @return Acknowledgement singleton instance.
     */
    @DokkaPluginApiPreview
    override fun pluginApiPreviewAcknowledgement(): PluginApiPreviewAcknowledgement {
        return PluginApiPreviewAcknowledgement
    }
}