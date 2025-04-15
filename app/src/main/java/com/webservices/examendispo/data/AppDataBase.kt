package com.webservices.examendispo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.webservices.examendispo.data.dao.ExpenseDao
import com.webservices.examendispo.data.entities.ExpenseEntity
import com.webservices.examendispo.data.dao.IncomeDao
import com.webservices.examendispo.data.entities.IncomeEntity

@Database(entities = [ExpenseEntity::class, IncomeEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun incomeDao(): IncomeDao

    companion object {
        @Volatile
        private  var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
        }
    }
}