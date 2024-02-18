package me.khruslan.tierlistmaker.data.providers.database

/**
 * Helper that simplifies persisting user preferences.
 *
 * All functions are synchronous. No error handling is required. Some properties are read-only
 * because they are not changed directly from code.
 */
interface PreferencesHelper {

    /**
     * Whether dark theme is preferred by user. If not - user opted for light theme.
     *
     * This property has read / write access.
     */
    var nightModeEnabled: Boolean

    /**
     * How many tiers will be added when a new tier list is created.
     *
     * This property is read-only.
     */
    val tiersCount: Int

    /**
     * How many images will fit in a row inside a tier in a new tier list.
     *
     * This property is read-only.
     */
    val scale: Int

    /**
     * The quality in percents of the new tier list images.
     *
     * This property is read-only.
     */
    val imageQuality: Int

    /**
     * Whether the default tier list collection has already been provided.
     *
     * This property is read-only. Use [markDefaultTierListCollectionAsProvided] to update the
     * preference.
     */
    val defaultTierListCollectionProvided: Boolean

    /**
     * Marks default tier list collection as provided.
     *
     * @see defaultTierListCollectionProvided
     */
    fun markDefaultTierListCollectionAsProvided()
}