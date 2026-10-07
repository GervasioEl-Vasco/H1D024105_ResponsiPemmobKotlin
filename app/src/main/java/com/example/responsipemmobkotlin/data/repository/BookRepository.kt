package com.example.responsipemmobkotlin.data.repository

import com.example.responsipemmobkotlin.data.model.BookDoc
import com.example.responsipemmobkotlin.data.remote.RetrofitInstance

class BookRepository {
    private val api = RetrofitInstance.api

    suspend fun searchBooks(query: String): Result<List<BookDoc>> {
        return try {
            val response = api.searchBooks(query = query, limit = 20)
            Result.success(response.docs)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
