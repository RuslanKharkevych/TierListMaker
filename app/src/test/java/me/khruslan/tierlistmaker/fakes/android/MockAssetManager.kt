package me.khruslan.tierlistmaker.fakes.android

import android.content.res.AssetManager
import io.mockk.every
import io.mockk.mockk

object MockAssetManager {

    fun get(): AssetManager {
        val manager: AssetManager = mockk()
        assetsMap.forEach { (path, files) ->
            every { manager.list(path) } returns files
        }
        return manager
    }

    private val assetsMap = mapOf(
        "tier_list_images/fruits" to arrayOf("apple.jpeg", "banana.jpeg", "orange.jpeg"),
        "tier_list_images/animals" to arrayOf("lion.jpeg", "shark.jpeg", "elephant.jpeg"),
        "tier_list_images/sports" to arrayOf(),
        "tier_list_images/fast_food" to arrayOf("hamburger.jpeg", "french_fries.jpeg")
    )

    val assets = assetsMap.flatMap { (path, files) ->
        files.map { file -> "file:///android_asset/$path/$file" }
    }
}