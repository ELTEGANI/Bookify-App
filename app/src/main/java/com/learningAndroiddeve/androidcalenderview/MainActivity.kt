package com.learningAndroiddeve.androidcalenderview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.activity_main.*

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
            "2020-6-24"
        )

        for (list in listOfDatesFromServer) {
            val listSpitted = list.split("-")
            val dates = CalendarDay.from(
                listSpitted[0].toInt(),
                listSpitted[1].toInt(),
                listSpitted[2].toInt()
            )
            materialCalendarView.addDecorators(ReservedDaysDecorator(listOf(dates),"anytext"))
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
                totalPrice = totalWeekEndDays.plus(totalNormalEndDays).plus(600)
                Toast.makeText(this, "TotalPrice for WeekEndDays $totalPrice", Toast.LENGTH_LONG).show()
            } else {
                val totalPriceWithoutWeekEndDays = normalDays.values.sum().times(700).plus(600)
                Toast.makeText(this, "TotalPrice for NormalDays $totalPriceWithoutWeekEndDays", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

}



