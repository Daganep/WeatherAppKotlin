package com.penkin.weatherappkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WindInfo(
        @Expose
        @SerializedName("speed")val speed: Float?,
        @Expose
        @SerializedName("deg")val deg: Float?
)