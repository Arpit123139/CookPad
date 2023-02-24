package com.example.data

import android.util.Log
import com.example.api.MealApi
import javax.inject.Inject


class MealRepository @Inject constructor(
    val meal:MealApi
){
    suspend fun getMeal(): MealResponse {
        Log.d("ArpitRepo ",meal.getDish().meal.toString())
        return meal.getDish();

    }

    suspend fun getPopularMeal():PopularResponse{
        Log.d("ArpitRepo ",meal.getPopularItem("Seafood").meals.toString())
        return meal.getPopularItem("Seafood")
    }
}