package com.learningAndroiddeve.androidcalenderview


import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade


class ReservedDaysDecorator(dates: List<CalendarDay>,priceText:String) :DayViewDecorator {
    private val dates: HashSet<CalendarDay> = HashSet(dates)
    var priceDay = priceText

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(AddTextToDates(priceDay))
        view.setDaysDisabled(true)
    }

}