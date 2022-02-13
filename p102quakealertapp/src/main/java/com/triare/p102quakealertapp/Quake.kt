package com.triare.p102quakealertapp

enum class QuakeIntensity {
    BARELY_NOTICEABLE,
    WEAK,
    AVERAGE,
    STRONG,
    VERY_STRONG;

    val title: Int
        get() = when (this) {
            BARELY_NOTICEABLE -> R.string.barely_noticeable_intensity_title
            WEAK -> R.string.weak_intensity_title
            AVERAGE -> R.string.average_intensity_title
            STRONG -> R.string.strong_intensity_title
            VERY_STRONG -> R.string.very_strong_intensity_title
        }

    val color: Int
        get() = when (this) {
            BARELY_NOTICEABLE -> R.color.green_intensity
            WEAK -> R.color.blue_intensity
            AVERAGE -> R.color.yellow_intensity
            STRONG -> R.color.orange_intensity
            VERY_STRONG -> R.color.red
        }

    val mapMarkers: Int
        get() = when(this) {
            BARELY_NOTICEABLE -> R.drawable.map_marker_green
            WEAK -> R.drawable.map_marker_blue
            AVERAGE -> R.drawable.map_marker_yellow
            STRONG -> R.drawable.map_marker_orange
            VERY_STRONG -> R.drawable.map_marker_red
        }

    companion object {
        fun getIntensity(intensity: Double): QuakeIntensity {
            return when (intensity) {
                in 1.0..1.99 -> BARELY_NOTICEABLE
                in 2.0..2.99 -> WEAK
                in 3.0..3.99 -> AVERAGE
                in 4.0..4.99 -> STRONG
                else -> VERY_STRONG
            }
        }
    }
}