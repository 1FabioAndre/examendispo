package com.webservices.examendispo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.webservices.examendispo.data.AppDatabase
import com.webservices.examendispo.data.repository.ExpenseRepository
import com.webservices.examendispo.presentation.screens.ExpenseScreen
import com.webservices.examendispo.presentation.viewmodel.ExpenseViewModel
import com.webservices.examendispo.presentation.viewmodel.ExpenseViewModelFactory
import com.webservices.examendispo.ui.theme.ExamenDispoTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)

        val repository = ExpenseRepository(database.expenseDao())

        val viewModelFactory = ExpenseViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory)[ExpenseViewModel::class.java]
        setContent {
            ExamenDispoTheme {
                ExpenseScreen(viewModel = viewModel)
            }
        }
    }
}