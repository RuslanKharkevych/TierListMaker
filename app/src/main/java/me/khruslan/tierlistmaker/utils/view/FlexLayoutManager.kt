package me.khruslan.tierlistmaker.utils.view

import android.content.Context
import android.view.ViewGroup
import com.google.android.flexbox.FlexboxLayoutManager

class FlexLayoutManager(context: Context) : FlexboxLayoutManager(context) {
    override fun generateLayoutParams(lp: ViewGroup.LayoutParams) = LayoutParams(lp)
}