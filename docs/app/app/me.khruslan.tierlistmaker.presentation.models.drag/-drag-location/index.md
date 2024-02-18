//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.models.drag](../index.md)/[DragLocation](index.md)

# DragLocation

data class [DragLocation](index.md)(val target: [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md), val positionInTarget: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html))

Model that contains general information about the drag target.

Suitable for all target types. In some cases can be mapped to [TargetLocation](../-target-location/index.md).

#### See also

| |
|---|
| [TargetLocation](../-target-location/index.md) |

## Constructors

| | |
|---|---|
| [DragLocation](-drag-location.md) | constructor(target: [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md), positionInTarget: [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html))<br>Creates a new drag location instance. |

## Properties

| Name | Summary |
|---|---|
| [positionInTarget](position-in-target.md) | val [positionInTarget](position-in-target.md): [PointF](https://developer.android.com/reference/kotlin/android/graphics/PointF.html)<br>Position of the cursor relative to the target. |
| [target](target.md) | val [target](target.md): [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md)<br>Drag target info. |
