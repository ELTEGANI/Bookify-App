package com.samemtech.androidcalenderview

import android.content.Context
import android.graphics.drawable.Drawable
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.MaterialCalendarView


class RangeDayDecorator(context: Context) : DayViewDecorator {
    private val list: HashSet<CalendarDay> = HashSet()
    private val drawable: Drawable
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return list.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.setSelectionDrawable(drawable)
    }

    /**
     * We're changing the dates, so make sure to call [MaterialCalendarView.invalidateDecorators]
     */
    fun addFirstAndLast(first: CalendarDay, last: CalendarDay) {
        list.clear()
        list.add(first)
        list.add(last)
    }

    init {
        drawable = context.getResources().getDrawable(R.drawable.my_selector)
    }
}