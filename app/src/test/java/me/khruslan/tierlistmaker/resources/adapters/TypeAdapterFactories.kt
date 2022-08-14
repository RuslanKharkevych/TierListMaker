package me.khruslan.tierlistmaker.resources.adapters

import com.google.gson.TypeAdapterFactory
import me.khruslan.tierlistmaker.data.drag.DragData
import me.khruslan.tierlistmaker.data.drag.ImageDragData
import me.khruslan.tierlistmaker.data.drag.TierDragData
import me.khruslan.tierlistmaker.data.drag.TrashBinDragData
import me.khruslan.tierlistmaker.data.drag.effects.*
import me.khruslan.tierlistmaker.data.tierlist.*
import me.khruslan.tierlistmaker.data.tierlist.image.Image
import me.khruslan.tierlistmaker.data.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.tierlist.image.StorageImage

object TypeAdapterFactories {

    fun get() = listOf<TypeAdapterFactory>(
        dragData,
        highlightEffect,
        image,
        insertEffect,
        removeEffect,
        tierListEvent,
        updateEffect
    )

    private val dragData by lazy {
        RuntimeTypeAdapterFactory
            .of(DragData::class.java)
            .registerSubtype(ImageDragData::class.java)
            .registerSubtype(TierDragData::class.java)
            .registerSubtype(TrashBinDragData::class.java)
    }

    private val highlightEffect by lazy {
        RuntimeTypeAdapterFactory
            .of(HighlightEffect::class.java)
            .registerSubtype(HighlightInTier::class.java)
            .registerSubtype(HighlightInBacklog::class.java)
            .registerSubtype(HighlightLastInTier::class.java)
            .registerSubtype(HighlightLastInBacklog::class.java)
            .registerSubtype(HighlightTrashBin::class.java)
    }

    private val image by lazy {
        RuntimeTypeAdapterFactory
            .of(Image::class.java)
            .registerSubtype(ResourceImage::class.java)
            .registerSubtype(StorageImage::class.java)
    }

    private val insertEffect by lazy {
        RuntimeTypeAdapterFactory
            .of(InsertEffect::class.java)
            .registerSubtype(InsertToBacklog::class.java)
            .registerSubtype(InsertToTier::class.java)
            .registerSubtype(InsertToEndOfBacklog::class.java)
            .registerSubtype(InsertToEndOfTier::class.java)
            .registerSubtype(InsertToTrashBin::class.java)
    }

    private val removeEffect by lazy {
        RuntimeTypeAdapterFactory
            .of(RemoveEffect::class.java)
            .registerSubtype(RemoveFromBacklog::class.java)
            .registerSubtype(RemoveFromTier::class.java)
            .registerSubtype(RemoveLastFromBacklog::class.java)
            .registerSubtype(RemoveLastFromTier::class.java)
            .registerSubtype(UnhighlightTrashBin::class.java)
    }

    private val tierListEvent by lazy {
        RuntimeTypeAdapterFactory
            .of(TierListEvent::class.java)
            .registerSubtype(BacklogDataChanged::class.java)
            .registerSubtype(BacklogImagesAdded::class.java)
            .registerSubtype(BacklogItemChanged::class.java)
            .registerSubtype(BacklogItemInserted::class.java)
            .registerSubtype(TierListChanged::class.java)
            .registerSubtype(TierChanged::class.java)
            .registerSubtype(TierInserted::class.java)
            .registerSubtype(ImageSizeUpdated::class.java)
            .registerSubtype(TrashBinHighlightUpdated::class.java)
            .registerSubtype(ImageRemoved::class.java)
    }

    private val updateEffect: TypeAdapterFactory by lazy {
        RuntimeTypeAdapterFactory
            .of(UpdateEffect::class.java)
            .registerSubtype(UpdateInBacklog::class.java)
            .registerSubtype(UpdateInTier::class.java)
            .registerSubtype(UpdateLastInBacklog::class.java)
            .registerSubtype(UpdateLastInTier::class.java)
    }
}