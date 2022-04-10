package com.example.costhistoryv2.presentation

import android.app.Application
import android.content.Context
import com.example.costhistory.data.entities.Settings
import com.example.costhistory.data.repositories.CurrencyRepository
import com.example.costhistory.data.repositories.SettingsRepository
import com.example.costhistory.units.DatabaseDefaultUnit
import com.example.costhistoryv2.di.dataModule
import com.example.costhistoryv2.di.presentationModule
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.BuildConfig
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(listOf(dataModule, presentationModule))
        }
        setDefaultData()
        super.onCreate()
    }

    private fun setDefaultData() {
        var sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        var isFirstStart: Boolean = sharedPreferences.getBoolean("isFirstStart", true)
        if (isFirstStart) {
            val currencyRepository = get<CurrencyRepository>()
            val settingsRepository = get<SettingsRepository>()
            var editor = sharedPreferences.edit()
            editor.putBoolean("isFirstStart", false)
            editor.apply()

            MainScope().launch {
                currencyRepository.insertAll(DatabaseDefaultUnit.getCurrencies())
                var defaultCurrency = currencyRepository.getByCode("usd")
                var settings = Settings(defaultCurrency.id)
                settingsRepository.insert(settings)
            }
        }
    }
}