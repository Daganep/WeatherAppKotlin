package com.penkin.weatherappkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OpenWeatherResponse(
    @Expose
    @SerializedName("cod")val cod: String?,
    @Expose
    @SerializedName("message")val message: Int?,
    @Expose
    @SerializedName("cnt")val cnt: Int?,
    @Expose
    @SerializedName("list")val list: Array<DaysList>?,
    @Expose
    @SerializedName("city")val city: CurrentCity?
)