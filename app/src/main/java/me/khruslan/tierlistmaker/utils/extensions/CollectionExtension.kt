package me.khruslan.tierlistmaker.utils.extensions

fun <T> MutableList<T>.swap(a: Int, b: Int) {
    val temp = this[a]
    this[a] = this[b]
    this[b] = temp
}

fun <T> MutableList<T>.updateLast(value: T) {
    this[lastIndex] = value
}