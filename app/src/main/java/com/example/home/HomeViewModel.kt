package com.example.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.CategoryResponse
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

    private var _popularmeal=MutableLiveData<PopularResponse>()
    val popularMealLive: LiveData<PopularResponse>
        get()=_popularmeal

    private var _onemeal=MutableLiveData<MealResponse>()
    val oneLiveData:LiveData<MealResponse>
        get()=_onemeal

    private var _categoryList=MutableLiveData<CategoryResponse>()
    val categoryListLive:LiveData<CategoryResponse>
        get() = _categoryList

    fun getMeal(){
        viewModelScope.launch {
            val mealResponse=repository.getMeal();
            _meal.value=mealResponse


            Log.d("ArpitView",mealResponse.toString())
            Log.d("ArpitView",_meal.value.toString())
        }
    }



    fun getPopularMeal(){
        viewModelScope.launch {
            val popularResponse=repository.getPopularMeal()
            _popularmeal.value=popularResponse

            Log.d("ArpitViewPopularItem",popularResponse.toString())
        }
    }

    fun getOneMeal(id: String){
    viewModelScope.launch {
        val response=repository.getOneMeal(id)
        Log.d("OneMealViewModel", "getOneMeal:${response} ")
        _onemeal.value=response
    }
    }


    fun getCategoryList(){

        viewModelScope.launch {
            val response=repository.getCategoryList()
            _categoryList.value=response
            Log.d("CATEGORY LIST", response.toString())
        }
    }
}