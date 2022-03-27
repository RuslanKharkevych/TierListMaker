package me.khruslan.tierlistmaker.utils.extensions

import io.paperdb.Book
import java.lang.IllegalArgumentException

fun <T> Book.readOrDefault(key: String, defaultValue: T) =
    read(key, defaultValue) ?: throw IllegalArgumentException("defaultValue can't be null")