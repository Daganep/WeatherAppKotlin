package com.penkin.weatherappkotlin.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.penkin.weatherappkotlin.model.entity.City
import io.reactivex.Single

@Dao
interface ICityDao {
    @Query("SELECT * FROM table_cities")
    fun getAll(): Single<MutableList<City?>?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(cities: MutableList<City?>?): Single<List<Long?>?>?

    @Query("DELETE FROM table_cities")
    fun deleteAll(): Single<Int?>?
}