package com.example.responsipemmobkotlin.data.remote

import com.example.responsipemmobkotlin.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApi {
    @GET("/search.json")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("limit") limit: Int = 20
    ): SearchResponse
}
