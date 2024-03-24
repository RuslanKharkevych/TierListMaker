//[app](../../../index.md)/[me.khruslan.tierlistmaker.util.analytics](../index.md)/[Event](index.md)

# Event

abstract class [Event](index.md)(val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val params: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;? = null)

Base class for analytic events.

This class is meant to be integrated with FirebaseAnalytics.

#### Inheritors

| |
|---|
| [AppRated](../-app-rated/index.md) |
| [FeedbackSent](../-feedback-sent/index.md) |
| [HintShown](../-hint-shown/index.md) |
| [ImageDeleted](../-image-deleted/index.md) |
| [ImagesAdded](../-images-added/index.md) |
| [PreferenceChanged](../-preference-changed/index.md) |
| [ScreenShown](../-screen-shown/index.md) |
| [TierAdded](../-tier-added/index.md) |
| [TierListCreated](../-tier-list-created/index.md) |
| [TierListDeleted](../-tier-list-deleted/index.md) |
| [TierListOpened](../-tier-list-opened/index.md) |
| [TierListRenamed](../-tier-list-renamed/index.md) |
| [TierListShared](../-tier-list-shared/index.md) |
| [TierListViewed](../-tier-list-viewed/index.md) |
| [TierRemoved](../-tier-removed/index.md) |
| [ThemeChanged](../-theme-changed/index.md) |
| [ZoomedIn](../-zoomed-in/index.md) |
| [ZoomedOut](../-zoomed-out/index.md) |

## Constructors

| | |
|---|---|
| [Event](-event.md) | constructor(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), params: [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;? = null)<br>Creates new event. |

## Properties

| Name | Summary |
|---|---|
| [name](name.md) | val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Name of the event. |
| [params](params.md) | val [params](params.md): [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;? = null<br>Optional params logged with this event. |
