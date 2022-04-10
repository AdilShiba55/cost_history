package com.example.costhistory.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency")
data class Currency(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var from: String,
    var name: String,
    var symbol: String,
    var code: String
) {
    constructor(from: String, name: String, symbol: String, code: String):
            this(0, from, name, symbol, code)
}