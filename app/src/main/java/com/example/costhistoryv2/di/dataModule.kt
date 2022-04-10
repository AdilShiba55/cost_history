package com.example.costhistoryv2.di

import com.example.costhistory.data.repositories.CostCategoryRepository
import com.example.costhistory.data.repositories.CostCharacterRepository
import com.example.costhistory.data.repositories.CurrencyRepository
import com.example.costhistory.data.repositories.SettingsRepository
import org.koin.dsl.module

var dataModule = module {
    single { CostCategoryRepository( get() ) }
    single { CostCharacterRepository( get() ) }
    single { CurrencyRepository( get() ) }
    single { SettingsRepository( get() ) }
}