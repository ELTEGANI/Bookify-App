package com.samemtech.androidcalenderview

import android.graphics.Color
import android.graphics.Typeface
import android.text.style.BackgroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade


class ReservedDaysDecorator(private val color: Int, dates: List<CalendarDay>) :DayViewDecorator {
    private val dates: HashSet<CalendarDay> = HashSet(dates)

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(BackgroundColorSpan(Color.RED))
        view.addSpan(StyleSpan(Typeface.BOLD))
        view.addSpan(RelativeSizeSpan(2.0f))
        view.setDaysDisabled(true)
    }

}