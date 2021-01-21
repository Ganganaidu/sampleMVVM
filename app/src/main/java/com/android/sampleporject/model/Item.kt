package com.android.sampleporject.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("media")
    val media: Media,
    @SerializedName("date_taken")
    val dateTaken: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("published")
    val published: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("author_id")
    val authorId: String,
    @SerializedName("tags")
    val tags: String
)