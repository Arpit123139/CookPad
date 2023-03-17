package com.example.data

import com.google.gson.annotations.SerializedName

data class Meal (


    @SerializedName("strMealThumb")
    val image:String,

    @SerializedName("strMeal")
    val name:String,

    @SerializedName("strInstructions")
    val description:String,

    @SerializedName("strCategory")
    val category:String,

    @SerializedName("strArea")
    val area:String,

    @SerializedName("strYoutube")
    val yt:String,

    @SerializedName("idMeal")
    val id:String

    )