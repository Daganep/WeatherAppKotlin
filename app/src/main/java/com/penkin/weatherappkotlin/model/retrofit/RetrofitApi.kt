package com.penkin.weatherappkotlin.model.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.penkin.weatherappkotlin.model.entity.OpenWeatherResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {
    private val baseUrl: String = "https://api.openweathermap.org/"
    private val api: IRetrofitService

    init{
        val gson: Gson = GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
        val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(gson)

        api = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(IRetrofitService::class.java)
    }

    fun requestServer(city: String, apiKey: String, units: String?): Observable<OpenWeatherResponse>
        = api.getWeather(city, apiKey, units).subscribeOn(Schedulers.io())
}