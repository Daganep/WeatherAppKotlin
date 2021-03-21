package com.penkin.weatherappkotlin.application

import android.app.Application
import com.penkin.weatherappkotlin.di.AppComponent
import com.penkin.weatherappkotlin.di.AppModule
import com.penkin.weatherappkotlin.di.DaggerAppComponent

class WeatherApp: Application() {
    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = generateAppComponent()
    }

    private fun generateAppComponent(): AppComponent{
        return DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}