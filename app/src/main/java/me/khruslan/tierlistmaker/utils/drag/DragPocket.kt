package me.khruslan.tierlistmaker.utils.drag

import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import timber.log.Timber

class DragPocket {
    var shadow: ImageDragData? = null
        get() {
            if (field == null) Timber.e("get shadow: shadow is null")
            return field.also { field = null }
        }
        set(value) {
            if (field != null) Timber.e("set shadow: $field is not null")
            field = value
        }

    var target: DragData? = null
        get() = field.also { field = null }
        set(value) {
            field = value
            if (value != null) cachedTarget = value
        }

    var cachedTarget: DragData? = null
        get() {
            if (field == null) Timber.e("get cachedTarget: cachedTarget is null")
            return field.also { field = null }
        }
}