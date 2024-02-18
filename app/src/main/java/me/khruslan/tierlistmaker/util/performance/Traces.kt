package me.khruslan.tierlistmaker.util.performance

/**
 * Traces image compressing.
 */
object CompressImageTrace {

    /**
     * The name of the trace.
     */
    const val NAME = "compress_image"

    /**
     * Whether the image was compressed successfully.
     */
    const val ATTR_IS_SUCCESSFUL = "is_successful"

    /**
     * The size in bytes of the compressed file.
     */
    const val METRIC_FILE_SIZE = "file_size"
}

/**
 * Traces generation of bitmap from tier list.
 */
object GenerateBitmapFromTierListTrace {

    /**
     * The name of the trace.
     */
    const val NAME = "generate_bitmap_from_tier_list"

    /**
     * The size in bytes of the generated bitmap.
     */
    const val METRIC_BITMAP_SIZE = "bitmap_size"
}

/**
 * Traces providing of the default tier list collection.
 */
object ProvideDefaultTierListCollectionTrace {

    /**
     * The name of the trace.
     */
    const val NAME = "provide_default_tier_list_collection"

    /**
     * The number of provided tier lists.
     */
    const val METRIC_COUNT = "count"
}

/**
 * Traces reading of tier lists.
 */
object ReadTierListsTrace {

    /**
     * The name of the trace.
     */
    const val NAME = "read_tier_lists"

    /**
     * Whether the transaction was successful.
     */
    const val ATTR_IS_SUCCESSFUL = "is_successful"

    /**
     * How many transaction attempts was tried.
     */
    const val METRIC_ATTEMPTS = "attempts"

    /**
     * Number of tier list read.
     */
    const val METRIC_COUNT = "count"
}

/**
 * Traces saving images.
 */
object SaveImagesTrace {

    /**
     * The name of the trace.
     */
    const val NAME = "save_images"

    /**
     * Number of saved images.
     */
    const val METRIC_COUNT = "count"
}

/**
 * Traces updating tier lists.
 */
object UpdateTierListsTrace {

    /**
     * The name of the trace.
     */
    const val NAME = "update_tier_lists"

    /**
     * Whether the transaction was successful.
     */
    const val ATTR_IS_SUCCESSFUL = "is_successful"

    /**
     * How many transaction attempts was tried.
     */
    const val METRIC_ATTEMPTS = "attempts"

    /**
     * How many tier lists was updated.
     */
    const val METRIC_COUNT = "count"
}

/**
 * Traces writing bitmap to file.
 */
object WriteBitmapToFileTrace {

    /**
     * The name of the trace.
     */
    const val NAME = "write_bitmap_to_file"

    /**
     * Whether bitmap was written to file successfully.
     */
    const val ATTR_IS_SUCCESSFUL = "is_successful"

    /**
     * The size in bytes of the source bitmap.
     */
    const val METRIC_BITMAP_SIZE = "bitmap_size"

    /**
     * The size in bytes of the destination file.
     */
    const val METRIC_FILE_SIZE = "file_size"
}