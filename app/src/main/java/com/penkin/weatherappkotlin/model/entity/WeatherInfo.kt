package com.penkin.weatherappkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherInfo(
    @Expose
    @SerializedName("id")val id: Int?,
    @Expose
    @SerializedName("main")val main: String?,
    @Expose
    @SerializedName("description")val description: String?,
    @Expose
    @SerializedName("icon")val icon: String?
)