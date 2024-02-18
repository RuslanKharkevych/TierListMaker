package me.khruslan.tierlistmaker.presentation.utils.navigation

/**
 * Indicates that tier list navigation result can't be resolved.
 *
 * Can be thrown during parsing of the result in the contract, or at a later stage when attempting
 * to obtain the tier list inside the activity or fragment.
 *
 * @param message Error message of the exception.
 * @constructor Creates exception with error message.
 */
class TierListResultException(message: String) : Exception(message)