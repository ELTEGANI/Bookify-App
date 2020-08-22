package com.learningAndroiddeve.androidcalenderview.utiles

import android.annotation.SuppressLint
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar
import kotlin.collections.ArrayList


@SuppressLint("SimpleDateFormat")
fun dayNameNumbers(dates: List<CalendarDay>):ArrayList<Int> {
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


@SuppressLint("SimpleDateFormat")
fun getDatesBetweenTwoDates(startDate: Date?, endDate: Date?): Iterable<String> {
    val simpleDateFormat = SimpleDateFormat("yyyy-m-d")
    val datesInRange: MutableList<String> = ArrayList()
    val calendar: Calendar = GregorianCalendar()
    calendar.time = startDate
    val endCalendar: Calendar = GregorianCalendar()
    endCalendar.time = endDate
    while (calendar.before(endCalendar)){
        val result = calendar.time
        datesInRange.add(simpleDateFormat.format(result).toString())
        calendar.add(Calendar.DATE,1)
    }
    return datesInRange
}

