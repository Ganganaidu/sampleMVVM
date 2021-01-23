package com.android.sampleporject.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("title")
    val title: String?,
    @SerializedName("link")
    val link: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("modified")
    val modified: String? = null,
    @SerializedName("generator")
    val generator: String? = null,
    @SerializedName("items")
    val items: List<Item>? = null
)