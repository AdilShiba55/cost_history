package com.example.costhistory.units

import com.example.costhistory.data.entities.Currency

object DatabaseDefaultUnit {
    fun getCurrencies(): List<Currency> {
        return listOf(
            Currency("United States", "United States dollar", "$", "USD"),
            Currency("Russia", "Russian ruble", "₽", "RUB"),
            Currency("Kazakhstan", "Kazakhstani tenge", "₸", "KZT"),
            Currency("Germany", "Euro", "€", "EUR"),
            Currency("Poland", "Polish złoty", "zł", "PLN")
        )
    }
}