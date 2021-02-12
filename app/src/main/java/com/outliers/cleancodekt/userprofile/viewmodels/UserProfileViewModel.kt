package com.outliers.cleancodekt.userprofile.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserProfileViewModel(): ViewModel() {

    val currFragLiveData: MutableLiveData<Fragment> = MutableLiveData()

    fun getFragmentByPosition(position: Int){

    }
}