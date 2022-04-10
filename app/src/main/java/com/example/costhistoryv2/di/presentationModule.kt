package com.example.costhistoryv2.di

import com.example.costhistoryv2.presentation.dialog.DialogUnit
import com.example.costhistoryv2.presentation.navigation.Navigator
import com.example.costhistoryv2.presentation.viewModels.CostCategoryDialogViewModel
import com.example.costhistoryv2.presentation.viewModels.CostCharacterDialogViewModel
import com.example.costhistoryv2.presentation.viewModels.CostDialogViewModel
import com.example.costhistoryv2.presentation.viewModels.CostHistoryViewModel
import org.koin.dsl.module

val presentationModule = module {
    single { Navigator() }
    single { DialogUnit() }
    single {  }

    factory { CostCategoryDialogViewModel( get() ) }
    factory { CostCharacterDialogViewModel( get() ) }
    factory { CostHistoryViewModel( get(), get() ) }
    factory { CostDialogViewModel() }
}