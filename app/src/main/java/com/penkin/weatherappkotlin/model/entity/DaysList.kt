package com.penkin.weatherappkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DaysList(
    @Expose
    @SerializedName("dt")val dt: Long?,
    @Expose
    @SerializedName("main")val main: MainInfo?,
    @Expose
    @SerializedName("weather")val weather: Array<WeatherInfo>?,
    @Expose
    @SerializedName("clouds")val clouds: CloudsInfo?,
    @Expose
    @SerializedName("wind")val wind: WindInfo?,
    @Expose
    @SerializedName("dt_txt")val dtTxt: String?
)