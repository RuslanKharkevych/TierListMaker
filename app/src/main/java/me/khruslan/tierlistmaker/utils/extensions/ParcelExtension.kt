package me.khruslan.tierlistmaker.utils.extensions

import android.os.Parcel

fun Parcel.requireString() = readString()
    ?: throw IllegalStateException("Can't read string from parcel $this")