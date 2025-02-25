package me.khruslan.tierlistmaker.dokka

import org.jetbrains.dokka.gfm.GfmCommand.Companion.templateCommand
import org.jetbrains.dokka.gfm.ResolveLinkGfmCommand
import org.jetbrains.dokka.gfm.renderer.CommonmarkRenderer
import org.jetbrains.dokka.model.DisplaySourceSet
import org.jetbrains.dokka.model.Documentable
import org.jetbrains.dokka.pages.ContentDRILink
import org.jetbrains.dokka.pages.ContentDivergentGroup
import org.jetbrains.dokka.pages.ContentDivergentInstance
import org.jetbrains.dokka.pages.ContentGroup
import org.jetbrains.dokka.pages.ContentKind
import org.jetbrains.dokka.pages.ContentList
import org.jetbrains.dokka.pages.ContentPage
import org.jetbrains.dokka.pages.ContentTable
import org.jetbrains.dokka.pages.ContentText
import org.jetbrains.dokka.pages.HtmlContent
import org.jetbrains.dokka.pages.MemberPage
import org.jetbrains.dokka.pages.PlatformHintedContent
import org.jetbrains.dokka.pages.Style
import org.jetbrains.dokka.pages.TextStyle
import org.jetbrains.dokka.pages.WithDocumentables
import org.jetbrains.dokka.plugability.DokkaContext

/**
 * Renders documentation in markdown format.
 *
 * Customizes the default GFM markdown renderer and fixes some of its issues.
 *
 * @param context Dokka context.
 * @constructor Creates a new markdown renderer in a provided context.
 */
class MarkdownRenderer(context: DokkaContext) : CommonmarkRenderer(context) {

    /**
     * Builds platform dependent without source set tags.
     *
     * Source set tags are not necessary because all code is written for the same platform.
     *
     * @param content Content to render.
     * @param pageContext Context of the page.
     * @param sourceSetRestriction Available source sets.
     * @receiver Current string builder.
     */
    override fun StringBuilder.buildPlatformDependent(
        content: PlatformHintedContent,
        pageContext: ContentPage,
        sourceSetRestriction: Set<DisplaySourceSet>?
    ) {
        val innerContent = content.inner
        val sourceSets = content.sourceSets

        if (innerContent is ContentGroup &&
            innerContent.children.firstOrNull { it is ContentTable } != null
        ) {
            buildContentNode(innerContent, pageContext, sourceSets)
        } else {
            val distinct = sourceSets.map {
                it to buildString { buildContentNode(innerContent, pageContext, setOf(it)) }
            }.groupBy(Pair<DisplaySourceSet, String>::second, Pair<DisplaySourceSet, String>::first)

            distinct.filter { it.key.isNotBlank() }.forEach { (text, _) ->
                buildParagraph()
                append(text.trim())
                buildParagraph()
            }
        }
    }

    /**
     * Builds divergent without source set tags.
     *
     * Source set tags are not necessary because all code is written for the same platform.
     * Additionally, this function inserts divider between overloaded etries.
     *
     * @param node A node to render.
     * @param pageContext Context of the page.
     * @receiver Current string builder.
     */
    override fun StringBuilder.buildDivergent(
        node: ContentDivergentGroup,
        pageContext: ContentPage
    ) {
        val distinct = node.groupDivergentInstances(
            pageContext = pageContext,
            beforeTransformer = { instance, _, sourceSet ->
                instance.before?.let { before ->
                    buildString { buildContentNode(before, pageContext, sourceSet) }
                } ?: ""
            },
            afterTransformer = { instance, _, sourceSet ->
                instance.after?.let { after ->
                    buildString { buildContentNode(after, pageContext, sourceSet) }
                } ?: ""
            }
        )

        distinct.values.forEachIndexed { index, entry ->
            val (instance, sourceSets) = entry.getInstanceAndSourceSets()
            buildParagraph()

            instance.before?.let {
                buildContentNode(it, pageContext, sourceSets.first())
                buildParagraph()
            }

            entry.groupBy {
                buildString {
                    buildContentNode(it.first.divergent, pageContext, setOf(it.second))
                }
            }.values.forEach { innerEntry ->
                val (innerInstance, innerSourceSets) = innerEntry.getInstanceAndSourceSets()
                innerInstance.divergent.build(
                    builder = this@buildDivergent,
                    pageContext = pageContext,
                    sourceSetRestriction = setOf(innerSourceSets.first())
                )
                buildParagraph()
            }

            instance.after?.let {
                buildContentNode(it, pageContext, sourceSets.first())
            }

            if (node.dci.kind == ContentKind.Main && index != distinct.size - 1) {
                if (pageContext is MemberPage && pageContext.documentables().size > 1) {
                    buildDivider()
                } else {
                    buildParagraph()
                }
            }
        }
    }

