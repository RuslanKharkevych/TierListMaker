package me.khruslan.tierlistmaker.utils

import org.junit.Assert.assertTrue
import kotlin.test.assertEquals

fun assertNotEmpty(actual: String) = assertTrue(actual.isNotEmpty())

fun assertEmpty(actual: Collection<*>) = assertTrue(actual.isEmpty())

fun <T> assertAll(actual: Collection<T>, predicate: (T) -> Boolean) =
    assertTrue(actual.all(predicate))

fun <T : Any> assertSealedEquals(expected: T, actual: T) =
    if (expected::class.objectInstance != null) {
        // Checking for equality by type comparison because GSON deserialization causes having two
        // instances of the same object
        assertEquals(expected::class, actual::class)
    } else {
        assertEquals(expected, actual)
    }