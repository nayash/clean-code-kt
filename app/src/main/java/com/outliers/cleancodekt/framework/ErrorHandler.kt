package com.outliers.cleancodekt.framework

import android.util.Log
import okhttp3.Response

object ErrorHandler {
    fun handleError(response: Response){
        when {
            response.code == 500 -> {
                Log.e("errorIntercept", "500--")
            }
            response.code == 404 -> {
                Log.e("errorIntercept", "404")
            }
            response.code == 400 -> {
                Log.e("errorIntercept", "400")
            }
        }
        Log.e("response", response.message)
    }
}