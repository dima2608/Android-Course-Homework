package com.triare.p102quakealertapp.adaptor

import androidx.annotation.StringRes
import com.triare.p102quakealertapp.R

enum class QuakeIntensity(
    @StringRes
    val title: Int,
    @StringRes
    val color: Int
) {
    BARELY_NOTICEABLE(R.string.barely_noticeable_intensity_title, R.color.green_intensity),
    WEAK(R.string.weak_intensity_title, R.color.blue_intensity),
    AVERAGE(R.string.average_intensity_title, R.color.yellow_intensity),
    STRONG(R.string.strong_intensity_title, R.color.orange_intensity),
    VERY_STRONG(R.string.very_strong_intensity_title, R.color.red);
}
