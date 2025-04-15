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
import com.webservices.examendispo.data.entities.IncomeEntity
import com.webservices.examendispo.presentation.viewmodel.IncomeViewModel
import kotlinx.coroutines.launch  // Importa la función launch de corutinas
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material3.ExperimentalMaterial3Api  // Importar para OptIn

// Aceptar la API experimental
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeListScreen(viewModel: IncomeViewModel) {
    // Contexto para el Toast
    val context = LocalContext.current

    // Lista de gastos
    val incomes = remember { mutableStateListOf<IncomeEntity>() }

    // Cargar los gastos al principio
    LaunchedEffect(Unit) {
        incomes.addAll(viewModel.getAllIncomes())
    }

    // Usar rememberCoroutineScope para manejar el scope de la corutina
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Ingresos") }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                items(incomes) { income ->
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
                                Text("Nombre: ${income.name}")
                                Text("Precio: ${income.price}")
                                Text("Descripción: ${income.description}")
                                Text(
                                    "Fecha: ${
                                        SimpleDateFormat(
                                            "dd/MM/yyyy HH:mm",
                                            Locale.getDefault()
                                        ).format(Date(income.date))
                                    }"
                                )
                            }
                            IconButton(onClick = {
                                // Lanzamos la eliminación dentro del CoroutineScope
                                coroutineScope.launch {
                                    viewModel.deleteIncome(income)
                                    Toast.makeText(context, "Ingreso eliminado", Toast.LENGTH_SHORT)
                                        .show()

                                    // Actualizamos la lista de gastos
                                    incomes.clear()
                                    incomes.addAll(viewModel.getAllIncomes())
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
