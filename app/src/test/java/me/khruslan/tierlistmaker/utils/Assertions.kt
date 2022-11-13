package me.khruslan.tierlistmaker.utils

import org.junit.Assert.assertTrue

fun assertNotEmpty(actual: String) = assertTrue(actual.isNotEmpty())

fun assertEmpty(actual: Collection<*>) = assertTrue(actual.isEmpty())

fun <T> assertAll(actual: Collection<T>, predicate: (T) -> Boolean) =
    assertTrue(actual.all(predicate))