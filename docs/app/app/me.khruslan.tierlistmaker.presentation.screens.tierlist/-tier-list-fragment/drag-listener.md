//[app](../../../index.md)/[me.khruslan.tierlistmaker.presentation.screens.tierlist](../index.md)/[TierListFragment](index.md)/[dragListener](drag-listener.md)

# dragListener

private val [dragListener](drag-listener.md): [TierListDragListener](../../me.khruslan.tierlistmaker.presentation.utils.drag/-tier-list-drag-listener/index.md)

Listener of the tier list drag events.

1. On drag started - dismisses [imageRemovedSnackbar](image-removed-snackbar.md) and starts a new drag.
2. On drag location changed - updates drag target and drag location in [autoScrollManager](auto-scroll-manager.md).
3. On drop - drops image and stops auto-scrolling.
4. On drag ended - removes drag target, restores released image and stops auto-scrolling.
