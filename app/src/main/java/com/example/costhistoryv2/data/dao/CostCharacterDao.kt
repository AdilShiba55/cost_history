package com.example.costhistory.data.dao

import androidx.room.*
import com.example.costhistoryv2.data.entities.CostCharacter

@Dao
interface CostCharacterDao {
    @Insert
    suspend fun insert(costCharacter: CostCharacter): Long

    @Update
    suspend fun update(costCharacter: CostCharacter)

    @Query("select * from cost_character order by id desc")
    suspend fun getAll(): List<CostCharacter>

    @Query("select * from cost_character where id = :id")
    suspend fun get(id: Long): CostCharacter

    @Delete
    suspend fun delete(costCharacter: CostCharacter)
}