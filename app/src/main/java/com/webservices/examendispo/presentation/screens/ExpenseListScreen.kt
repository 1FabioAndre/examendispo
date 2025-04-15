package com.webservices.examendispo.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope  // Importa esto para usar launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.webservices.examendispo.data.entities.ExpenseEntity
import com.webservices.examendispo.presentation.viewmodel.ExpenseViewModel
import kotlinx.coroutines.launch  // Importa la función launch de corutinas
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material3.ExperimentalMaterial3Api  // Importar para OptIn

@OptIn(ExperimentalMaterial3Api::class)  // Aceptar la API experimental
@Composable
fun ExpenseListScreen(viewModel: ExpenseViewModel) {
    // Contexto para el Toast
    val context = LocalContext.current

    // Lista de gastos
    val expenses = remember { mutableStateListOf<ExpenseEntity>() }

    // Cargar los gastos al principio
    LaunchedEffect(Unit) {
        expenses.addAll(viewModel.getAllExpenses())
    }

    // Usar rememberCoroutineScope para manejar el scope de la corutina
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Gastos") }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                items(expenses) { expense ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Nombre: ${expense.name}")
                                Text("Precio: ${expense.price}")
                                Text("Descripción: ${expense.description}")
                                Text(
                                    "Fecha: ${
                                        SimpleDateFormat(
                                            "dd/MM/yyyy HH:mm",
                                            Locale.getDefault()
                                        ).format(Date(expense.date))
                                    }"
                                )
                            }
                            IconButton(onClick = {
                                // Lanzamos la eliminación dentro del CoroutineScope
                                coroutineScope.launch {
                                    viewModel.deleteExpense(expense)
                                    Toast.makeText(context, "Gasto eliminado", Toast.LENGTH_SHORT)
                                        .show()

                                    // Actualizamos la lista de gastos
                                    expenses.clear()
                                    expenses.addAll(viewModel.getAllExpenses())
                                }
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                            }
                        }
                    }
                }
            }
        }
    )
}
