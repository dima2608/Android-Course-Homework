package com.triare.p102quakealertapp.data.mapper

import android.text.format.DateUtils
import com.google.android.gms.maps.model.LatLng
import com.triare.p102quakealertapp.QuakeIntensity
import com.triare.p102quakealertapp.data.api.model.QuakeDto
import com.triare.p102quakealertapp.ui.dvo.FeatureQuakeDvo
import com.triare.p102quakealertapp.ui.dvo.MapsDvo
import com.triare.p102quakealertapp.utils.TimeUtils
import java.util.*

class MapsMapper(
    private val featureQuakeDto: QuakeDto
) {
    fun map(): List<MapsDvo> {
        return featureQuakeDto.features.map {
            val magnitude = QuakeIntensity.getIntensity(it.properties.magnitude)
            MapsDvo(
                setTime(it.properties.time),
                it.properties.locality,
                magnitude.title,
                magnitude.color,
                magnitude.mapMarkers,
                setMagnitude(it.properties.magnitude),
                getCoordinates(it.geometry.coordinates)
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

    private fun getCoordinates(coordinates: List<Double>): LatLng {
        return LatLng(coordinates[1], coordinates[0])
    }
}