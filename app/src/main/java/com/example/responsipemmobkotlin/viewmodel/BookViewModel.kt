package com.example.responsipemmobkotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsipemmobkotlin.data.model.BookDoc
import com.example.responsipemmobkotlin.data.repository.BookRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

@OptIn(FlowPreview::class)
class BookViewModel : ViewModel() {

    private val repository = BookRepository()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<List<BookDoc>>>(UiState.Idle)
    val uiState: StateFlow<UiState<List<BookDoc>>> = _uiState.asStateFlow()

    private val _selectedBook = MutableStateFlow<BookDoc?>(null)
    val selectedBook: StateFlow<BookDoc?> = _selectedBook.asStateFlow()

    init {
        _searchQuery
            .debounce(500L)
            .distinctUntilChanged()
            .filter { it.isNotBlank() }
            .onEach { query -> searchBooks(query) }
            .launchIn(viewModelScope)
    }

    fun onQueryChange(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            _uiState.value = UiState.Idle
        }
    }

    fun selectBook(book: BookDoc) {
        _selectedBook.value = book
    }

    private fun searchBooks(query: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = repository.searchBooks(query)
            _uiState.value = result.fold(
                onSuccess = { books ->
                    if (books.isEmpty()) UiState.Error("Buku tidak ditemukan")
                    else UiState.Success(books)
                },
                onFailure = { error ->
                    UiState.Error("Gagal mengambil data, cek koneksi internet")
                }
            )
        }
    }
}
