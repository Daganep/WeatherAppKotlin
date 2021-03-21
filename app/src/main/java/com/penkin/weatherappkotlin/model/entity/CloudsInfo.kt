package com.penkin.weatherappkotlin.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CloudsInfo(
    @Expose
    @field:SerializedName("all")val all: Int?
)