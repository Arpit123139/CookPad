package com.example.data

import com.example.data.Meal
import com.google.gson.annotations.SerializedName
import javax.inject.Singleton

@Singleton
data class MealResponse (
    @SerializedName("meals")
    val meal:List<Meal>
    )