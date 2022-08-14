package me.khruslan.tierlistmaker.dataproviders

import me.khruslan.tierlistmaker.resources.ResourceProvider

abstract class DataProvider(protected val resFolder: String) {

    protected inline fun <reified T> readJson(fileName: String) =
        ResourceProvider.readJson<T>("$resFolder/$fileName.json")

    protected fun mergedData(vararg arrays: Array<*>): List<Array<*>> {
        val arraySize = arrays.first().size

        if (arrays.any { it.size != arraySize }) {
            throw IllegalArgumentException("All lists must have the same size!")
        }

        return List(arraySize) { index ->
            arrays.map { it[index] }.toTypedArray()
        }
    }
}