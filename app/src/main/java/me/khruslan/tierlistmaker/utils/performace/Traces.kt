package me.khruslan.tierlistmaker.utils.performace

/**
 * Traces image compressing. Attributes: result of the operation. Metrics: file size.
 */
object CompressImageTrace {
    const val NAME = "compress_image"
    const val ATTR_IS_SUCCESSFUL = "is_successful"
    const val METRIC_FILE_SIZE = "file_size"
}

/**
 * Traces generation of bitmap from tier list. Metrics: bitmap size.
 */
object GenerateBitmapFromTierListTrace {
    const val NAME = "generate_bitmap_from_tier_list"
    const val METRIC_BITMAP_SIZE = "bitmap_size"
}

/**
 * Traces providing of the default tier list collection. Metrics: count.
 */
object ProvideDefaultTierListCollectionTrace {
    const val NAME = "provide_default_tier_list_collection"
    const val METRIC_COUNT = "count"
}

/**
 * Traces reading of tier lists. Attributes: result of the operation. Metrics: attempts, count.
 */
object ReadTierListsTrace {
    const val NAME = "read_tier_lists"
    const val ATTR_IS_SUCCESSFUL = "is_successful"
    const val METRIC_ATTEMPTS = "attempts"
    const val METRIC_COUNT = "count"
}

/**
 * Traces saving images. Metrics: count.
 */
object SaveImagesTrace {
    const val NAME = "save_images"
    const val METRIC_COUNT = "count"
}

/**
 * Traces updating tier lists. Attributes: result of the operation. Metrics: count.
 */
object UpdateTierListsTrace {
    const val NAME = "update_tier_lists"
    const val ATTR_IS_SUCCESSFUL = "is_successful"
    const val METRIC_ATTEMPTS = "attempts"
    const val METRIC_COUNT = "count"
}

/**
 * Traces writing bitmap to file. Attributes: result of the operation. Metrics: bitmap size, file
 * size.
 */
object WriteBitmapToFileTrace {
    const val NAME = "write_bitmap_to_file"
    const val ATTR_IS_SUCCESSFUL = "is_successful"
    const val METRIC_BITMAP_SIZE = "bitmap_size"
    const val METRIC_FILE_SIZE = "file_size"
}