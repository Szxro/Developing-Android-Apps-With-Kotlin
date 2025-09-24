package com.example.sleepnight.common.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    private const val DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    fun millisToDate(millis: Long, pattern: String = DEFAULT_PATTERN): String {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(Date(millis))
    }
}