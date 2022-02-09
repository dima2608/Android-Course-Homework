package com.triare.p151notes.utisl

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object TimeUtils {

    private const val DATE_TIME_PATTERN = "dd MMM, HH:mm"

    @SuppressLint("SimpleDateFormat")
    fun convertLongToTime(millis: Long): String {
        val date = Date(millis)
        val format = SimpleDateFormat(DATE_TIME_PATTERN)
        return format.format(date)
    }

    fun currentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}