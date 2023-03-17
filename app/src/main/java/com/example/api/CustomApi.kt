package com.example.api

import com.example.data.Meal
import retrofit2.http.Body
import retrofit2.http.POST

interface CustomApi {

    @POST("meal/addMeal")
    suspend fun saveMeal(@Body meal: Meal)
}