package com.penkin.weatherappkotlin.model

object Settings {

    const val defaultCity = "Moscow" //if have no lastKey, no location
    var currentCity = "" //current city from choose
    var locationCity = "" //current location city
    var units: String? = null
    var theme = "spring"
    var isNotificationOn = false
}