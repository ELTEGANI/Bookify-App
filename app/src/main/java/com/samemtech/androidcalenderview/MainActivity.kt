package com.samemtech.androidcalenderview

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


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
            var totalPrice = 0
            var endWeekDayPrice = 900
            var midWeekDayPrice = 700
            val removedList = materialCalendarView.selectedDates.toList()
            for (day in removedList) {
                val dayOfWeek = dayName(day)
                if (dayOfWeek == 6 || dayOfWeek == 7) {
                    Toast.makeText(this, "pay 900 SAR", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "pay 700 SAR", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    @SuppressLint("SimpleDateFormat")
    private fun dayName(day: CalendarDay): Int {
        val dateFormat = SimpleDateFormat("yyyy-M-d")
        val date: Date? = dateFormat.parse(day.toString().trim().replace(Regex("""[CalendarDay{}]"""), ""))
        val c = Calendar.getInstance()
        c.time = date
        val dayOfWeek = c[Calendar.DAY_OF_WEEK]
        return dayOfWeek
    }
}








//                val simpleDateFormat = SimpleDateFormat("EEEE,yyyy-MM-d")
//                val formatedDate: String = simpleDateFormat.parse(day.toString().trim().replace(Regex("""[CalendarDay{}]"""),"")).toString()
//                 val date: LocalDate = LocalDate.parse(day.toString().trim().replace(Regex("""[CalendarDay{}]"""),""))
//                 val day: DayOfWeek = date.dayOfWeek
//get all selected dates using materialCalendarView.selectedDates
//Toast.makeText(this,materialCalendarView.selectedDates.toString(),Toast.LENGTH_LONG).show()
//replace(Regex("""[CalendarDay{}]"""))
// Toast.makeText(this,,""),Toast.LENGTH_LONG).show()

//            for (selectedlists in selectedDates){
//                val  splitlist = selectedDates.split(",")
//                val sdf2 = SimpleDateFormat("EEEE")
//                val stringDate2: String = sdf2.format(splitlist.toString())
//}
//            selectedDates.forEach {
//            for (ss in selectedDates){
//                println("Today is: $ss")
//            }
//            }

//.trim().replace(Regex("""[CalendarDay{}]"""),"")


//             for (lll in removedList.indices){
//                 println("Today is: $lll[0]")
//             }
//             for (list in removedList){
////                 val sdf2 = SimpleDateFormat("EEEE")
////                val nameDay: String = sdf2.format(list)
////                 if (nameDay == "SUNDAY"){
//
////                 }
//             }