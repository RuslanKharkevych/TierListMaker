package me.khruslan.tierlistmaker.util

/**
 * Used to differentiate the backlog from other tiers.
 *
 * Although backlog is a separate entity, sometimes it's modeled to be interchangeable with other
 * tiers. In such cases this constant is considered to be its position in the tier list.
 */
const val BACKLOG_TIER_POSITION = -1

/**
 * A proportion of the tier list image width to the display width.
 *
 * Can be used to calculate image size when compressing it to a file or generating a bitmap.
 */
const val TIER_IMAGE_WIDTH_FRACTION = 1f / 3f