package com.android.sampleporject.model

import com.google.gson.annotations.SerializedName

data class Media(
    @SerializedName("m")
    val mediaLink: String,
)