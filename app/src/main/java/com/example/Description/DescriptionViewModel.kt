package com.example.Description

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Meal
import com.example.data.MealRepository
import com.example.data.MealResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DescriptionViewModel @Inject constructor(
    private  var mealRepository: MealRepository
):ViewModel() {

    fun saveMeal(meal: Meal){
        viewModelScope.launch {
            mealRepository.saveMeal(meal)
        }
    }

    private val _meal= MutableLiveData<MealResponse>()
    val _mealLive:LiveData<MealResponse>
        get()=_meal

    fun  getMealById(id:String){
        viewModelScope.launch {
            val response=mealRepository.getOneMeal(id);
            _meal.value=response

        }
    }
}