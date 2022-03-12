package me.khruslan.tierlistmaker.ui.adapters.reorderable

interface Reorderable {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemSwiped(position: Int)
}