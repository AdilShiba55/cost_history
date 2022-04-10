package com.example.costhistoryv2.presentation.navigation

import com.example.costhistoryv2.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String)
{
    object Home : NavigationItem("home", R.drawable.ic_home, "Общее")
    object CostHistory : NavigationItem("cost_history", R.drawable.ic_note, "Расходы")
    object IncomeHistory : NavigationItem("income_history", R.drawable.ic_note, "Доходы")
    object Settings : NavigationItem("settings", R.drawable.ic_engineering, "Настройки")
}