package com.example.api

import com.example.data.Meal
import com.example.data.UserRequest
import com.example.data.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CustomApi {

    @POST("meal/addMeal")
    suspend fun saveMeal(@Body meal: Meal)

    @POST("user/signup")
    suspend fun signUp(@Body userRequest: UserRequest):Response<UserResponse>

    @POST("user/signIn")
    suspend fun signIn(@Body userRequest: UserRequest):Response<UserResponse>
}