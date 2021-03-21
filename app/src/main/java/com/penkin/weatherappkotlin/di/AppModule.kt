package com.penkin.weatherappkotlin.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.penkin.weatherappkotlin.model.database.CityHistoryDatabase
import com.penkin.weatherappkotlin.model.retrofit.RetrofitApi
import com.penkin.weatherappkotlin.utils.ImageSetter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): CityHistoryDatabase {
        return Room.databaseBuilder(
                context,
                CityHistoryDatabase::class.java, "nasalib_database")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitApi(): RetrofitApi {
        return RetrofitApi()
    }

    @Singleton
    @Provides
    fun provideImageSetter(): ImageSetter {
        return ImageSetter()
    }

    @Singleton
    @Provides
    fun provideContext() = application
}