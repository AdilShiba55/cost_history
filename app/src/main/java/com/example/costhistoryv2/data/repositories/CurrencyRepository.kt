package com.example.costhistory.data.repositories

import android.content.Context
import com.example.costhistory.data.dao.CurrencyDao
import com.example.costhistory.data.entities.Currency
import com.example.costhistoryv2.data.AppDatabase
import org.koin.core.component.KoinComponent

class CurrencyRepository(context: Context) {

    private lateinit var currencyDao: CurrencyDao

    init {
        val db: AppDatabase = AppDatabase.getDatabase(context)
        currencyDao = db.currencyDao()
    }


    suspend fun getByCode(code: String): Currency {
        return currencyDao.getByCode(code)
    }

    suspend fun getAll(): List<Currency> {
        return currencyDao.getAll()
    }

    suspend fun insertAll(currencies: List<Currency>) {
        currencyDao.insertAll(currencies)
    }
}