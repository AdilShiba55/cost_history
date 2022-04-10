package com.example.costhistory.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Settings(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var currencyId: Long
) {
    constructor(currencyId: Long): this(0, currencyId)
}