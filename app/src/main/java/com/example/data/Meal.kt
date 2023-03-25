package com.example.data

import com.google.gson.annotations.SerializedName

data class Meal (


    @SerializedName("strMeal")
    val name:String,

    @SerializedName("strArea")
    val area:String,

    @SerializedName("strCategory")
    val category:String,

    @SerializedName("strInstructions")
    val description:String,

    @SerializedName("strYoutube")
    val yt:String,

    @SerializedName("strMealThumb")
    val image:String,

    @SerializedName("idMeal")
    val id:String,

    )