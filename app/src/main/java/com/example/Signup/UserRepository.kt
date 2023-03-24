package com.example.Signup

import com.example.api.CustomApi
import com.example.data.UserRequest
import com.example.data.UserResponse
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject


class UserRepository @Inject constructor(
    val customApi: CustomApi
){

    suspend fun signUpUser(userRequest: UserRequest):Response<UserResponse>{
        return customApi.signUp(userRequest)
    }

    suspend fun signInUser(userRequest: UserRequest):Response<UserResponse>{
        return customApi.signIn(userRequest)
    }
}