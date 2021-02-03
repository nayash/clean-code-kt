package com.outliers.cleancodekt.users.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.outliers.cleancodekt.constants.Const
import com.outliers.cleancodekt.users.models.UserModel

class UsersViewModel: ViewModel() {
    val usersLiveData: MutableLiveData<ArrayList<UserModel>> = MutableLiveData()
    val listUsers: ArrayList<UserModel> = ArrayList()

    init {

    }

    fun fetchUsers(pageNum:Int = Const.INIT_PAGE_NUM, pageSize:Int = Const.PAGE_SIZE){

    }
}
