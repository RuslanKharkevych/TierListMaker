//[lint](../../../index.md)/[me.khruslan.tierlistmaker.lint.detectors](../index.md)/[FlexboxLayoutManagerUsageDetector](index.md)

# FlexboxLayoutManagerUsageDetector

class [FlexboxLayoutManagerUsageDetector](index.md) : Detector, SourceCodeScanner

Detects usages of the [FlexboxLayoutManager](https://github.com/google/flexbox-layout) constructor and shows a warning with a suggestion to replace it with [FlexLayoutManager](https://github.com/RuslanKharkevych/TierListMaker/blob/master/app/src/main/java/me/khruslan/tierlistmaker/presentation/utils/recyclerview/FlexLayoutManager.kt).

The motivation of this detector is to prevent a crash caused by a [bug](https://github.com/google/flexbox-layout/issues/568) in FlexboxLayoutManager.

## Constructors

| | |
|---|---|
| [FlexboxLayoutManagerUsageDetector](-flexbox-layout-manager-usage-detector.md) | constructor()<br>Default constructor. Should not be called from the library code. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | object [Constants](-constants/index.md)<br>Contains [ISSUE](-constants/-i-s-s-u-e.md) and other constants of this detector. |

## Properties

| Name | Summary |
|---|---|
| [relativeFilePath](relative-file-path.md) | private val JavaContext.[relativeFilePath](relative-file-path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A path of the JavaContext.file relative to the project directory. |
| [visitedNodes](visited-nodes.md) | private val [visitedNodes](visited-nodes.md): [MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)&lt;UCallExpression&gt;<br>A set of nodes that are already visited. |

## Functions

| Name | Summary |
|---|---|
| [getApplicableConstructorTypes](get-applicable-constructor-types.md) | open override fun [getApplicableConstructorTypes](get-applicable-constructor-types.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Returns FlexboxLayoutManager constructor type. |
| [visitConstructor](visit-constructor.md) | open override fun [visitConstructor](visit-constructor.md)(context: JavaContext, node: UCallExpression, constructor: PsiMethod)<br>Reports the issue if it's applicable to a given AST node. |
