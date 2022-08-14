package me.khruslan.tierlistmaker.dataproviders.data

import me.khruslan.tierlistmaker.data.tierlist.TierList
import me.khruslan.tierlistmaker.data.tierlist.image.Image
import me.khruslan.tierlistmaker.data.tierlist.image.ResourceImage
import me.khruslan.tierlistmaker.data.tierlist.image.StorageImage
import me.khruslan.tierlistmaker.dataproviders.DataProvider
import me.khruslan.tierlistmaker.dataproviders.DataProviderParams

@Suppress("ObjectPropertyName")
object TierListDataProvider : DataProvider("data/tierlist") {

    val arraySizes: Array<Int> by lazy { readJson("array-sizes") }
    val filePaths: Array<String> by lazy { readJson("file-paths") }
    val imageIds: Array<String> by lazy { readJson("image-ids") }
    val imagePayloads: Array<String> by lazy { readJson("image-payloads") }
    val images: Array<Image> by lazy { readJson("images") }
    val otherResourceImages: Array<Image> by lazy { readJson("other-resource-images") }
    val otherStorageImages: Array<Image> by lazy { readJson("other-storage-images") }
    val resourceImageEqualityResults: Array<Boolean> by lazy { readJson("resource-image-equality-results") }
    val resourceImageHashCodes: Array<Int> by lazy { readJson("resource-image-hash-codes") }
    val resourceImages: Array<ResourceImage> by lazy { readJson("resource-images") }
    val resourceImageStrings: Array<String> by lazy { readJson("resource-image-strings") }
    val storageImageEqualityResults: Array<Boolean> by lazy { readJson("storage-image-equality-results") }
    val storageImageHashCodes: Array<Int> by lazy { readJson("storage-image-hash-codes") }
    val storageImages: Array<StorageImage> by lazy { readJson("storage-images") }
    val storageImageStrings: Array<String> by lazy { readJson("storage-image-strings") }

    private val _lists: Array<TierList> by lazy { readJson("lists") }
    val lists get() = _lists.map { it.copy() }.toTypedArray()

    private val _previews: Array<TierList.Preview> by lazy { readJson("previews") }
    val previews get() = _previews.map { it.copy() }.toTypedArray()

    object Previews : DataProviderParams() {
        const val listParam = 0
        const val previewParam = 1

        override val data get() = mergedData(lists, previews)
    }

    object ImagePayloads : DataProviderParams() {
        const val idParam = 0
        const val payloadParam = 1
        const val imageParam = 2

        override val data get() = mergedData(imageIds, imagePayloads, images)
    }

    object ResourceImageHashCodes : DataProviderParams() {
        const val imageParam = 0
        const val hashCodeParam = 1

        override val data get() = mergedData(resourceImages, resourceImageHashCodes)
    }

    object ResourceImageStrings : DataProviderParams() {
        const val imageParam = 0
        const val stringParam = 1

        override val data get() = mergedData(resourceImages, resourceImageStrings)
    }

    object ResourceImageEqualityResults : DataProviderParams() {
        const val imageParam = 0
        const val otherParam = 1
        const val equalityResult = 2

        override val data
            get() = mergedData(resourceImages, otherResourceImages, resourceImageEqualityResults)
    }

    object StorageImageHashCodes : DataProviderParams() {
        const val imageParam = 0
        const val hashCodeParam = 1

        override val data get() = mergedData(storageImages, storageImageHashCodes)
    }

    object StorageImageStrings : DataProviderParams() {
        const val imageParam = 0
        const val stringParam = 1

        override val data get() = mergedData(storageImages, storageImageStrings)
    }

    object StorageImageEqualityResults : DataProviderParams() {
        const val imageParam = 0
        const val otherParam = 1
        const val equalityResult = 2

        override val data
            get() = mergedData(storageImages, otherStorageImages, storageImageEqualityResults)
    }
}