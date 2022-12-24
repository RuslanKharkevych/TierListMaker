package me.khruslan.tierlistmaker.fakes.utils.drag

import me.khruslan.tierlistmaker.data.models.drag.DragData
import me.khruslan.tierlistmaker.data.models.drag.ImageDragData
import me.khruslan.tierlistmaker.utils.drag.DragPocket

class FakeDragPocket : DragPocket {

    override var shadow: ImageDragData? = null
        get() = field.also { field = null }

    override var target: DragData? = null
        get() = field.also { field = null }
        set(value) {
            field = value
            if (value != null) cachedTarget = value
        }

    override var cachedTarget: DragData? = null
        get() = field.also { field = null }
        private set
}