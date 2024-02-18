//[app](../../index.md)/[me.khruslan.tierlistmaker.presentation.models.drag](index.md)

# Package-level declarations

UI states and models related to dragging.

## Types

| Name | Summary |
|---|---|
| [DragLocation](-drag-location/index.md) | data class [DragLocation](-drag-location/index.md)(val target: [DragData](../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md), val positionInTarget: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html))<br>Model that contains general information about the drag target. |
| [DragState](-drag-state/index.md) | enum [DragState](-drag-state/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[DragState](-drag-state/index.md)&gt; <br>UI state of dragging. |
| [TargetLocation](-target-location/index.md) | data class [TargetLocation](-target-location/index.md)(val adapterPosition: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val verticalOffset: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Model that contains specific information about the drag target. |
