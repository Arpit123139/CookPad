package com.example.data

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("_id")
    val userId: String,
    val email: String,
    val password: String
)