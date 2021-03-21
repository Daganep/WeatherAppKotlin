package com.penkin.weatherappkotlin.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.penkin.weatherappkotlin.model.entity.City

@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class CityHistoryDatabase: RoomDatabase() {
    abstract fun cityDao(): ICityDao
}