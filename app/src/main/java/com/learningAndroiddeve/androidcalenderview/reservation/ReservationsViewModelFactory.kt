package com.learningAndroiddeve.androidcalenderview.reservation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ReservationsViewModelFactory (var application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReservationViewModel::class.java)) {
            return ReservationViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}