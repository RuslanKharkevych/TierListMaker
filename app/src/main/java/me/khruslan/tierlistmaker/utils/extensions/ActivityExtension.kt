package me.khruslan.tierlistmaker.utils.extensions

import android.app.Activity
import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment

/**
 * Finds [NavHostFragment] by id.
 *
 * @param id id of the [NavHostFragment].
 * @receiver [FragmentActivity] that hosts [NavHostFragment].
 * @return The found [NavHostFragment].
 */
fun FragmentActivity.findNavHostFragmentById(@IdRes id: Int) =
    supportFragmentManager.findFragmentById(id) as NavHostFragment

/**
 * Sets activity result with result code as [Activity.RESULT_OK] and data as [data].
 *
 * After that immediately finishes the activity.
 *
 * @receiver Any [Activity].
 * @param data [Intent] that is sent as an activity result.
 */
fun Activity.setResultDataAndFinish(data: Intent) {
    setResult(Activity.RESULT_OK, data)
    finish()
}