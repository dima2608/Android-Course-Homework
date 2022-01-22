package com.triare.p102quakealertapp.data.mapper

import android.text.format.DateUtils
import com.triare.p102quakealertapp.QuakeIntensity
import com.triare.p102quakealertapp.data.api.model.QuakeDto
import com.triare.p102quakealertapp.ui.quake.dvo.FeatureQuakeDvo
import com.triare.p102quakealertapp.utils.TimeUtils
import java.util.*

class FeatureQuakeMapper(private val featureQuakeDto: QuakeDto) {

    fun map(): List<FeatureQuakeDvo> {
        return featureQuakeDto.features.map {
            val magnitude = QuakeIntensity.getIntensity(it.properties.magnitude)
            FeatureQuakeDvo(
                setTime(it.properties.time),
                it.properties.locality,
                magnitude.title,
                magnitude.color,
                setMagnitude(it.properties.magnitude)
            )
        }
    }

    private fun setTime(time: String): String {
        var properTimeStr: String? = null
        val formattedTime = TimeUtils.parseTime(time)
        formattedTime?.let {
            properTimeStr = DateUtils.getRelativeTimeSpanString(
                formattedTime,
                Calendar.getInstance().timeInMillis,
                DateUtils.DAY_IN_MILLIS
            ) as String
        }

        return properTimeStr!!
    }

    private fun setMagnitude(magnitude: Double): String {
        return String.format("%.2f", magnitude)
    }
}