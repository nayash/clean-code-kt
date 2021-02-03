package com.outliers.cleancodekt.framework

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.outliers.cleancodekt.constants.Const

class CCKtParentActivity: Activity() {

    lateinit var appPref: SharedPreferences

    override fun onCreate(onSavedInstanceState: Bundle?){
        super.onCreate(onSavedInstanceState)
        appPref = getSharedPreferences(Const.APP_PREF_NAME, Context.MODE_PRIVATE)
    }
}