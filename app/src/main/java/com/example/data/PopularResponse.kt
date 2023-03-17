package com.example.data

import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("meals")
    val meals: List<PopularItem>
)
