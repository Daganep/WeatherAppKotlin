package com.penkin.weatherappkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MainInfo(
    @Expose
    @SerializedName("temp")val temp: Float?,
    @Expose
    @SerializedName("feels_like")val feelsLike: Float?,
    @Expose
    @SerializedName("pressure")val pressure: Float?,
    @Expose
    @SerializedName("humidity")val humidity: Float?,
    @Expose
    @SerializedName("temp_min")val tempMin: Float?,
    @Expose
    @SerializedName("temp_max")val tempMax: Float?
)