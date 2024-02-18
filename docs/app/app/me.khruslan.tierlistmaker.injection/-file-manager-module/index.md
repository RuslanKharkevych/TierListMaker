//[app](../../../index.md)/[me.khruslan.tierlistmaker.injection](../index.md)/[FileManagerModule](index.md)

# FileManagerModule

@Module

abstract class [FileManagerModule](index.md)

Hilt module that contains bindings related to file manager.

This class must be used only by Hilt codegen.

## Constructors

| | |
|---|---|
| [FileManagerModule](-file-manager-module.md) | constructor()<br>Default empty constructor. |

## Functions

| Name | Summary |
|---|---|
| [bindFileManager](bind-file-manager.md) | @Binds<br>@Singleton<br>abstract fun [bindFileManager](bind-file-manager.md)(fileManagerImpl: [FileManagerImpl](../../me.khruslan.tierlistmaker.data.providers.file/-file-manager-impl/index.md)): [FileManager](../../me.khruslan.tierlistmaker.data.providers.file/-file-manager/index.md)<br>Binds file manager interface to its implementation. |
| [bindImageCompressor](bind-image-compressor.md) | @Binds<br>@Singleton<br>abstract fun [bindImageCompressor](bind-image-compressor.md)(imageCompressorImpl: [ImageCompressorImpl](../../me.khruslan.tierlistmaker.data.providers.file/-image-compressor-impl/index.md)): [ImageCompressor](../../me.khruslan.tierlistmaker.data.providers.file/-image-compressor/index.md)<br>Binds image compressor interface to its implementation. |
