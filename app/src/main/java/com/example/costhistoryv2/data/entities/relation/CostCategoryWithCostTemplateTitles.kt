package com.example.costhistoryv2.data.entities.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.costhistoryv2.data.entities.CostCategory
import com.example.costhistory.data.entities.CostTemplateTitle

data class CostCategoryWithCostTemplateTitles (
    @Embedded
    val costCategory: CostCategory,

    @Relation(parentColumn = "id", entityColumn = "costCategoryId")
    val costTemplateTitles: MutableList<CostTemplateTitle>
)