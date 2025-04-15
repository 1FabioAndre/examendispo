package com.webservices.examendispo.data.repository

import com.webservices.examendispo.data.dao.IncomeDao
import com.webservices.examendispo.data.entities.IncomeEntity

class IncomeRepository (private  val incomeDao: IncomeDao) {
    suspend fun insertIncome(income: IncomeEntity) {
        incomeDao.insertIncome(income)
    }

    suspend fun deleteIncome(income: IncomeEntity) {
        incomeDao.deleteIncome(income)
    }

    fun getAllIncome(): List<IncomeEntity> {
        return incomeDao.getAllIncomes()
    }
}