package com.example.costhistoryv2.presentation.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.costhistory.data.repositories.CostCharacterRepository
import com.example.costhistoryv2.data.entities.CostCharacter
import com.example.costhistory.data.repositories.CostCategoryRepository
import com.example.costhistory.units.IconUnit
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.getKoin
import java.util.*

class CostCharacterDialogViewModel(
    val costCharacterRepository: CostCharacterRepository
): ViewModel() {

    var costCharacters = mutableStateOf(mutableStateListOf<CostCharacter>())

    fun fetchCostCharacters() {
        viewModelScope.launch {
            costCharacters.value.clear()
            costCharacters.value.addAll(costCharacterRepository.getAll())
        }
    }

    var id = mutableStateOf(0L)
    var title = mutableStateOf("")
    val icons = IconUnit.getCharacterIcons()
    var selectedIconIndex = mutableStateOf(-1)

    fun isNew(): Boolean {
        return id.value <= 0
    }
    fun clear() {
        id.value = 0
        title.value = ""
        selectedIconIndex.value = -1
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun set(costCharacterId: Long = 0) {
        viewModelScope.launch {
            val costCharacter = if(costCharacterId > 0) costCharacterRepository.get(costCharacterId) else CostCharacter()
            val theSameClick = id.value == costCharacter.id
            clear()
            if(!theSameClick) {
                id.value = costCharacter.id
                title.value = costCharacter.name
                icons.forEachIndexed { index, item ->
                    if(item == costCharacter.img) selectedIconIndex.value = index
                }
            }
        }
    }

    private fun buildItem(): CostCharacter {
        var costCharacter = CostCharacter()
        costCharacter.id = id.value
        costCharacter.name = title.value
        if(selectedIconIndex.value != -1) {
            costCharacter.img = icons[selectedIconIndex.value]
        }
        return costCharacter
    }

    fun saveCostCharacter() {
        viewModelScope.launch {
            var costCharacter = buildItem()
            costCharacter.name = title.value.trim()
            if(isNew()) {
                costCharacter.dtCreate = Date()
                costCharacter.dtModify = costCharacter.dtCreate
                costCharacterRepository.insert(costCharacter)
            } else {
                costCharacter.dtModify = Date()
                costCharacterRepository.update(costCharacter)
            }
            clear()
            fetchCostCharacters()
        }
    }
//    fun removeCostCharacter() {
//        var costCharacter = buildItem()
//        if(!isNew()) {
//            viewModelScope.launch {
//                costCharacterRepository.delete(costCharacter)
//            }
//        }
//        clear()
//    }

    fun removeCostCharacter(costCharacter: CostCharacter) {
        if(!isNew()) {
            viewModelScope.launch {
                costCharacterRepository.delete(costCharacter)
                fetchCostCharacters()
            }
        }
        clear()
    }

    init {
        fetchCostCharacters()
    }


}