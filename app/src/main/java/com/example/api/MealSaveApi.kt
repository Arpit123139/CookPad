package com.example.api

import com.example.data.Meal
import com.example.data.MealSaveResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MealSaveApi {

    @POST("meal/addMeal")
    suspend fun saveMeal(@Body meal: Meal):Response<MealSaveResponse>

}