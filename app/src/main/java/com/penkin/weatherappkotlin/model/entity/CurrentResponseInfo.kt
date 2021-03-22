package com.penkin.weatherappkotlin.model.entity

import com.penkin.weatherappkotlin.application.Constants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CurrentResponseInfo(val response: OpenWeatherResponse?, private val tempU: String?){

    private lateinit var sdf: SimpleDateFormat
    private lateinit var sdd: SimpleDateFormat
    private lateinit var calendar: Calendar
    private var tempUnits: String = if(tempU == "imperial") "°F"
    else "°C"

    init {
        makeDate()
    }

    private fun makeDate(){
        val currentDate = Date()
        val dt = currentDate.toString() // Start date
        sdf = SimpleDateFormat("dd MMMM", Locale.ENGLISH)
        sdd = SimpleDateFormat("EEEE", Locale.ENGLISH)
        calendar = Calendar.getInstance()
        try {
            calendar.time = Objects.requireNonNull(sdf.parse(dt))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    fun getCityName(): String? {
        return response?.city?.name
    }

    fun getCurrentTemp(): String? {
        return java.lang.String.format(Locale.getDefault(), "%.0f", response?.list?.get(0)?.main?.temp).toString() + tempUnits
    }

    fun getCurrentImagePath(): String? {
        return Constants.IMAGE_PATH + (response?.list?.get(0)?.weather?.get(0)?.icon) + Constants.IMAGE_FORMAT
    }

    fun getCurrentTempDescription(): String? {
        return response?.list?.get(0)?.weather?.get(0)?.description
    }

    fun getGetCurrentTempSens(): String? {
        return "Feels like: " + java.lang.String.format(Locale.getDefault(), "%.0f", response?.list?.get(0)?.main?.feelsLike) + tempUnits
    }

    fun getWindSpeed(): String? {
        return java.lang.String.format(Locale.getDefault(), "%.0f", response?.list?.get(0)?.wind?.speed).toString() + " meter/sec"
    }

    fun getPressure(): String? {
        return java.lang.String.format(Locale.getDefault(), "%.0f", response?.list?.get(0)?.main?.pressure).toString() + " hPa"
    }

    fun getHumidity(): String? {
        return java.lang.String.format(Locale.getDefault(), "%.0f", response?.list?.get(0)?.main?.humidity).toString() + " %"
    }

    fun getOtherDayDate(i: Int): String? {
        calendar.add(Calendar.DATE, i + 1) // number of days to add
        return sdf.format(calendar.time)
    }

    fun getOtherDay(i: Int): String? {
        calendar.add(Calendar.DATE, i + 1) // number of days to add
        return sdd.format(calendar.time)
    }

    fun getOtherDayImagePath(i: Int): String? {
        return Constants.IMAGE_PATH + response?.list?.get(i)?.weather?.get(0)?.icon + Constants.IMAGE_FORMAT
    }

    fun getOtherDayTemp(i: Int): String? {
        return java.lang.String.format(Locale.getDefault(), "%.0f", response?.list?.get(1)?.main?.tempMax).toString() + tempUnits
    }

    fun getOtherNightTemp(i: Int): String? {
        return java.lang.String.format(Locale.getDefault(), "%.0f", response?.list?.get(1)?.main?.tempMin).toString() + tempUnits
    }
}