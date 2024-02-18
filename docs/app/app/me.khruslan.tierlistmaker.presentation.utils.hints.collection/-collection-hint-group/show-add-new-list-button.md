//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.utils.hints.collection](../index.md)/[CollectionHintGroup](index.md)/[showAddNewListButton](show-add-new-list-button.md)

# showAddNewListButton

private fun [showAddNewListButton](show-add-new-list-button.md)()

Shows &quot;Add new list&quot; floating action button.

Since FAB visibility is controlled by the scroll behaviour of the coordinator layout, simple show/hide doesn't work. Instead, HideBottomViewOnScrollBehavior.slideUp must be used. Also, it's important to disable slide animation, because this function must be synchronous.
