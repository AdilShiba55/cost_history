package com.example.costhistoryv2.presentation.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.costhistory.data.repositories.CostCategoryRepository
import com.example.costhistory.units.IconUnit
import com.example.costhistoryv2.data.entities.CostCategory
import kotlinx.coroutines.launch
import java.util.*

class CostCategoryDialogViewModel(
    var costCategoryRepository: CostCategoryRepository
): ViewModel() {

    var costCategories = mutableStateOf(mutableStateListOf<CostCategory>())

    fun fetchCostCategories() {
        viewModelScope.launch {
            costCategories.value.clear()
            costCategories.value.addAll(costCategoryRepository.getAll())
        }
    }

    var id = mutableStateOf(0L)
    val icons = IconUnit.getAllIcons()
    var title = mutableStateOf("")
    var description = mutableStateOf("")
    var selectedIconIndex = mutableStateOf(-1)
    var currentCostTemplateTitle = mutableStateOf("")
    var costTemplateTitles = mutableStateOf(mutableStateListOf<String>())

    fun isNew(): Boolean {
        return id.value <= 0
    }
    fun clear() {
//        LocalViewModelStoreOwner.current.viewModelStore.
        id.value = 0
        title.value = ""
        description.value = ""
        selectedIconIndex.value = -1
        currentCostTemplateTitle.value = ""
        costTemplateTitles.value = mutableStateListOf()
    }
    fun set(costCategoryId: Long) {
        viewModelScope.launch {
            val costCategory = if(costCategoryId > 0) costCategoryRepository.get(costCategoryId) else CostCategory()
            val theSameClick = id.value == costCategory.id
            clear()
            if(!theSameClick) {
                id.value = costCategory.id
                title.value = costCategory.title
                description.value = costCategory.description
                icons.forEachIndexed { index, item ->
                    if(item == costCategory.img) selectedIconIndex.value = index
                }
                costTemplateTitles.value.clear()
                costTemplateTitles.value.addAll(costCategory.templateTitles)
            }
        }

    }
    fun addCostTemplateTitle() {
        costTemplateTitles.value.add(currentCostTemplateTitle.value.trim())
        currentCostTemplateTitle.value = ""
//        currentCostTemplateTitle = ""
    }
    fun removeCostTemplateTitle(index: Int) {
        costTemplateTitles.value.removeAt(index)
//        currentCostTemplateTitle = ""
    }

    fun buildItem(): CostCategory {
        val costCategory = CostCategory()
        costCategory.id = id.value
        costCategory.title = title.value
        costCategory.description = description.value
        if(selectedIconIndex.value != -1) {
            costCategory.img = icons[selectedIconIndex.value]
        }
        costCategory.templateTitles = costTemplateTitles.value
        return costCategory
    }

    fun saveCostCategory() {
        viewModelScope.launch {
            var costCategory = buildItem()
            costCategory.title = title.value.trim()
            costCategory.description = description.value.trim()
            if(isNew()) {
                costCategory.dtCreate = Date()
                costCategory.dtModify = costCategory.dtCreate
                costCategoryRepository.insert(costCategory)
            } else {
                costCategory.dtModify = Date()
                costCategoryRepository.update(costCategory)
            }
            clear()
            fetchCostCategories()
        }
    }

    fun removeCostCategory(costCategory: CostCategory) {
        viewModelScope.launch {
            costCategoryRepository.delete(costCategory)
            fetchCostCategories()
        }
        clear()
    }

    init {
        fetchCostCategories()
    }
}