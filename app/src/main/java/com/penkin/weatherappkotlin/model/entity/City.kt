package com.penkin.weatherappkotlin.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "table_cities")
class City(): Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name: String? = null
    var country: String? = null

    constructor(city: String) : this() {
        this.name = city
    }
}