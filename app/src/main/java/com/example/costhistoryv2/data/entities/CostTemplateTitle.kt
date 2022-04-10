package com.example.costhistory.data.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.costhistoryv2.domain.units.converters.DateConverter
import java.util.*

@Entity(tableName = "cost_template_title")
data class CostTemplateTitle (
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var title: String,
    @TypeConverters(DateConverter::class)
    var dtCreate: Date,
    var costCategoryId: Long
) {
    @Ignore
    constructor(title: String, category: Long): this(0, title, Date(), category)
}