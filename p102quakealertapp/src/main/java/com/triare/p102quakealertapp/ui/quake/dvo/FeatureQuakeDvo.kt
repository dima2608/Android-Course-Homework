package com.triare.p102quakealertapp.ui.quake.dvo

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeatureQuakeDvo(
    var dateTime: String,
    var location: String,
    @StringRes var intensityTitle: Int,
    @ColorRes var intensityColor: Int,
    var magnitude: String
) : Parcelable


