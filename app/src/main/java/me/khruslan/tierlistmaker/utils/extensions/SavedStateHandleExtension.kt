package me.khruslan.tierlistmaker.utils.extensions

import androidx.lifecycle.SavedStateHandle

fun <T> SavedStateHandle.require(key: String) = get<T>(key)
    ?: throw IllegalArgumentException("SavedStateHandle $this doesn't contain key $key")