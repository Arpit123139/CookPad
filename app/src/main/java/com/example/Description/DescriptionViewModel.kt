package com.example.Description

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Meal
import com.example.data.MealRepository
import com.example.data.MealResponse
import com.example.data.MealSaveResponse
import com.example.utils.Network
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DescriptionViewModel @Inject constructor(
    private  var mealRepository: MealRepository
):ViewModel() {


    private val _meal= MutableLiveData<MealResponse>()
    val _mealLive:LiveData<MealResponse>
        get()=_meal

    private val _mealSave= MutableLiveData<Network<MealSaveResponse>>()
    val _mealSaveLive:LiveData<Network<MealSaveResponse>>
        get()=_mealSave


    fun saveMeal(meal: Meal){
        _mealSave.value=Network.Loading()
        Log.d("DescriptionViewModel", "Hello View Model ")
        viewModelScope.launch {
           val response= mealRepository.saveMeal(meal)
            handle(response)
        }
    }

    private fun handle(response: Response<MealSaveResponse>) {

        if (response.isSuccessful && response.body() != null) {
            _mealSave.value = Network.Success(response.body()!!)
        } else if (response.errorBody() != null) {        //ERROR BODY HAS A JSON
            //parsing the JSON
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _mealSave.value = Network.Error(errorObj.getString("message"))
        } else {
            _mealSave.value = Network.Error("Something Went Wrong")
        }
    }

    fun  getMealById(id:String){
        viewModelScope.launch {
            val response=mealRepository.getOneMeal(id);
            _meal.value=response

        }
    }
}