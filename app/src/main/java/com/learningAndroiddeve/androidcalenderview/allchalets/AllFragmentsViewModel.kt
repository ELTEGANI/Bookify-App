package com.learningAndroiddeve.androidcalenderview.allchalets

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.learningAndroiddeve.androidcalenderview.R
import com.learningAndroiddeve.androidcalenderview.data.ChaletsProperties
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AllFragmentsViewModel(var application: Application) : ViewModel() {

    private val _allChalets = MutableLiveData<List<ChaletsProperties>>()
    val allChalets: LiveData<List<ChaletsProperties>>
        get() = _allChalets


     var allChaletsInformations = listOf(ChaletsProperties("chalet AAA" ,
         "For Families Only",120.0,14.0
     ,30.0,20.0,
         listOf("2020-8-16",
             "2021-1-17",
             "2021-1-18",
             "2021-1-19",
             "2021-1-20",
             "2021-1-21",
             "2021-1-22",
             "2021-1-23",
             "2021-1-24"),
         listOf("https://images.unsplash.com/photo-1576675784201-0e142b423952","https://images.unsplash.com/photo-1588621356760-480a27a2d105")),
         ChaletsProperties("Chalet BBB", "For Both",130.0,14.0
         ,30.0,20.0, listOf(
             "2021-2-16",
             "2021-2-17",
             "2021-2-18",
             "2021-2-19",
             "2021-2-20",
             "2021-2-21",
             "2021-2-22",
             "2021-2-23",
             "2021-2-24"),
          listOf("https://images.unsplash.com/photo-1590490360182-c33d57733427","https://images.unsplash.com/photo-1576675784201-0e142b423952"))
         , ChaletsProperties("Chalet CCC", "For Both",140.0,14.0
             ,30.0,20.0, listOf(
                 "2021-2-16",
                 "2021-2-17",
                 "2021-2-18",
                 "2021-2-19",
                 "2021-2-20",
                 "2021-2-21",
                 "2021-2-22",
                 "2021-2-23",
                 "2021-2-24"),
             listOf("https://images.unsplash.com/photo-1527760127628-23c37a0c9af9","https://images.unsplash.com/photo-1576675784201-0e142b423952"))
     , ChaletsProperties("Chalet DDD", "For Both",150.0,14.0
             ,30.0,20.0, listOf(
                 "2021-2-16",
                 "2021-2-17",
                 "2021-2-18",
                 "2021-2-19",
                 "2021-2-20",
                 "2021-2-21",
                 "2021-2-22",
                 "2021-2-23",
                 "2021-2-24"),
             listOf("https://images.unsplash.com/photo-1561501878-aabd62634533","https://images.unsplash.com/photo-1576675784201-0e142b423952"))
     , ChaletsProperties("Chalet EEE", "For Both",160.0,14.0
             ,30.0,20.0, listOf(
                 "2021-2-16",
                 "2021-2-17",
                 "2021-2-18",
                 "2021-2-19",
                 "2021-2-20",
                 "2021-2-21",
                 "2021-2-22",
                 "2021-2-23",
                 "2021-2-24"),
             listOf("https://images.unsplash.com/photo-1590490359854-dfba19688d70","https://images.unsplash.com/photo-1576675784201-0e142b423952"))
     , ChaletsProperties("Chalet FFF", "For Both",170.0,14.0
             ,30.0,20.0, listOf(
                 "2021-2-16",
                 "2021-2-17",
                 "2021-2-18",
                 "2021-2-19",
                 "2021-2-20",
                 "2021-2-21",
                 "2021-2-22",
                 "2021-2-23",
                 "2021-2-24"),
             listOf("https://images.unsplash.com/photo-1566073771259-6a8506099945",
                 "https://images.unsplash.com/photo-1576675784201-0e142b423952"))
     ,
         ChaletsProperties("Chalet GGG", "For Both",170.0,14.0
             ,30.0,20.0, listOf(
                 "2021-2-16",
                 "2021-2-17",
                 "2021-2-18",
                 "2021-2-19",
                 "2021-2-20",
                 "2021-2-21",
                 "2021-2-22",
                 "2021-2-23",
                 "2021-2-24"),
             listOf("https://images.unsplash.com/photo-1596394516093-501ba68a0ba6","https://images.unsplash.com/photo-1576675784201-0e142b423952"))
         , ChaletsProperties("Chalet HHH", "For Both",180.0,14.0
             ,30.0,20.0, listOf(
                 "2021-2-16",
                 "2021-2-17",
                 "2021-2-18",
                 "2021-2-19",
                 "2021-2-20",
                 "2021-2-21",
                 "2021-2-22",
                 "2021-2-23",
                 "2021-2-24"),
             listOf("https://images.unsplash.com/photo-1599392687131-d82ad47a871f",
                 "https://images.unsplash.com/photo-1576675784201-0e142b423952"))
     )

        init {
            viewModelScope.launch {
                displayAllChalets()
            }
        }

    private fun displayAllChalets(){
        if (allChaletsInformations.isNotEmpty()){
            _allChalets.value = allChaletsInformations
        }else{
            _allChalets.value = listOf()
        }
    }



}