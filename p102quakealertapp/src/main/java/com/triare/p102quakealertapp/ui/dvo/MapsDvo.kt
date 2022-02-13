package com.triare.p102quakealertapp.ui.dvo

import android.media.MicrophoneInfo
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.google.android.gms.maps.model.LatLng

data class MapsDvo(
    var dateTime: String,
    var location: String,
    @StringRes var intensityTitle: Int,
    @ColorRes var intensityColor: Int,
    @DrawableRes var mapMarker: Int,
    var magnitude: String,
    var latLng: LatLng
)