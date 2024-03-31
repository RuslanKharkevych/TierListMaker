package me.khruslan.tierlistmaker.utils

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice

val targetContext: Context
    get() = instrumentation.targetContext

val uiDevice
    get() = UiDevice.getInstance(instrumentation)

private val instrumentation
    get() = InstrumentationRegistry.getInstrumentation()