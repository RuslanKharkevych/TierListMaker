package me.khruslan.tierlistmaker.utils

import androidx.lifecycle.LiveData
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import java.util.concurrent.TimeUnit

private const val TIMEOUT_SECONDS = 2L

fun <T> LiveData<T>.awaitValue(value: T): TestObserver<T> = test().awaitValue(value)

fun <T> TestObserver<T>.awaitValue(value: T): TestObserver<T> =
    awaitValue(TIMEOUT_SECONDS, TimeUnit.SECONDS).assertValue(value)

fun <T> TestObserver<T>.awaitValues(vararg values: T): TestObserver<T> =
    awaitValue(TIMEOUT_SECONDS, TimeUnit.SECONDS).assertValueHistory(*values)