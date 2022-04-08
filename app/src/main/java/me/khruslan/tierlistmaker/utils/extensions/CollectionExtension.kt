package me.khruslan.tierlistmaker.utils.extensions

/**
 * Swaps two items in the [MutableList].
 *
 * @param T type of the list item.
 * @param a first item.
 * @param b second item.
 * @receiver [MutableList] of an arbitrary type.
 */
fun <T> MutableList<T>.swap(a: Int, b: Int) {
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}

/**
 * Sets the [value] to the last item in the [MutableList].
 *
 * @param T type of the list item.
 * @param value value to set.
 * @receiver [MutableList] of an arbitrary type.
 */
fun <T> MutableList<T>.updateLast(value: T) {
    this[lastIndex] = value
}