package com.example.costhistoryv2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.costhistory.data.dao.*
import com.example.costhistory.data.entities.*
import com.example.costhistoryv2.data.dao.CostCategoryDao
import com.example.costhistoryv2.data.entities.CostCategory
import com.example.costhistoryv2.data.entities.CostCharacter
import com.example.costhistoryv2.domain.units.converters.DateConverter

@TypeConverters(DateConverter::class)
@Database(
    entities = [
        CostCategory::class, CostTemplateTitle::class, CostCharacter::class, Currency::class,
        Settings::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun costCategoryDao(): CostCategoryDao
    abstract fun costTemplateTitleDao(): CostTemplateTitleDao
    abstract fun costCharacterDao(): CostCharacterDao
    abstract fun currencyDao(): CurrencyDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cost_history_database"
                )
                    .fallbackToDestructiveMigration()
//                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }

        }
    }
}