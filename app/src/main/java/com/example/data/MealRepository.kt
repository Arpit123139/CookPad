package com.example.data

import android.util.Log
import com.example.api.CustomApi
import com.example.api.MealApi
import com.example.api.MealSaveApi
import retrofit2.Response
import javax.inject.Inject


class MealRepository @Inject constructor(
    val meal:MealApi,
    val customApi: CustomApi,
    val mealSaveApi: MealSaveApi
){
    suspend fun getMeal(): MealResponse {
      //  Log.d("ArpitRepo ",meal.getDish().meal.toString())
        return meal.getDish();

    }

    suspend fun getPopularMeal():PopularResponse{
        Log.d("ArpitRepo ",meal.getPopularItem("Seafood").meals.toString())
        return meal.getPopularItem("Seafood")
    }

    suspend fun getOneMeal(id:String):MealResponse{
        return meal.getOneItem(id)
    }



    suspend fun saveMeal(meal: Meal):Response<MealSaveResponse>{
        return mealSaveApi.saveMeal(meal)
    }

    suspend fun getAllMeal():Response<FavouriteMealResponse>{
        return mealSaveApi.getAllMeal()
    }

    suspend fun deleteMeal(id:String){
        return mealSaveApi.deleteMeal(id)
    }

    suspend fun getCategoryList():CategoryResponse{
       return  meal.getCategoryList()
    }

    suspend fun getMealByCategory(category: String):PopularResponse{
        return meal.getMealByCategory(category)
    }

}