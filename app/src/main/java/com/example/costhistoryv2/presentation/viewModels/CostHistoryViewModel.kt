package com.example.costhistoryv2.presentation.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.costhistory.data.repositories.CostCategoryRepository
import com.example.costhistory.data.repositories.CostCharacterRepository
import com.example.costhistoryv2.data.entities.CostCategory
import com.example.costhistoryv2.data.entities.CostCharacter
import com.example.costhistoryv2.presentation.dialog.DialogUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.getKoin

class CostHistoryViewModel(
    var costCategoryRepository: CostCategoryRepository,
    var costCharacterRepository: CostCharacterRepository
) : ViewModel() {

//    private val costCategoryRepository = getKoin().get<CostCategoryRepository>()
//    private val costCharacterRepository = getKoin().get<CostCharacterRepository>()

    var costCategories = mutableStateOf(mutableStateListOf<CostCategory>())

    fun fetchCostCategories() {
        costCategories.value.clear()
        viewModelScope.launch {
            costCategories.value.addAll(costCategoryRepository.getAll())
        }
    }

    var costCharacters = mutableStateOf(mutableStateListOf<CostCharacter>())

    fun fetchCostCharacters() {
        costCharacters.value.clear()
        viewModelScope.launch {
            costCharacters.value.addAll(costCharacterRepository.getAll())
        }
    }

//    fun openCategoryDialog(coroutineScope: CoroutineScope, costCategoryId: Long = 0L) {
//        costCategoryDialogViewModel.set(costCategoryId)
//        DialogViewModel.setDialogId(DialogUnit.CATEGORY_DIALOG)
//        DialogViewModel.showDialog(coroutineScope)
//    }
//    fun openCharacterDialog(coroutineScope: CoroutineScope, costCharacterId: Long = 0L) {
//        costCategoryDialogViewModel.set(costCharacterId)
//        DialogViewModel.setDialogId(DialogUnit.CHARACTER_DIALOG)
//        DialogViewModel.showDialog(coroutineScope)
//    }

    init {
//        fetchCostCategories()
//        fetchCostCharacters()
    }

}