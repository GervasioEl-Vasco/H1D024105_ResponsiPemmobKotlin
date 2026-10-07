package com.example.responsipemmobkotlin.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.responsipemmobkotlin.data.model.BookDoc
import com.example.responsipemmobkotlin.ui.components.BookItem
import com.example.responsipemmobkotlin.viewmodel.BookViewModel
import com.example.responsipemmobkotlin.viewmodel.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onBookClick: (BookDoc) -> Unit,
    viewModel: BookViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Book Search",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.onQueryChange(it) },
                    label = { Text("Cari buku") },
                    placeholder = { Text("contoh: kotlin, java, indonesia") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }

            when (val state = uiState) {
                is UiState.Idle -> {
                    item {
                        InfoText(
                            message = "Masukkan kata kunci untuk mencari buku",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                is UiState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                is UiState.Success -> {
                    items(
                        items = state.data,
                        key = { book -> book.key }
                    ) { book ->
                        BookItem(
                            book = book,
                            onClick = onBookClick
                        )
                    }
                }

                is UiState.Error -> {
                    item {
                        InfoText(
                            message = state.message,
                            isError = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoText(
    message: String,
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(vertical = 48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isError) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
