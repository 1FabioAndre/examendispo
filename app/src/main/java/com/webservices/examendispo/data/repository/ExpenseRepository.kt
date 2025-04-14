package com.webservices.examendispo.data.repository

import com.webservices.examendispo.data.dao.ExpenseDao
import com.webservices.examendispo.data.entities.ExpenseEntity

class ExpenseRepository(private  val expenseDao: ExpenseDao) {

    suspend fun insertExpense(expense: ExpenseEntity) {
        expenseDao.insertExpense(expense)
    }

    suspend fun deleteExpense(expense: ExpenseEntity) {
        expenseDao.deleteExpense(expense)
    }

    fun getAllExpense(): List<ExpenseEntity> {
        return expenseDao.getAllExpenses()
    }
}