package com.penkin.weatherappkotlin.model.retrofit

import com.penkin.weatherappkotlin.model.entity.OpenWeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofitService {
    @GET("data/2.5/forecast")
    fun getWeather(@Query("q") city: String?,
                   @Query("appid")keyApi: String,
                   @Query("units")units: String): Observable<OpenWeatherResponse>
}