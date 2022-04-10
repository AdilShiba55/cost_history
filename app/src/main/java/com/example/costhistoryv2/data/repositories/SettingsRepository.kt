package com.example.costhistory.data.repositories

import android.content.Context
import com.example.costhistory.data.dao.SettingsDao
import com.example.costhistory.data.entities.Settings
import com.example.costhistoryv2.data.AppDatabase

class SettingsRepository(context: Context) {

    private lateinit var settingsDao: SettingsDao

    init {
        val db: AppDatabase = AppDatabase.getDatabase(context)
        settingsDao = db.settingsDao()
    }

    suspend fun insert(settings: Settings): Long {
        var size = getAll().size
        if(size == 0) {
            return settingsDao.insert(settings)
        } else return -1
    }
    suspend fun getAll(): List<Settings> {
        return settingsDao.getAll()
    }
    suspend fun get(): Settings {
        return settingsDao.getAll()[0]
    }


}