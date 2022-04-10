package com.example.costhistory.data.dao

import androidx.room.*
import com.example.costhistory.data.entities.Currency

@Dao
interface CurrencyDao {
    @Query("select * from currency order by id")
    suspend fun getAll(): List<Currency>

    @Query("select * from currency where lower(code) = lower(:code)")
    suspend fun getByCode(code: String): Currency

    @Query("select * from currency where id = :id")
    suspend fun get(id: Long): Currency

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<Currency>)
}