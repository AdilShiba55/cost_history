package com.example.costhistoryv2.domain.units

import com.example.costhistoryv2.presentation.navigation.NavigationItem

object NavigationUnit {
    val drawerItems: List<NavigationItem> = listOf(
        NavigationItem.Home,
        NavigationItem.CostHistory,
        NavigationItem.IncomeHistory,
        NavigationItem.Settings
    )
}