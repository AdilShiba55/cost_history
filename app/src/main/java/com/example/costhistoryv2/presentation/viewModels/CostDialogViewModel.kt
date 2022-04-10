package com.example.costhistoryv2.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import com.example.costhistory.units.IconUnit

class CostDialogViewModel(

) {
    var id = mutableStateOf(0L)
    var title = mutableStateOf("")
    var description = mutableStateOf("")
}