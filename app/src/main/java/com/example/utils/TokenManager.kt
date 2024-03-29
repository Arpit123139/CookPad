package com.example.utils

import android.content.Context
import com.example.utils.constants.Companion.SharePreferene
import com.example.utils.constants.Companion.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    var prefs=context.getSharedPreferences( SharePreferene, Context.MODE_PRIVATE)

    fun saveToken(token:String){
        val editor=prefs.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }

    fun getToken():String?{
        return prefs.getString(USER_TOKEN,null)
    }
}