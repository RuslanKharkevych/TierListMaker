//[lint](../../../index.md)/[me.khruslan.tierlistmaker.lint](../index.md)/[LintIssueRegistry](index.md)

# LintIssueRegistry

class [LintIssueRegistry](index.md) : IssueRegistry

Registry of the custom lint checks.

Make sure to add a com.android.tools.lint.client.api.IssueRegistry file in resources META-INF services. The file should contain a single line that has the name of this class with the full path.

## Constructors

| | |
|---|---|
| [LintIssueRegistry](-lint-issue-registry.md) | constructor()<br>Default constructor. Should not be called from the library code. |

## Types

| Name | Summary |
|---|---|
| [Constants](-constants/index.md) | private object [Constants](-constants/index.md)<br>Constants for internal usage. |

## Properties

| Name | Summary |
|---|---|
| [api](api.md) | open override val [api](api.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The Lint API version this issue registry's checks were compiled. |
| [issues](issues.md) | open override val [issues](issues.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;Issue&gt;<br>The list of issues that can be found by all known detectors. |
| [vendor](vendor.md) | open override val [vendor](vendor.md): Vendor<br>The vendor providing lint checks. |
