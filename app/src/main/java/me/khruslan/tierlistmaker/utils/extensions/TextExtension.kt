package me.khruslan.tierlistmaker.utils.extensions

import kotlin.text.toInt as convertToInt

fun CharSequence.toInt(): Int = toString().convertToInt()