//[app](../../../index.md)/[me.khruslan.tierlistmaker.data.providers.drag](../index.md)/[DragPocketImpl](index.md)/[cachedTarget](cached-target.md)

# cachedTarget

open override var [cachedTarget](cached-target.md): [DragData](../../me.khruslan.tierlistmaker.data.models.drag/-drag-data/index.md)?

The &quot;cached&quot; target is the last non-nullable target.

Once cached target is read, the field is cleared. If it's read before it's initialized, returns null and logs error.
