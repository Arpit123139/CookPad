package com.example.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.MealResponse
import com.example.data.MealRepository
import com.example.data.PopularResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor (
    private val repository: MealRepository
): ViewModel() {


    //val img= MutableStateFlow("");

    private var _meal= MutableLiveData<MealResponse>()
    val mealLive: LiveData<MealResponse>
        get()=_meal

    fun getMeal(){
        viewModelScope.launch {
            val mealResponse=repository.getMeal();
            _meal.value=mealResponse


            Log.d("ArpitView",mealResponse.toString())
            Log.d("ArpitView",_meal.value.toString())
        }
    }

    private var _popularmeal=MutableLiveData<PopularResponse>()
    val popularMealLive: LiveData<PopularResponse>
        get()=_popularmeal

    fun getPopularMeal(){
        viewModelScope.launch {
            val popularResponse=repository.getPopularMeal()
            _popularmeal.value=popularResponse

            Log.d("ArpitViewPopularItem",popularResponse.toString())
        }
    }
}