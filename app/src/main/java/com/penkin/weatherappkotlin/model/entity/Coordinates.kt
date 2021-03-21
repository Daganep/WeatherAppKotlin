package com.penkin.weatherappkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Coordinates(
    @Expose
    @field:SerializedName("lon")val lon: Float?,
    @Expose
    @field:SerializedName("lat")val lat: Float?
)