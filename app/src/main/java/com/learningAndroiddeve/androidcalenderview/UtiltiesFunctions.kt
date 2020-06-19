package com.learningAndroiddeve.androidcalenderview

import android.annotation.SuppressLint
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar
import kotlin.collections.ArrayList


@SuppressLint("SimpleDateFormat")
fun dayNameNumbers(dates: List<CalendarDay>): ArrayList<Int> {
    val dayOfWeek = arrayListOf<Int>()
    val dateFormat = SimpleDateFormat("yyyy-M-d")
    for (dateInList in dates){
        val date: Date? = dateFormat.parse(dateInList.toString().trim().replace(Regex("""[CalendarDay{}]"""), ""))
        val c = Calendar.getInstance()
        c.time = date
        dayOfWeek.add(c[Calendar.DAY_OF_WEEK])
    }
    return dayOfWeek
}