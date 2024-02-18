package me.khruslan.tierlistmaker.dokka

import org.jetbrains.dokka.DokkaConfigurationImpl
import org.jetbrains.dokka.DokkaSourceSetID
import org.jetbrains.dokka.Platform
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.renderers.RootCreator
import org.jetbrains.dokka.base.resolvers.external.DefaultExternalLocationProviderFactory
import org.jetbrains.dokka.base.resolvers.external.javadoc.JavadocExternalLocationProviderFactory
import org.jetbrains.dokka.gfm.GfmPlugin
import org.jetbrains.dokka.gfm.location.MarkdownLocationProvider
import org.jetbrains.dokka.links.DRI
import org.jetbrains.dokka.pages.ContentDivergentGroup
import org.jetbrains.dokka.pages.ContentEmbeddedResource
import org.jetbrains.dokka.pages.ContentKind
import org.jetbrains.dokka.pages.DCI
import org.jetbrains.dokka.pages.TextStyle
import org.jetbrains.dokka.testApi.context.MockContext
import org.junit.Assert.assertEquals
import org.junit.Test
import renderers.RawTestPage
import renderers.RenderingOnlyTestBase
import renderers.testPage
import testApi.testRunner.defaultSourceSet
import utils.TestOutputWriter
import java.io.File

@Suppress("RemoveRedundantBackticks")
class MarkdownRendererTest : RenderingOnlyTestBase<String>() {

    private val files = TestOutputWriter()

    override val context = MockContext(
        DokkaBase().outputWriter to { files },
        DokkaBase().locationProviderFactory to MarkdownLocationProvider::Factory,
        DokkaBase().externalLocationProviderFactory to ::JavadocExternalLocationProviderFactory,
        DokkaBase().externalLocationProviderFactory to ::DefaultExternalLocationProviderFactory,
        GfmPlugin().gfmPreprocessors to { RootCreator },

        testConfiguration = DokkaConfigurationImpl(moduleName = "root", finalizeCoroutines = false)
    )

    override val renderedContent: String by lazy {
        files.contents.getValue("test-page.md")
    }

