package com.penkin.weatherappkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentCity(
    @Expose
    @SerializedName("id")val id: Int?,
    @Expose
    @SerializedName("name")val name: String?,
    @Expose
    @SerializedName("coord")val coordinates: Coordinates?,
    @Expose
    @SerializedName("country")val country: String?,
    @Expose
    @SerializedName("timezone")val timezone: Int?,
    @Expose
    @SerializedName("sunrise")val sunrise: Long?,
    @Expose
    @SerializedName("sunset")val sunset: Long?
)