package com.example.Favourite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.FavouriteMealResponse
import com.example.data.Meal
import com.example.data.MealRepository
import com.example.data.MealSaveResponse
import com.example.utils.Network
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private var repository: MealRepository
) :ViewModel() {

    private var _meal=MutableLiveData<Network<FavouriteMealResponse>>()
    val _mealLive:LiveData<Network<FavouriteMealResponse>>
        get()=_meal

//    private var _mealList=MutableLiveData<FavouriteMealResponse>()
//    val _mealLiveList:LiveData<FavouriteMealResponse>
//        get()=_mealList

    fun getAllMeal(){
        viewModelScope.launch {
            var response=repository.getAllMeal()
            handle(response)
        }
    }

   fun deleteMeal(id:String){
//        _mealList.value!!.meal=_meal.value!!.data!!.meal.filter {
//            it.id!=id
//        }

        viewModelScope.launch {

            repository.deleteMeal(id)
        }
    }

    fun saveMeal(meal: Meal){
        viewModelScope.launch {
            val response= repository.saveMeal(meal)
        }
    }


    private fun handle(response: Response<FavouriteMealResponse>) {

        if (response.isSuccessful && response.body() != null) {
            _meal.value = Network.Success(response.body()!!)
            Log.d("Favourite ViewModel", response.body().toString())
        } else if (response.errorBody() != null) {        //ERROR BODY HAS A JSON
            //parsing the JSON
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _meal.value = Network.Error(errorObj.getString("message"))
        } else {
            _meal.value = Network.Error("Something Went Wrong")
        }
    }

}