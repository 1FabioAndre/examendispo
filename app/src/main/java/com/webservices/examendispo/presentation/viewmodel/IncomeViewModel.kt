package com.webservices.examendispo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webservices.examendispo.data.entities.IncomeEntity
import com.webservices.examendispo.data.repository.IncomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IncomeViewModel(private val repository: IncomeRepository) : ViewModel() {

    suspend fun addIncome(name: String, price: Double, description: String, date: Long) {
        withContext(Dispatchers.IO) {
            val income = IncomeEntity(
                name = name,
                price = price,
                description = description,
                date = date
            )
            repository.insertIncome(income)
        }
    }

    suspend fun deleteIncome(income: IncomeEntity) {
        withContext(Dispatchers.IO) {
            repository.deleteIncome(income)
        }
    }

    suspend fun getAllIncomes(): List<IncomeEntity> {
        return withContext(Dispatchers.IO) {
            repository.getAllIncome()
        }
    }
}