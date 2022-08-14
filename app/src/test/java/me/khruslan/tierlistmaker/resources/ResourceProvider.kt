package me.khruslan.tierlistmaker.resources

import com.google.gson.Gson
import me.khruslan.tierlistmaker.resources.adapters.TypeAdapterFactories
import java.io.InputStreamReader

object ResourceProvider {

    val gson: Gson by lazy {
        Gson()
            .newBuilder()
            .apply {
                TypeAdapterFactories.get().forEach { factory ->
                    registerTypeAdapterFactory(factory)
                }
            }
            .create()
    }

    inline fun <reified T> readJson(filePath: String): T {
        val classLoader = checkNotNull(javaClass.classLoader)
        val reader = InputStreamReader(classLoader.getResourceAsStream(filePath))
        return gson.fromJson(reader, T::class.java)
    }
}