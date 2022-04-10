package com.example.costhistory.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.costhistory.data.entities.CostTemplateTitle

@Dao
interface CostTemplateTitleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(costTemplateTitles: List<CostTemplateTitle>)
//    suspend fun insert(costTemplateTitle: CostTemplateTitle): Long

    @Query("select * from cost_template_title order by id desc")
    suspend fun getAll(): List<CostTemplateTitle>

    @Query("""
            select * from cost_template_title
            where costCategoryId = :costCategory
        """)
    suspend fun getAllByCategory(costCategory: Long): List<CostTemplateTitle>

    @Query(
        """
            delete from cost_template_title
            where costCategoryId = :id
        """
    )
    suspend fun deleteAllByCategory(id: Long)
}