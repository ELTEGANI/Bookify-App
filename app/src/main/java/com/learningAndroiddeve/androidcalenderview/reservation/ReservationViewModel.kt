package com.learningAndroiddeve.androidcalenderview.reservation

import android.app.Application
import androidx.lifecycle.ViewModel
import com.learningAndroiddeve.androidcalenderview.utiles.dayNameNumbers
import com.prolificinteractive.materialcalendarview.CalendarDay
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ReservationViewModel(var application: Application) : ViewModel() {
    fun calculatePrice(
        datesList: List<CalendarDay>,
        chaletPercentage: String,
        chaletInsurance: String,
        chaletCommision: String,
        chaletNormalPrice: String,
        chaletWeekEndPrice: String
    ): MutableList<String> {
        var totalPrice= 0
        val percentage = chaletPercentage.toInt()
        val insurance  = chaletInsurance.toInt()
        val commission = chaletCommision.toInt()
        val normalPrice= chaletNormalPrice.toInt()
        val weekEndPrice = chaletWeekEndPrice.toInt()
        viewModelScope.launch {
            val totalCommissionAndInsurance = insurance.plus(commission)
            val totalPercentage:Int
            val dayOfWeek = dayNameNumbers(datesList)
            val weekEndDays = dayOfWeek.groupingBy { it }.eachCount().filter { it.key in 5..6 }
            val normalDays = dayOfWeek.groupingBy { it }.eachCount().filter { it.key == 7 || it.key in 1..4 }
            if (weekEndDays.isNotEmpty()) {
                val totalWeekEndDays = weekEndDays.values.sum().times(weekEndPrice)
                val totalNormalEndDays = normalDays.values.sum().times(normalPrice)
                totalPrice = totalWeekEndDays.plus(totalNormalEndDays).plus(totalCommissionAndInsurance)
                if(percentage > 0){
                    totalPercentage = totalPrice.times(percentage).div(100)
                    totalPrice = totalPrice.minus(totalPercentage)
                }
            }else{
                val totalPriceWithoutWeekEndDays = normalDays.values.sum().times(normalPrice).plus(totalCommissionAndInsurance)
                totalPercentage = totalPriceWithoutWeekEndDays.times(percentage).div(100)
                totalPrice = totalPriceWithoutWeekEndDays.minus(totalPercentage)
            }
        }
        val priceDetailes = mutableListOf<String>()
        priceDetailes.add(normalPrice.toString())
        priceDetailes.add(weekEndPrice.toString())
        priceDetailes.add(insurance.toString())
        priceDetailes.add(commission.toString())
        priceDetailes.add(percentage.toString())
        priceDetailes.add(totalPrice.toString())
        return  priceDetailes
    }
}