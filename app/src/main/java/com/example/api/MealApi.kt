package com.example.api

import com.example.data.CategoryResponse
import com.example.data.MealResponse
import com.example.data.PopularItem
import com.example.data.PopularResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    suspend fun getDish(): MealResponse
   // suspend fun getDish(@Url url:String):MealResponse

    @GET("filter.php?")
    suspend fun getPopularItem(@Query("c")categoryName:String): PopularResponse

    @GET("lookup.php?")
    suspend fun getOneItem(@Query("i")id:String):MealResponse

    @GET("categories.php")
    suspend fun getCategoryList():CategoryResponse

    @GET("filter.php?")
    suspend fun getMealByCategory(@Query("c")categoryName:String): PopularResponse

}