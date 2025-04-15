package com.webservices.examendispo.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToRegisterExpenses: () -> Unit,
    onNavigateToExpenses: () -> Unit,
    onNavigateToRegisterIncomes: () -> Unit,
    onNavigateToIncomes: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onNavigateToRegisterExpenses, modifier = Modifier.padding(bottom = 16.dp)) {
            Text("Ir al Registro de Gastos")
        }
        Button(onClick = onNavigateToExpenses) {
            Text("Ir a la Lista de Gastos")
        }
        Button(onClick = onNavigateToRegisterIncomes, modifier = Modifier.padding(bottom = 16.dp)) {
            Text("Ir al Registro de Ingresos")
        }
        Button(onClick = onNavigateToIncomes) {
            Text("Ir a la Lista de Ingresos")
        }
    }
}
