package com.example.costhistory.data.repositories

import android.content.Context
import com.example.costhistory.data.dao.CostCharacterDao
import com.example.costhistoryv2.data.AppDatabase
import com.example.costhistoryv2.data.entities.CostCharacter

class CostCharacterRepository(context: Context) {

    private lateinit var costCharacterDao: CostCharacterDao

    init {
        val db: AppDatabase = AppDatabase.getDatabase(context)
        costCharacterDao = db.costCharacterDao()
    }

    suspend fun insert(costCharacter: CostCharacter): Long {
        return costCharacterDao.insert(costCharacter)
    }
    suspend fun update(costCharacter: CostCharacter) {
        costCharacterDao.update(costCharacter)
    }
    suspend fun delete(costCharacter: CostCharacter) {
        costCharacterDao.delete(costCharacter)
    }
    suspend fun getAll(): List<CostCharacter> {
        return costCharacterDao.getAll()
    }
    suspend fun get(id: Long): CostCharacter {
        return costCharacterDao.get(id)
    }
}