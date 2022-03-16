package me.khruslan.tierlistmaker.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.khruslan.tierlistmaker.utils.drag.DragPocket

@Module
@InstallIn(ViewModelComponent::class)
object TierListModule {
    @Provides
    @ViewModelScoped
    fun provideDragPocket() = DragPocket()
}