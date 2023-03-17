package com.example.Category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.MealRepository
import com.example.data.MealResponse
import com.example.data.PopularResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Category_Meal_ViewModel @Inject constructor(
    private  var repository: MealRepository
):ViewModel(){

    private var _itemLive= MutableLiveData<PopularResponse>()
    val itemLive:LiveData<PopularResponse>
            get()=_itemLive

    private var _itemOne= MutableLiveData<MealResponse>()
    val itemOne:LiveData<MealResponse>
        get()=_itemOne

    fun getMealByCategory(c:String){

        viewModelScope.launch {
            val response=repository.getMealByCategory(c)
            _itemLive.value=response
        }

    }

    fun getOneMeal(i:String){

        viewModelScope.launch {
            val response1=repository.getOneMeal(i)
            _itemOne.value=response1
        }

    }

}