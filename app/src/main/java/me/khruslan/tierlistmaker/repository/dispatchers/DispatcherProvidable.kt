package me.khruslan.tierlistmaker.repository.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvidable {
    val default: CoroutineDispatcher
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}