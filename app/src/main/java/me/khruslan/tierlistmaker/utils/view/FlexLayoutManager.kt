package me.khruslan.tierlistmaker.utils.view

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * [FlexboxLayoutManager] implementation that generates the missing override of
 * [RecyclerView.LayoutManager.generateLayoutParams] overloaded function.
 *
 * It's a workaround to avoid a crash when the layout params are calculated.
 *
 * @param context activity context.
 */
class FlexLayoutManager(context: Context) : FlexboxLayoutManager(context) {
    override fun generateLayoutParams(lp: ViewGroup.LayoutParams) = LayoutParams(lp)
}