    /**
     * Builds table, making sure that all available contents are shown.
     *
     * Fixes the issue in the [CommonmarkRenderer], where table cells are not populated if header is
     * empty.
     *
     * @param node A table node to render.
     * @param pageContext Context of the page.
     * @param sourceSetRestriction Available source sets.
     * @receiver Current string builder.
     */
    override fun StringBuilder.buildTable(
        node: ContentTable,
        pageContext: ContentPage,
        sourceSetRestriction: Set<DisplaySourceSet>?
    ) {
        buildTableHeader(node, pageContext)
        buildTableCells(node, pageContext)
    }

    /**
     * Builds list, making sure nested lists are ordered correctly.
     *
     * Fixes the issue in the [CommonmarkRenderer], where nested lists were not ordered as expected.
     *
     * @param node A list node to render.
     * @param pageContext Context of the page.
     * @param sourceSetRestriction Available source sets.
     * @receiver Current string builder.
     */
    override fun StringBuilder.buildList(
        node: ContentList,
        pageContext: ContentPage,
        sourceSetRestriction: Set<DisplaySourceSet>?
    ) {
        buildParagraph()

        node.children.forEachIndexed { index, item ->
            when {
                item is ContentList -> append("\n   ")
                node.ordered -> append("${index + 1}. ")
                else -> append("- ")
            }

            buildString { item.build(this, pageContext, item.sourceSets) }
                .replace("\n", "\n   ")
                .trim().let { append(it) }
            appendLine()
        }

        buildParagraph()
    }

    /**
     * Builds DRI link, excluding generated code.
     *
     * View binding and safe args classes are generated and should not be documented. Fixes issue
     * in [CommonmarkRenderer] where no trailing space is added after an annotation.
     *
     * @param node DRI link node to render.
     * @param pageContext Context of the page.
     * @param sourceSetRestriction Available source sets.
     * @receiver Current string builder.
     */
    override fun StringBuilder.buildDRILink(
        node: ContentDRILink,
        pageContext: ContentPage,
        sourceSetRestriction: Set<DisplaySourceSet>?
    ) {
        val location = locationProvider.resolve(node.address, node.sourceSets, pageContext)

        when {
            location?.contains("generated") == true -> {
                buildText(node.children, pageContext, sourceSetRestriction)
            }

            location == null -> {
                val isPartial = context.configuration.delayTemplateSubstitution
                if (isPartial) {
                    templateCommand(ResolveLinkGfmCommand(node.address)) {
                        buildText(node.children, pageContext, sourceSetRestriction)
                    }
                } else {
                    buildText(node.children, pageContext, sourceSetRestriction)
                }
            }

            else -> {
                buildLink(location) {
                    buildText(node.children, pageContext, sourceSetRestriction)
                }
                if (node.isAnnotation()) append(" ")
            }
        }
    }

    /**
     * Builds text, making sure annotations are rendered with leading space when necessary.
     *
     * Fixes the respective issue in [CommonmarkRenderer].
     *
     * @param textNode A text node to render.
     * @receiver Current string builder.
     */
    override fun StringBuilder.buildText(textNode: ContentText) {
        if (textNode.text == "@" && endsWith(")")) append(" ")
        buildTextWithDecorators(textNode)
    }

    /**
     * Renders text with decorators.
     *
     * An exact copy of [CommonmarkRenderer.buildText]. Can't call super because of Kotlin language
     * limitation (see [KT-11488](https://youtrack.jetbrains.com/issue/KT-11488)).
     *
     * @receiver Current string builder.
     * @param textNode Text node to render.
     */
    private fun StringBuilder.buildTextWithDecorators(textNode: ContentText) {
        if (textNode.extra[HtmlContent] != null) {
            append(textNode.text)
        } else if (textNode.text.isNotBlank()) {
            val decorators = decorators(textNode.style)
            append(textNode.text.takeWhile { it == ' ' })
            append(decorators)
            append(textNode.text.trim().htmlEscape())
            append(decorators.reversed())
            append(textNode.text.takeLastWhile { it == ' ' })
        }
    }

    /**
     * Renders text style decorators.
     *
     * Supported styles: bold, italic, strong and strikethrough.
     *
     * @param styles Text styles to render.
     * @return Rendered decorators.
     */
    private fun decorators(styles: Set<Style>): String {
        return buildString {
            styles.forEach { style ->
                when (style) {
                    TextStyle.Bold -> append("**")
                    TextStyle.Italic -> append("*")
                    TextStyle.Strong -> append("**")
                    TextStyle.Strikethrough -> append("~~")
                    else -> Unit
                }
            }
        }
    }

