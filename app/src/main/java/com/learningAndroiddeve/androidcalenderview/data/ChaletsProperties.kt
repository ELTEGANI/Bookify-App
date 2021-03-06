package com.learningAndroiddeve.androidcalenderview.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data  class ChaletsProperties (
 var chaletName:String,
 var chaletDescriptions:String,
 var chaletNormalDayPrice:Double,
 var chaletHolidayPrice:Double,
 var chaletInsurancePrice:Double,
 var chaletCommissionsValue:Double,
 var chaletAvailableDays:List<String>,
 var chaletImages:List<String>
 ):Parcelable

