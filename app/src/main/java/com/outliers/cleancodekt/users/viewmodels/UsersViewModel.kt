package com.outliers.cleancodekt.users.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.outliers.cleancodekt.users.models.UserModel

class UsersViewModel: ViewModel() {
    val usersLiveData: MutableLiveData<ArrayList<UserModel>> = MutableLiveData()
    init {

    }
}
