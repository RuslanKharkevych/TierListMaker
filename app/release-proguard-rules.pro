## region Crashlytics
-keepattributes SourceFile,LineNumberTable
-keep class * extends java.lang.Exception
## endregion Cranshlytics

## region Paper
-keep class me.khruslan.tierlistmaker.data.models.tierlist.TierList { *; }
-keep class me.khruslan.tierlistmaker.data.models.tierlist.Tier { *; }
-keep class me.khruslan.tierlistmaker.data.models.tierlist.TierStyle { *; }
-keep class me.khruslan.tierlistmaker.data.models.tierlist.image.Image { *; }
-keep class me.khruslan.tierlistmaker.data.models.tierlist.image.ResourceImage { *; }
-keep class me.khruslan.tierlistmaker.data.models.tierlist.image.StorageImage { *; }
-keep class me.khruslan.tierlistmaker.application.BaseTierListMakerApplication { *; }
## endregion Paper