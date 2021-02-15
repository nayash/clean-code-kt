package com.outliers.cleancodekt.framework

import androidx.lifecycle.MutableLiveData

interface AppViewModel {
    val apiCallStateLiveData: MutableLiveData<ApiCallState>
}