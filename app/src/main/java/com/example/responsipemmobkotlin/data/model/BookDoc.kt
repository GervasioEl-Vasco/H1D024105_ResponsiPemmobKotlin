package com.example.responsipemmobkotlin.data.model

import com.google.gson.annotations.SerializedName

data class BookDoc(
    @SerializedName("title")
    val title: String = "",

    @SerializedName("author_name")
    val authorName: List<String>? = null,

    @SerializedName("first_publish_year")
    val firstPublishYear: Int? = null,

    @SerializedName("edition_count")
    val editionCount: Int? = null,

    @SerializedName("language")
    val language: List<String>? = null,

    @SerializedName("key")
    val key: String = ""
) {
    val displayAuthors: String
        get() = authorName?.joinToString(", ") ?: "Tidak diketahui"

    val displayYear: String
        get() = firstPublishYear?.toString() ?: "Tidak diketahui"

    val displayEditions: String
        get() = editionCount?.let { "$it edisi" } ?: "Tidak diketahui"

    val displayLanguages: String
        get() = language?.take(5)?.joinToString(", ")?.uppercase() ?: "Tidak diketahui"
}
