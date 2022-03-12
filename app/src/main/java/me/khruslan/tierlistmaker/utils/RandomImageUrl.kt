package me.khruslan.tierlistmaker.utils

// fixme: Added for testing purposes only
object RandomImageUrl {
    private var signatureIndex = 0

    fun generate(): String {
        signatureIndex++
        return "https://source.unsplash.com/random/200x200?sig=$signatureIndex"
    }
}