    // region Code Wrapping
    @Test
    fun `Wrapped code block`() {
        val page = testPage {
            codeBlock {
                text("fun myCode(): String")
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |```kotlin
                        |fun myCode(): String
                        |```""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Should preserve original text without escaping`() {
        val page = testPage {
            codeBlock {
                text("<----> **text** & ~~this~~  and \"that\"")
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |```kotlin
                        |<----> **text** & ~~this~~  and "that"
                        |```""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }


    @Test
    fun `Wrapped inline code`() {
        val page = testPage {
            text("This function adds the values of ")
            codeInline {
                text("left")
            }
            text(" and ")
            codeInline {
                text("right")
            }
            text(".\nBoth numbers must be finite, or an exception occurs.\n")
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |This function adds the values of `left` and `right`.
                        |Both numbers must be finite, or an exception occurs.""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Should not add trailing backslash to newline elements for code inline code`() {
        val page = testPage {
            text("This adds some symbols (")
            codeInline {
                text("<----> **text** & ~~this~~  and \"that\"")
            }
            text(") to the test")
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |This adds some symbols (`<----> **text** & ~~this~~  and "that"`) to the test""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }
    // endregion Code Wrapping
    
    // region Divergent
    private val js = defaultSourceSet.copy(
        "js",
        DokkaSourceSetID("root", "js"),
        analysisPlatform = Platform.js,
        sourceRoots = setOf(File("pl1"))
    )
    private val jvm = defaultSourceSet.copy(
        "jvm",
        DokkaSourceSetID("root", "jvm"),
        analysisPlatform = Platform.jvm,
        sourceRoots = setOf(File("pl1"))
    )
    private val native = defaultSourceSet.copy(
        "native",
        DokkaSourceSetID("root", "native"),
        analysisPlatform = Platform.native,
        sourceRoots = setOf(File("pl1"))
    )

    @Test
    fun `Simple wrapping case`() {
        val page = testPage {
            divergentGroup(ContentDivergentGroup.GroupID("test")) {
                instance(setOf(DRI("test", "Test")), setOf(js)) {
                    divergent {
                        text("a")
                    }
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `No platform hint case`() {
        val page = testPage {
            divergentGroup(
                ContentDivergentGroup.GroupID("test"),
                implicitlySourceSetHinted = false
            ) {
                instance(setOf(DRI("test", "Test")), setOf(js)) {
                    divergent {
                        text("a")
                    }
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Divergent between source sets`() {
        val page = testPage {
            divergentGroup(ContentDivergentGroup.GroupID("test")) {
                instance(setOf(DRI("test", "Test")), setOf(js)) {
                    divergent {
                        text("a")
                    }
                }
                instance(setOf(DRI("test", "Test")), setOf(jvm)) {
                    divergent {
                        text("b")
                    }
                }
                instance(setOf(DRI("test", "Test")), setOf(native)) {
                    divergent {
                        text("c")
                    }
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a
                        |
                        |b
                        |
                        |c""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Divergent in one source set`() {
        val page = testPage {
            divergentGroup(ContentDivergentGroup.GroupID("test")) {
                instance(setOf(DRI("test", "Test")), setOf(js)) {
                    divergent {
                        text("a")
                    }
                }
                instance(setOf(DRI("test", "Test2")), setOf(js)) {
                    divergent {
                        text("b")
                    }
                }
                instance(setOf(DRI("test", "Test3")), setOf(js)) {
                    divergent {
                        text("c")
                    }
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a
                        |
                        |b
                        |
                        |c""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Divergent in and between source sets`() {
        val page = testPage {
            divergentGroup(ContentDivergentGroup.GroupID("test")) {
                instance(setOf(DRI("test", "Test")), setOf(native)) {
                    divergent {
                        text("a")
                    }
                }
                instance(setOf(DRI("test", "Test")), setOf(js)) {
                    divergent {
                        text("b")
                    }
                }
                instance(setOf(DRI("test", "Test")), setOf(jvm)) {
                    divergent {
                        text("c")
                    }
                }
                instance(setOf(DRI("test", "Test2")), setOf(js)) {
                    divergent {
                        text("d")
                    }
                }
                instance(setOf(DRI("test", "Test3")), setOf(native)) {
                    divergent {
                        text("e")
                    }
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a
                        |
                        |b
                        |
                        |c
                        |
                        |d
                        |
                        |e""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Divergent in and between source sets with grouping`() {
        val page = testPage {
            divergentGroup(ContentDivergentGroup.GroupID("test")) {
                instance(setOf(DRI("test", "Test")), setOf(native)) {
                    divergent {
                        text("a")
                    }
                    after {
                        text("a+")
                    }
                }
                instance(setOf(DRI("test", "Test")), setOf(js)) {
                    divergent {
                        text("b")
                    }
                    after {
                        text("bd+")
                    }
                }
                instance(setOf(DRI("test", "Test")), setOf(jvm)) {
                    divergent {
                        text("c")
                    }
                }
                instance(setOf(DRI("test", "Test2")), setOf(js)) {
                    divergent {
                        text("d")
                    }
                    after {
                        text("bd+")
                    }
                }
                instance(setOf(DRI("test", "Test3")), setOf(native)) {
                    divergent {
                        text("e")
                    }
                    after {
                        text("e+")
                    }
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a
                        |
                        |a+
                        |
                        |b
                        |
                        |d
                        |
                        |bd+
                        |
                        |c
                        |
                        |e
                        |
                        |e+""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Divergent same before`() {
        val page = testPage {
            divergentGroup(ContentDivergentGroup.GroupID("test")) {
                instance(setOf(DRI("test", "Test")), setOf(native)) {
                    before {
                        text("ab-")
                    }
                    divergent {
                        text("a")
                    }
                }
                instance(setOf(DRI("test", "Test2")), setOf(native)) {
                    before {
                        text("ab-")
                    }
                    divergent {
                        text("b")
                    }
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |ab-
                        |
                        |a
                        |
                        |b""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Divergent same after`() {
        val page = testPage {
            divergentGroup(ContentDivergentGroup.GroupID("test")) {
                instance(setOf(DRI("test", "Test")), setOf(native)) {
                    divergent {
                        text("a")
                    }
                    after {
                        text("ab+")
                    }
                }
                instance(setOf(DRI("test", "Test2")), setOf(native)) {
                    divergent {
                        text("b")
                    }
                    after {
                        text("ab+")
                    }
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a
                        |
                        |b
                        |
                        |ab+""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Divergent grouped by before and after`() {
        val page = testPage {
            divergentGroup(ContentDivergentGroup.GroupID("test")) {
                instance(setOf(DRI("test", "Test")), setOf(native)) {
                    before {
                        text("ab-")
                    }
                    divergent {
                        text("a")
                    }
                    after {
                        text("ab+")
                    }
                }
                instance(setOf(DRI("test", "Test2")), setOf(native)) {
                    before {
                        text("ab-")
                    }
                    divergent {
                        text("b")
                    }
                    after {
                        text("ab+")
                    }
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |ab-
                        |
                        |a
                        |
                        |b
                        |
                        |ab+""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Divergent different before and after`() {
        val page = testPage {
            divergentGroup(ContentDivergentGroup.GroupID("test")) {
                instance(setOf(DRI("test", "Test")), setOf(native)) {
                    before {
                        text("a-")
                    }
                    divergent {
                        text("a")
                    }
                    after {
                        text("ab+")
                    }
                }
                instance(setOf(DRI("test", "Test2")), setOf(native)) {
                    before {
                        text("b-")
                    }
                    divergent {
                        text("b")
                    }
                    after {
                        text("ab+")
                    }
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a-
                        |
                        |a
                        |
                        |ab+
                        |
                        |b-
                        |
                        |b
                        |
                        |ab+""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Divergent in and between source sets with grouping and common parts`() {
        val page = testPage {
            divergentGroup(ContentDivergentGroup.GroupID("test")) {
                instance(setOf(DRI("test", "Test")), setOf(native)) {
                    divergent {
                        text("a")
                    }
                    after {
                        text("a+")
                    }
                }
                instance(setOf(DRI("test", "Test")), setOf(js)) {
                    divergent {
                        text("b")
                    }
                    after {
                        text("bd+")
                    }
                }
                instance(setOf(DRI("test", "Test")), setOf(jvm)) {
                    divergent {
                        text("c")
                    }
                    after {
                        text("bd+")
                    }
                }
                instance(setOf(DRI("test", "Test2")), setOf(js)) {
                    divergent {
                        text("d")
                    }
                    after {
                        text("bd+")
                    }
                }
                instance(setOf(DRI("test", "Test3")), setOf(native)) {
                    divergent {
                        text("e")
                    }
                    after {
                        text("e+")
                    }
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a
                        |
                        |a+
                        |
                        |b
                        |
                        |c
                        |
                        |d
                        |
                        |bd+
                        |
                        |e
                        |
                        |e+""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }
    // endregion Divergent
    
    // region Group Wrapping
    @Test
    fun `Not wrapped`() {
        val page = testPage {
            group {
                text("a")
                text("b")
            }
            text("c")
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |abc""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Paragraph wrapped`() {
        val page = testPage {
            group(styles = setOf(TextStyle.Paragraph)) {
                text("a")
                text("b")
            }
            text("c")
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |ab
                        |
                        |c""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Block wrapped`() {
        val page = testPage {
            group(styles = setOf(TextStyle.Block)) {
                text("a")
                text("b")
            }
            text("c")
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |ab
                        |
                        |c""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Nested`() {
        val page = testPage {
            group(styles = setOf(TextStyle.Block)) {
                text("a")
                group(styles = setOf(TextStyle.Block)) {
                    group(styles = setOf(TextStyle.Block)) {
                        text("b")
                        text("c")
                    }
                }
                text("d")
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a
                        |
                        |bc
                        |
                        |d""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }
    // endregion Group Wrapping

    // region Simple Elements
    @Test
    fun `Header`() {
        val page = testPage {
            header(1, "The Hobbit or There and Back Again")
        }
        val expect = "//[testPage](test-page.md)\n\n# The Hobbit or There and Back Again"
        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Link`() {
        val page = testPage {
            link("They are not all accounted for, the lost Seeing Stones.", "http://www.google.com")
        }
        val expect =
            "//[testPage](test-page.md)\n\n[They are not all accounted for, the lost Seeing Stones.](http://www.google.com)"
        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Bold`() {
        val page = testPage {
            text(
                "That there’s some good in this world, Mr. Frodo… and it’s worth fighting for.",
                styles = setOf(TextStyle.Bold)
            )
        }
        val expect =
            "//[testPage](test-page.md)\n\n**That there’s some good in this world, Mr. Frodo… and it’s worth fighting for.**"
        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Italic`() {
        val page = testPage {
            text("Even the smallest person can change the course of the future.", styles = setOf(TextStyle.Italic))
        }
        val expect = "//[testPage](test-page.md)\n\n*Even the smallest person can change the course of the future.*"
        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Italic and bold`() {
        val page = testPage {
            text(
                "There is no curse in Elvish, Entish, or the tongues of Men for this treachery.",
                styles = setOf(TextStyle.Bold, TextStyle.Italic)
            )
        }
        val expect =
            "//[testPage](test-page.md)\n\n***There is no curse in Elvish, Entish, or the tongues of Men for this treachery.***"
        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Strikethrough`() {
        val page = testPage {
            text(
                "A day may come when the courage of men fails… but it is not THIS day",
                styles = setOf(TextStyle.Strikethrough)
            )
        }
        val expect =
            "//[testPage](test-page.md)\n\n~~A day may come when the courage of men fails… but it is not THIS day~~"
        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Images`() {
        val image = ContentEmbeddedResource(
            children = emptyList(),
            address = "https://www.google.pl/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
            altText = "This is a google logo",
            dci = DCI(setOf(DRI.topLevel), ContentKind.Main),
            sourceSets = emptySet()
        )
        val page = RawTestPage(content = image)
        val expect =
            "//[testPage](test-page.md)\n\n![This is a google logo](https://www.google.pl/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png)"
        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Simple table with header`() {
        val page = testPage {
            table {
                header {
                    text("Col1")
                    text("Col2")
                    text("Col3")
                }
                row {
                    text("Text1")
                    text("Text2")
                    text("Text3")
                }
                row {
                    text("Text4")
                    text("Text5")
                    text("Text6")
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        || Col1 | Col2 | Col3 |
                        ||---|---|---|
                        || Text1 | Text2 | Text3 |
                        || Text4 | Text5 | Text6 |""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Simple table without header`() {
        val page = testPage {
            table {
                row {
                    text("Text1")
                    text("Text2")
                    text("Text3")
                }
                row {
                    text("Text4")
                    text("Text5")
                    text("Text6")
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        || | | |
                        ||---|---|---|
                        || Text1 | Text2 | Text3 |
                        || Text4 | Text5 | Text6 |""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Table with extra cell in row`() {
        val page = testPage {
            table {
                header {
                    text("Col1")
                }
                row {
                    text("Text1")
                    text("Text2")
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        || Col1 | |
                        ||---|---|
                        || Text1 | Text2 |""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Table with extra cell in header`() {
        val page = testPage {
            table {
                header {
                    text("Col1")
                    text("Col2")
                }
                row {
                    text("Text1")
                }
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        || Col1 | Col2 |
                        ||---|---|
                        || Text1 |""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Escape text`() {
        val page = testPage {
            text(
                "<b>a</b>",
            )
        }
        val expect =
            "//[testPage](test-page.md)\n\n&lt;b&gt;a&lt;/b&gt;"
        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Unordered list with two items`() {
        val page = testPage {
            unorderedList {
                item { text("Item 1") }
                item { text("Item 2") }
            }
        }

        val expect = """|//[testPage](test-page.md)
                        |
                        |- Item 1
                        |- Item 2""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Unordered list with styled text`() {
        val page = testPage {
            unorderedList {
                item {
                    text("Nobody", styles = setOf(TextStyle.Italic))
                    text(" tosses a Dwarf!")
                }
            }
        }

        val expect = "//[testPage](test-page.md)\n\n- *Nobody* tosses a Dwarf!"

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Ordered list with two items`() {
        val page = testPage {
            orderedList {
                item { text("Item 1") }
                item { text("Item 2") }
            }
        }

        val expect = """|//[testPage](test-page.md)
                        |
                        |1. Item 1
                        |2. Item 2""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Ordered list with nested unordered list`() {
        val page = testPage {
            orderedList {
                item {
                    text("And another list:")
                    unorderedList {
                        item { text("Item 1") }
                        item { text("Item 2") }
                    }
                }
                item { text("Following item") }
            }
        }

        val expect = """|//[testPage](test-page.md)
                        |
                        |1. And another list:
                        |   
                        |   - Item 1
                        |   - Item 2
                        |2. Following item""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Ordered list with nested table`() {
        val page = testPage {
            orderedList {
                item {
                    text("The following table is nested in a list:")
                    table {
                        header {
                            text("Col1")
                            text("Col2")
                        }
                        row {
                            text("Text1")
                            text("Text2")
                        }
                    }
                }
            }
        }

        val expect = """|//[testPage](test-page.md)
                        |
                        |1. The following table is nested in a list:
                        |   | Col1 | Col2 |
                        |   |---|---|
                        |   | Text1 | Text2 |""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Three levels of list`() {
        val page = testPage {
            unorderedList {
                item {
                    text("Level 1")
                    unorderedList {
                        item {
                            text("Level 2")
                            unorderedList {
                                item {
                                    text("Level 3")
                                }
                            }
                        }
                    }
                }
            }
        }

        // Extra newlines are not pretty but do not impact formatting
        val expect = """|//[testPage](test-page.md)
                        |
                        |- Level 1
                        |   
                        |   - Level 2
                        |      
                        |      - Level 3""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Nested list with no text preceding it`() {
        val page = testPage {
            unorderedList {
                item {
                    unorderedList {
                        item {
                            text("Nested")
                        }
                    }
                }
            }
        }

        val expect = """|//[testPage](test-page.md)
                        |
                        |- - Nested""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }
    // endregion Simple Elements

    // region Source Set Dependent Hint
    private val pl1 = defaultSourceSet.copy(
        "pl1",
        DokkaSourceSetID("root", "pl1"),
        analysisPlatform = Platform.js,
        sourceRoots = setOf(File("pl1"))
    )
    private val pl2 = defaultSourceSet.copy(
        "pl2",
        DokkaSourceSetID("root", "pl2"),
        analysisPlatform = Platform.jvm,
        sourceRoots = setOf(File("pl1"))
    )
    private val pl3 = defaultSourceSet.copy(
        "pl3",
        DokkaSourceSetID("root", "pl3"),
        analysisPlatform = Platform.native,
        sourceRoots = setOf(File("pl1"))
    )

    @Test
    fun `Platform independent case`() {
        val page = testPage {
            sourceSetDependentHint(sourceSets = setOf(pl1, pl2, pl3), styles = setOf(TextStyle.Block)) {
                text("a")
                text("b")
                text("c")
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |abc""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Completely divergent case`() {
        val page = testPage {
            sourceSetDependentHint(sourceSets = setOf(pl1, pl2, pl3), styles = setOf(TextStyle.Block)) {
                text("a", sourceSets = setOf(pl1))
                text("b", sourceSets = setOf(pl2))
                text("c", sourceSets = setOf(pl3))
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a
                        |
                        |b
                        |
                        |c""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Overlapping case`() {
        val page = testPage {
            sourceSetDependentHint(sourceSets = setOf(pl1, pl2), styles = setOf(TextStyle.Block)) {
                text("a", sourceSets = setOf(pl1))
                text("b", sourceSets = setOf(pl1, pl2))
                text("c", sourceSets = setOf(pl2))
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |ab
                        |
                        |bc""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Case that can be simplified`() {
        val page = testPage {
            sourceSetDependentHint(sourceSets = setOf(pl1, pl2), styles = setOf(TextStyle.Block)) {
                text("a", sourceSets = setOf(pl1, pl2))
                text("b", sourceSets = setOf(pl1))
                text("b", sourceSets = setOf(pl2))
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |ab""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Case with group breaking simplification`() {
        val page = testPage {
            sourceSetDependentHint(sourceSets = setOf(pl1, pl2), styles = setOf(TextStyle.Block)) {
                group(styles = setOf(TextStyle.Block)) {
                    text("a", sourceSets = setOf(pl1, pl2))
                    text("b", sourceSets = setOf(pl1))
                }
                text("b", sourceSets = setOf(pl2))
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |ab
                        |
                        |a
                        |
                        |b""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Case with group not breaking simplification`() {
        val page = testPage {
            sourceSetDependentHint(sourceSets = setOf(pl1, pl2)) {
                group {
                    text("a", sourceSets = setOf(pl1, pl2))
                    text("b", sourceSets = setOf(pl1))
                }
                text("b", sourceSets = setOf(pl2))
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |ab""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }

    @Test
    fun `Partially unified case`() {
        val page = testPage {
            sourceSetDependentHint(sourceSets = setOf(pl1, pl2, pl3), styles = setOf(TextStyle.Block)) {
                text("a", sourceSets = setOf(pl1))
                text("a", sourceSets = setOf(pl2))
                text("b", sourceSets = setOf(pl3))
            }
        }
        val expect = """|//[testPage](test-page.md)
                        |
                        |a
                        |
                        |b""".trimMargin()

        MarkdownRenderer(context).render(page)
        assertEquals(expect, renderedContent)
    }
    // endregion Source Set Dependent Hint
}