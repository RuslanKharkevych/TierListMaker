package me.khruslan.tierlistmaker.presentation.utils.recyclerview

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * [FlexboxLayoutManager] implementation that generates the missing override of
 * [RecyclerView.LayoutManager.generateLayoutParams] overloaded function.
 *
 * It's a workaround to avoid a crash that happens when a recycler view, which is configured with
 * FlexboxLayoutManager, contains nested recycler view.
 *
 * @param context Activity context.
 * @constructor Creates horizontal flexbox layout manager.
 */
class FlexLayoutManager(context: Context) : FlexboxLayoutManager(context) {

    /**
     * Creates layout params object for this layout manager, copying relevant values from the
     * supplied layout params.
     *
     * @param lp Source layout params to copy values from.
     * @return A new layout params.
     */
    override fun generateLayoutParams(lp: ViewGroup.LayoutParams) = LayoutParams(lp)
}