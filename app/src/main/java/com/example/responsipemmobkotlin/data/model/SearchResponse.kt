package com.example.responsipemmobkotlin.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("numFound")
    val numFound: Int = 0,

    @SerializedName("docs")
    val docs: List<BookDoc> = emptyList()
)
