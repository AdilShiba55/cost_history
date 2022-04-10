package com.example.costhistory.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.costhistory.data.entities.Settings

@Dao
interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(settings: Settings): Long

    @Query("select * from settings")
    suspend fun getAll(): List<Settings>
}