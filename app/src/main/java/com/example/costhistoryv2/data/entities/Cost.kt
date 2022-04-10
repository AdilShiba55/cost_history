package com.example.costhistoryv2.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.costhistoryv2.domain.units.converters.DateConverter
import java.util.*

@Entity(tableName = "cost")
data class Cost(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var sum: Int,
    var title: String,
    var description: String,
    var necessary: Boolean,
    var imOkWithPrice: Boolean,
    var imOkWithCost: Boolean,
    @TypeConverters(DateConverter::class)
    var dtCreate: Date,
    @TypeConverters(DateConverter::class)
    var dtModify: Date,

    var costCategoryId: Long,
    var costCharacterId: Long
) {

}