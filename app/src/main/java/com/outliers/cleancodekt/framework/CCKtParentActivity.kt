package com.outliers.cleancodekt.framework

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.outliers.cleancodekt.constants.Const

open class CCKtParentActivity: AppCompatActivity() {

    lateinit var appPref: SharedPreferences

    override fun onCreate(onSavedInstanceState: Bundle?){
        super.onCreate(onSavedInstanceState)
        appPref = getSharedPreferences(Const.APP_PREF_NAME, Context.MODE_PRIVATE)
    }
}