//[app](../../index.md)/[me.khruslan.tierlistmaker.data.providers.file](index.md)

# Package-level declarations

Data providers for working with file system.

## Types

| Name | Summary |
|---|---|
| [FileManager](-file-manager/index.md) | interface [FileManager](-file-manager/index.md)<br>Manager for working with files. |
| [FileManagerImpl](-file-manager-impl/index.md) | class [FileManagerImpl](-file-manager-impl/index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val imageCompressor: [ImageCompressor](-image-compressor/index.md), val dispatcherProvider: [DispatcherProvider](../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), val performanceService: [PerformanceService](../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)) : [FileManager](-file-manager/index.md)<br>[FileManager](-file-manager/index.md) implementation. |
| [ImageCompressor](-image-compressor/index.md) | interface [ImageCompressor](-image-compressor/index.md)<br>Compressor of image files. |
| [ImageCompressorImpl](-image-compressor-impl/index.md) | class [ImageCompressorImpl](-image-compressor-impl/index.md) @[Inject](https://javax-inject.github.io/javax-inject/api/javax/inject/Inject.html) constructor(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val dispatcherProvider: [DispatcherProvider](../me.khruslan.tierlistmaker.data.providers.dispatchers/-dispatcher-provider/index.md), val preferencesHelper: [PreferencesHelper](../me.khruslan.tierlistmaker.data.providers.database/-preferences-helper/index.md), val performanceService: [PerformanceService](../me.khruslan.tierlistmaker.util.performance/-performance-service/index.md)) : [ImageCompressor](-image-compressor/index.md)<br>[ImageCompressor](-image-compressor/index.md) implementation. |
