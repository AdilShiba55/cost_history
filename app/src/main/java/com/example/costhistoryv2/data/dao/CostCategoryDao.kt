package com.example.costhistoryv2.data.dao

import androidx.room.*
import com.example.costhistoryv2.data.entities.CostCategory
import com.example.costhistoryv2.data.entities.relation.CostCategoryWithCostTemplateTitles

@Dao
interface CostCategoryDao {

    @Query("select * from cost_category order by id desc")
    suspend fun getAll(): List<CostCategory>

    @Query("select * from cost_category where id = :id")
    suspend fun get(id: Long): CostCategory

    @Insert
    suspend fun insert(costCategory: CostCategory): Long

    @Update
    suspend fun update(costCategory: CostCategory)

    @Delete
    suspend fun delete(costCategory: CostCategory)

    @Query("delete from cost_category where id = :id")
    suspend fun delete(id: Long)

    @Query("delete from cost_category")
    suspend fun deleteAll()

    @Transaction
    @Query(
        """
            SELECT * FROM cost_category 
            where id = :id 
            order by id desc
        """
    )
    suspend fun getCostTemplateTitles(id: Long): List<CostCategoryWithCostTemplateTitles>

}