package com.example.costhistory.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import com.example.costhistoryv2.data.dao.CostCategoryDao
import com.example.costhistory.data.dao.CostTemplateTitleDao
import com.example.costhistoryv2.data.entities.CostCategory
import com.example.costhistory.data.entities.CostTemplateTitle
import com.example.costhistoryv2.data.AppDatabase

@SuppressLint("StaticFieldLeak")
class CostCategoryRepository(context: Context) {

    lateinit var costCategoryDao: CostCategoryDao
    lateinit var costTemplateTitleDao: CostTemplateTitleDao

    init {
        val db: AppDatabase = AppDatabase.getDatabase(context)
        costCategoryDao = db.costCategoryDao()
        costTemplateTitleDao = db.costTemplateTitleDao()
    }

    suspend fun getAll(): List<CostCategory> {
        return costCategoryDao.getAll()
    }

    suspend fun get(id: Long): CostCategory {
        var costCategoryData = costCategoryDao.getCostTemplateTitles(id)[0]
        val costCategory: CostCategory = costCategoryData.costCategory
        val costTemplateTitles: List<CostTemplateTitle> = costCategoryData.costTemplateTitles
        costTemplateTitles.forEach {
            costCategory.templateTitles.add(it.title)
        }
        return costCategory
    }

    suspend fun insert(costCategory: CostCategory): Long {
        var category: Long = costCategoryDao.insert(costCategory)
        var titles: MutableList<CostTemplateTitle> = mutableListOf()
        if(costCategory.templateTitles.isNotEmpty()) {
            costCategory.templateTitles.forEach {
                val title = CostTemplateTitle(title = it, category = category)
                titles.add(title)
            }
            costTemplateTitleDao.insertAll(titles)
        }
        return category
    }

    suspend fun update(costCategory: CostCategory) {
        var category: Long = costCategory.id
        costCategoryDao.update(costCategory)
        costTemplateTitleDao.deleteAllByCategory(category)
        var titles: MutableList<CostTemplateTitle> = mutableListOf()
        if(costCategory.templateTitles.isNotEmpty()) {
            costCategory.templateTitles.forEach {
                val title = CostTemplateTitle(title = it, category = category)
                titles.add(title)
            }
            costTemplateTitleDao.insertAll(titles)
        }
    }

    suspend fun delete(costCategory: CostCategory) {
        costCategoryDao.delete(costCategory)
    }
    suspend fun delete(id: Long) {
        costCategoryDao.delete(id)
    }

}