    /**
     * Converts special characters to their corresponding HTML entities.
     *
     * @receiver Input string.
     * @return Output string.
     */
    private fun String.htmlEscape(): String {
        return replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
    }

    /**
     * Renders table header.
     *
     * If there are less header nodes than children nodes in the table, inserts additional empty
     * headers.
     *
     * @param table Table node to render.
     * @param context Context of the page.
     * @receiver Current string builder.
     */
    private fun StringBuilder.buildTableHeader(table: ContentTable, context: ContentPage) {
        appendLine()
        val headerNodesCount = table.header.firstOrNull()?.children?.size ?: 0
        val childrenNodesCount = table.children.firstOrNull()?.children?.size ?: 0

        table.header.forEach { row ->
            row.children.forEach { cell ->
                append("| ")
                cell.build(this, context, cell.sourceSets)
                append(" ")
            }
        }
        if (childrenNodesCount > headerNodesCount) {
            append("| ".repeat(childrenNodesCount - headerNodesCount))
        }
        append("|")
        appendLine()

        append("|---".repeat(maxOf(headerNodesCount, childrenNodesCount)))
        append("|")
        appendLine()
    }

    /**
     * Renders table cells.
     *
     * For better readability, splits overloaded entries with an extra line break.
     *
     * @param table Table to render.
     * @param context Context of the page.
     * @receiver Current string builder.
     */
    private fun StringBuilder.buildTableCells(table: ContentTable, context: ContentPage) {
        table.children.forEach { row ->
            row.children.forEach { cell ->
                append("| ")
                append(buildString { cell.build(this, context) }
                    .trim()
                    .replace("#+ ".toRegex(), "")
                    .replace("\\\n", "\n\n")
                    .replace("\n\n+".toRegex(), "<br>")
                    .replace("\n", " ")
                    .insertBreakBetweenOverloads()
                )
                append(" ")
            }
            append("|")
            appendLine()
        }
    }

    /**
     * Inserts an extra line break between overloaded entries.
     *
     * The algorithm steps:
     * 1. Split the input string into lines.
     * 2. Exclude lines with annotations.
     * 3. If there are less than 4 lines, return input string.
     * 4. Add an extra break after each description.
     * 5. Remove the trailing break.
     *
     * @receiver Input string containing method (constructor) signatures and descriptions.
     * @return Output string with divided overloaded entries.
     */
    private fun String.insertBreakBetweenOverloads(): String {
        val lines = split("<br>").filter { !it.startsWith("@") }
        if (lines.count() < 4) return this

        return lines.joinToString("<br>") { line ->
            val isConstructor = line.startsWith("constructor(")
            val isMethod = line.contains("fun [")

            if (!isConstructor && !isMethod) {
                "$line<br>"
            } else {
                line
            }
        }.removeSuffix("<br>")
    }

    /**
     * Checks whether this node links to an annotation.
     *
     * Compares node's fully-qualified class name for the exact match. The list of annotations to
     * compare with is hardcoded. To support correct rendering of new annotations, they must be
     * listed here.
     *
     * @receiver Link node.
     * @return Whether this node links to a supported annotation.
     */
    private fun ContentDRILink.isAnnotation(): Boolean {
        val className = "${address.packageName}.${address.classNames}"
        val annotationClasses = listOf(
            "androidx.annotation.AttrRes",
            "androidx.annotation.ColorInt",
            "androidx.annotation.DrawableRes",
            "androidx.annotation.IdRes",
            "androidx.annotation.StringRes",
            "javax.inject.Inject",
        )
        return className in annotationClasses
    }

    /**
     * Builds a paragraph that consists of the two blank lines.
     *
     * @receiver Current string builder.
     */
    private fun StringBuilder.buildParagraph() {
        appendLine()
        appendLine()
    }

    /**
     * Builds a divider surrounded by line breaks.
     *
     * @receiver Current string builder.
     */
    private fun StringBuilder.buildDivider() {
        append("<br>\n---\n<br>")
    }

    /**
     * Returns documentables of this page.
     *
     * Documentables represent data that is parsed from sources.
     *
     * @receiver Context of the page.
     * @return The list of documentables. Can be empty if page doesn't contain any documentables.
     */
    private fun ContentPage.documentables(): List<Documentable> {
        return (this as? WithDocumentables)?.documentables ?: emptyList()
    }

    /**
     * An exact copy of [CommonmarkRenderer.getInstanceAndSourceSets].
     *
     * @receiver Original instance and source sets.
     * @return Mapped instance and source sets.
     */
    private fun List<Pair<ContentDivergentInstance, DisplaySourceSet>>.getInstanceAndSourceSets():
            Pair<ContentDivergentInstance, Set<DisplaySourceSet>> {
        return Pair(first().first, map { it.second }.toSet())
    }
}