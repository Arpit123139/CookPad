package com.example.Signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.UserRequest
import com.example.data.UserResponse
import com.example.utils.Network
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    val userRepository: UserRepository
):ViewModel() {

    var _userResponse=MutableLiveData<Network<UserResponse>>()
    val user_res:LiveData<Network<UserResponse>>
        get()= _userResponse

    var _userResponse1=MutableLiveData<Network<UserResponse>>()
    val user_res1:LiveData<Network<UserResponse>>
        get()= _userResponse1

    fun signUp(userRequest: UserRequest){
       viewModelScope.launch {

           _userResponse.value=Network.Loading()

           val response=userRepository.signUpUser(userRequest)

           handleResponse(response)
       }
    }

    fun signIn(userRequest: UserRequest){
        viewModelScope.launch {

            _userResponse.value=Network.Loading()

            val response=userRepository.signInUser(userRequest)
            handleResponse(response)
        }
    }

    fun handleResponse(userResponse: Response<UserResponse>){

        if (userResponse.isSuccessful && userResponse.body() != null) {
            _userResponse.value = Network.Success(userResponse.body()!!)
        } else if (userResponse.errorBody() != null) {        //ERROR BODY HAS A JSON
            //parsing the JSON
            val errorObj = JSONObject(userResponse.errorBody()!!.charStream().readText())
            _userResponse.value = Network.Error(errorObj.getString("message"))
        } else {
            _userResponse.value = Network.Error("Something Went Wrong")
        }
    }
}