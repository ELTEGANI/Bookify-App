package com.learningAndroiddeve.androidcalenderview.allchalets

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class AllFragmentsViewModelFactory (private val application: Application): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllFragmentsViewModel::class.java)) {
            return AllFragmentsViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}