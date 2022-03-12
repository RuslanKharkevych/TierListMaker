package me.khruslan.tierlistmaker.utils.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment

fun FragmentActivity.findNavHostFragmentById(@IdRes id: Int) =
    supportFragmentManager.findFragmentById(id) as NavHostFragment