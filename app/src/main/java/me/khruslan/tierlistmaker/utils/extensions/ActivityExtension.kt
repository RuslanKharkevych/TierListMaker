package me.khruslan.tierlistmaker.utils.extensions

import android.app.Activity
import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment

fun FragmentActivity.findNavHostFragmentById(@IdRes id: Int) =
    supportFragmentManager.findFragmentById(id) as NavHostFragment

fun Activity.setResultDataAndFinish(data: Intent) {
    setResult(Activity.RESULT_OK, data)
    finish()
}