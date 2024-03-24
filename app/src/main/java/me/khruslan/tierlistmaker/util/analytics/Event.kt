package me.khruslan.tierlistmaker.util.analytics

import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Base class for analytic events.
 *
 * This class is meant to be integrated with [FirebaseAnalytics].
 *
 * @property name Name of the event.
 * @property params Optional params logged with this event.
 * @constructor Creates new event.
 */
abstract class Event(
    val name: String,
    val params: Map<String, Any>? = null
)

/**
 * Logged when user rates the app.
 *
 * This event helps to identify how often users rate the app.
 *
 * @constructor Creates event with URL parameter.
 * @param url The URL launched depending on the agent used.
 */
class AppRated(url: String): Event(
    name = "app_rated",
    params = mapOf("url" to url)
)

/**
 * Logged when user sends feedback event.
 *
 * This event helps to identify how often users send feedback.
 *
 * @constructor Creates event with feedback type parameter.
 * @param isBugReport Wheneter the feedback is a bug report.
 */
class FeedbackSent(isBugReport: Boolean): Event(
    name = "feedback_sent",
    params = mapOf("feedback_type" to if (isBugReport) "Bug report" else "Feedback" )
)

/**
 * Logged when user views a hint.
 *
 * This event helps to identify which hints users view the most often.
 *
 * @constructor Creates event with hint group and hint step parameters.
 * @param hintGroup Name of the hint group.
 * @param hintStep Name of the hint step.
 */
class HintShown(hintGroup: String, hintStep: String) : Event(
    name = "hint_shown",
    params = mapOf("hint_group" to hintGroup, "hint_step" to hintStep)
)

/**
 * Logged when user deletes an image from a tier list.
 *
 * This event helps to identify which images users remove the most often.
 *
 * @constructor Creates event with image payload and tier list title parameters.
 * @param imagePayload Payload of the deleted image.
 * @param tierListTitle Title of the tier list.
 */
class ImageDeleted(imagePayload: String, tierListTitle: String) : Event(
    name = "image_deleted",
    params = mapOf("image_payload" to imagePayload, "tier_list_title" to tierListTitle)
)

/**
 * Logged when user adds new images to a tier list.
 *
 * This event helps to identify how often and how many images user add to tier lists.
 *
 * @constructor Creates event with count and tier list title parameters.
 * @param count How many images were added.
 * @param tierListTitle Title of the tier list.
 */
class ImagesAdded(count: Int, tierListTitle: String) : Event(
    name = "images_added",
    params = mapOf("count" to count, "tier_list_title" to tierListTitle)
)

/**
 * Logged when user changes preference.
 *
 * This event helps to identify which preferences users change and which values are the most
 * favourited.
 *
 * @constructor Creates event with preference key and value parameters.
 * @param key Key of the updated preference.
 * @param value Value of the updated preference.
 */
class PreferenceChanged(key: String?, value: Any?) : Event(
    name = "preference_changed",
    params = mapOf("key" to (key ?: "N/A"), "value" to (value ?: "N/A"))
)

/**
 * Logged when user navigates to a certain screen.
 *
 * This event helps to identify which screens users visit the most often. Note theat this event
 * should be used to log only fragments because activities are logged by default.
 *
 * @constructor Creates event with class name parameter.
 * @param className Name of the fragment class.
 */
class ScreenShown(className: String) : Event(
    name = FirebaseAnalytics.Event.SCREEN_VIEW,
    params = mapOf(
        FirebaseAnalytics.Param.SCREEN_NAME to className,
        FirebaseAnalytics.Param.SCREEN_CLASS to className
    )
)

/**
 * Logged when user adds a new tier.
 *
 * This event helps to identify how often and how many tiers users add.
 *
 * @constructor Creates event with tier index and tier list title parameters.
 * @param index Index of the inserted tier.
 * @param tierListTitle Title of the tier list.
 */
class TierAdded(index: Int, tierListTitle: String) : Event(
    name = "tier_added",
    params = mapOf("index" to index, "tier_list_title" to tierListTitle)
)

