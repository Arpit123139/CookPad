package com.example.api

import com.example.data.FavouriteMealResponse
import com.example.data.Meal
import com.example.data.MealSaveResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MealSaveApi {

    @POST("meal/addMeal")
    suspend fun saveMeal(@Body meal: Meal):Response<MealSaveResponse>

    @GET("meal/getAllMeal")
    suspend fun getAllMeal():Response<FavouriteMealResponse>

    @DELETE("meal/deleteMeals/{id}")
    suspend fun deleteMeal(@Path("id")id:String)

}