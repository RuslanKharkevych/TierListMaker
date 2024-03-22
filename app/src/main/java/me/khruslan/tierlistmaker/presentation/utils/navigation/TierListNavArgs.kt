package me.khruslan.tierlistmaker.presentation.utils.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import me.khruslan.tierlistmaker.data.models.tierlist.TierList
import me.khruslan.tierlistmaker.presentation.utils.hints.tierlist.TierListHintStep

/**
 * Navigation arguments supplied to the [TierListResultContract].
 *
 * @property tierList Required tier list argument.
 * @property hintStep Optional hint step argument.
 */
@Parcelize
data class TierListNavArgs(
    val tierList: TierList,
    val hintStep: TierListHintStep?
): Parcelable