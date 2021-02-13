package com.outliers.cleancodekt.utils

import android.content.Context
import android.widget.Toast

object Util {
    fun showToast(context: Context, msg: String, length: Int=Toast.LENGTH_SHORT){
        Toast.makeText(context, msg, length).show()
    }
}