/**
 * Logged when user creates a new tier list.
 *
 * This event helps to identify how often users create new tier lists and which topics they are
 * interested in.
 *
 * @constructor Creates event with tier list title parameter.
 * @param tierListTitle Title of the new tier list.
 */
class TierListCreated(tierListTitle: String) : Event(
    name = "tier_list_created",
    params = mapOf("tier_list_title" to tierListTitle)
)

/**
 * Logged when user deletes a tier list.
 *
 * This event helps to identify which tier lists user delete most often.
 *
 * @constructor Creates event with tier list title parameter.
 * @param tierListTitle Title of the deleted tier list.
 */
class TierListDeleted(tierListTitle: String) : Event(
    name = "tier_list_deleted",
    params = mapOf("tier_list_title" to tierListTitle)
)

/**
 * Logged when user opens a tier list.
 *
 * This event helps to identify which tier lists user open the most.
 *
 * @constructor Creates event with tier list title parameter.
 * @param tierListTitle Title of the opened tier list.
 */
class TierListOpened(tierListTitle: String) : Event(
    name = "tier_list_opened",
    params = mapOf("tier_list_title" to tierListTitle)
)

/**
 * Logged when user renames a tier list.
 *
 * This event helps to identify how often users rename tier lists and which ones they rename the
 * most.
 *
 * @constructor Creates event with initial title and updated title parameters.
 * @param initialTitle Tier list title before renaming
 * @param updatedTitle Tier list title after renaming.
 */
class TierListRenamed(initialTitle: String, updatedTitle: String) : Event(
    name = "tier_list_renamed",
    params = mapOf("initial_title" to initialTitle, "updated_title" to updatedTitle)
)

/**
 * Logged when user shares a tier list.
 *
 * This event helps to identify which tier lists user share the most and how often they do that.
 *
 * @constructor Creates event with tier list title parameter.
 * @param tierListTitle Title of the shared tier list.
 */
class TierListShared(tierListTitle: String) : Event(
    name = "tier_list_shared",
    params = mapOf("tier_list_title" to tierListTitle)
)

/**
 * Logged when user views a tier list.
 *
 * This event helps to identify which tier lists user view the most and how often they do that.
 *
 * @constructor Creates event with tier list title parameter.
 * @param tierListTitle Title of the viewed tier list.
 */
class TierListViewed(tierListTitle: String) : Event(
    name = "tier_list_viewed",
    params = mapOf("tier_list_title" to tierListTitle)
)

/**
 * Logged when user removes a tier.
 *
 * This event helps to identify how often users remove tiers.
 *
 * @constructor Creates event with tier index and tier list title parameters.
 * @param index Index of the removed tier.
 * @param tierListTitle Title of the tier list.
 */
class TierRemoved(index: Int, tierListTitle: String) : Event(
    name = "tier_removed",
    params = mapOf("index" to index, "tier_list_title" to tierListTitle)
)

/**
 * Logged when user changes theme.
 *
 * This event helps to identify how often users toggle between light and dark themes and which one
 * they prefer.
 *
 * @constructor Creates event with theme parameter.
 * @param toDark Whether theme was changed to dark mode.
 */
class ThemeChanged(toDark: Boolean) : Event(
    name = "theme_changed",
    params = mapOf("theme" to if (toDark) "Dark" else "Light")
)

/**
 * Logged when zooms in (in a tier list).
 *
 * This event helps to identify how often users zoom in and which scale they prefer.
 *
 * @constructor Creates event with zoom value parameter.
 * @param zoomValue Target zoom value.
 */
class ZoomedIn(zoomValue: Int) : Event(
    name = "zoomed_in",
    params = mapOf("zoom_value" to zoomValue)
)

/**
 * Logged when user zooms out (in a tier list).
 *
 * This event helps to identify how often users zoom out and which scale they prefer.
 *
 * @constructor Creates event with zoom value parameter.
 * @param zoomValue Target zoom value.
 */
class ZoomedOut(zoomValue: Int) : Event(
    name = "zoomed_out",
    params = mapOf("zoom_value" to zoomValue)
)