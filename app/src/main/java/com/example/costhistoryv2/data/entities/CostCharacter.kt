package com.example.costhistoryv2.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.costhistoryv2.domain.units.converters.DateConverter
import java.util.*

@Entity(tableName = "cost_character")
data class CostCharacter(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var img: Int,
    val costId: Long,
    @TypeConverters(DateConverter::class)
    var dtCreate: Date,
    @TypeConverters(DateConverter::class)
    var dtModify: Date,
) {
    constructor(): this(id = 0L, name = "", img = 0, costId = 0, Date(), Date())

    fun isNew(): Boolean {
        return id <= 0
    }
    fun iHaveIcon(): Boolean {
        return img != 0 && img != -1
    }
}

