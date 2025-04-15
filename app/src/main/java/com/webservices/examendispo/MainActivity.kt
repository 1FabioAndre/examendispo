package com.webservices.examendispo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.webservices.examendispo.data.AppDatabase
import com.webservices.examendispo.data.repository.ExpenseRepository
import com.webservices.examendispo.presentation.screens.RegisterExpenseScreen
import com.webservices.examendispo.presentation.screens.ExpenseListScreen
import com.webservices.examendispo.data.repository.IncomeRepository
import com.webservices.examendispo.presentation.screens.RegisterIncomeScreen
import com.webservices.examendispo.presentation.screens.IncomeListScreen
import com.webservices.examendispo.presentation.screens.HomeScreen
import com.webservices.examendispo.presentation.viewmodel.ExpenseViewModel
import com.webservices.examendispo.presentation.viewmodel.ExpenseViewModelFactory
import com.webservices.examendispo.presentation.viewmodel.IncomeViewModel
import com.webservices.examendispo.presentation.viewmodel.IncomeViewModelFactory
import com.webservices.examendispo.ui.theme.ExamenDispoTheme

import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {

    private lateinit var viewModelExpense: ExpenseViewModel
    private lateinit var viewModelIncome: IncomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)

        val expenseRepository = ExpenseRepository(database.expenseDao())
        val expenseViewModelFactory = ExpenseViewModelFactory(expenseRepository)
        viewModelExpense = ViewModelProvider(this, expenseViewModelFactory)[ExpenseViewModel::class.java]

        val incomeRepository = IncomeRepository(database.incomeDao())
        val incomeViewModelFactory = IncomeViewModelFactory(incomeRepository)
        viewModelIncome = ViewModelProvider(this, incomeViewModelFactory)[IncomeViewModel::class.java]

//        setContent {
//            ExamenDispoTheme {
//                ExpenseScreen(viewModel = viewModel)
//            }
//        }
            setContent {
                ExamenDispoTheme {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(
                                onNavigateToRegisterExpenses = { navController.navigate("registerExpense") },
                                onNavigateToExpenses = { navController.navigate("listExpenses") },
                                onNavigateToRegisterIncomes = { navController.navigate("registerIncome") },
                                onNavigateToIncomes = { navController.navigate("listIncomes") },
                                )
                        }
                        composable("registerExpense") {
                            RegisterExpenseScreen(viewModel = viewModelExpense)
                        }
                        composable("listExpenses") {
                            ExpenseListScreen(viewModel = viewModelExpense)
                        }
                        composable("registerIncome") {
                            RegisterIncomeScreen(viewModel = viewModelIncome)
                        }
                        composable("listIncomes") {
                            IncomeListScreen(viewModel = viewModelIncome)
                        }
                    }
                }
            }
    }
}