package com.samemtech.androidcalenderview

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var materialCalendarView : MaterialCalendarView

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        materialCalendarView = findViewById(R.id.calendarView)


        val listOfDatesFromServer = listOf(
            "2020-6-16",
            "2020-6-17",
            "2020-6-18",
            "2020-6-19",
            "2020-6-20",
            "2020-6-21",
            "2020-6-22",
            "2020-6-23",
            "2020-6-24",
            "2020-6-25",
            "2020-6-26",
            "2020-6-27",
            "2020-6-28",
            "2020-7-28",
            "2020-7-16",
            "2020-7-17",
            "2020-7-18",
            "2020-7-19",
            "2020-7-20",
            "2020-7-21",
            "2020-7-22",
            "2020-7-23",
            "2020-7-24",
            "2020-7-25",
            "2020-7-26",
            "2020-7-27",
            "2020-7-28"
        )

        for (list in listOfDatesFromServer) {
            val listSpitted = list.split("-")
            val dates = CalendarDay.from(
                listSpitted[0].toInt(),
                listSpitted[1].toInt(),
                listSpitted[2].toInt()
            )
            materialCalendarView.addDecorators(ReservedDaysDecorator(Color.RED, listOf(dates)))
            materialCalendarView.invalidateDecorators()
        }


        save_button.setOnClickListener {
            val totalPrice: Int
            val removedList = materialCalendarView.selectedDates.toList()
            val dayOfWeek = dayNameNumbers(removedList)
            val weekEndDays = dayOfWeek.groupingBy { it }.eachCount().filter { it.key in 5..6 }
            val normalDays = dayOfWeek.groupingBy { it }.eachCount().filter { it.key == 7 || it.key in 1..4 }
            if (weekEndDays.isNotEmpty()) {
                val totalWeekEndDays = weekEndDays.values.sum().times(900)
                val totalNormalEndDays = normalDays.values.sum().times(700)
                totalPrice = totalWeekEndDays.plus(totalNormalEndDays).plus(500)
                Toast.makeText(this, "weekEnd Days $totalPrice", Toast.LENGTH_LONG).show()
            } else {
                val totalPriceWithoutWeekEndDays = normalDays.values.sum().times(700).plus(500)
                Toast.makeText(this, "Normal Days $totalPriceWithoutWeekEndDays", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
    @SuppressLint("SimpleDateFormat")
    private fun dayNameNumbers(dates: List<CalendarDay>): ArrayList<Int> {
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

    fun <T> hasDuplicates(arr: Array<T>): Boolean {
        return arr.size != arr.distinct().count();
    }
}



