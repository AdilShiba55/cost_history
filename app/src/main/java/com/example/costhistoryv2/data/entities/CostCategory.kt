package com.example.costhistoryv2.data.entities

import androidx.compose.runtime.mutableStateListOf
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.costhistoryv2.domain.units.converters.DateConverter
import java.util.*

@Entity(tableName = "cost_category")
data class CostCategory (
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var img: Int,
    var title: String,
    var description: String,
    @TypeConverters(DateConverter::class)
    var dtCreate: Date,
    @TypeConverters(DateConverter::class)
    var dtModify: Date,
) {

    @Ignore
    var templateTitles: MutableList<String> = mutableStateListOf()

    @Ignore
    constructor(): this(0, 0, "", "", Date(), Date())

    fun isNew(): Boolean {
        return id <= 0
    }
    fun iHaveIcon(): Boolean {
        return img != 0 && img != -1
    }
}