package com.outliers.cleancodekt.users.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.outliers.cleancodekt.constants.Const
import com.outliers.cleancodekt.users.models.UserModel
import com.outliers.cleancodekt.users.repos.UsersRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class UsersViewModel(val usersRepo: UsersRepo):ViewModel() {

    val usersLiveData: MutableLiveData<List<UserModel>> = MutableLiveData()
    val listUsers: MutableList<UserModel> = ArrayList()

    fun fetchUsers(pageNum:Int = Const.INIT_PAGE_NUM, pageSize:Int = Const.PAGE_SIZE){
        viewModelScope.launch {
            val resp: Response<List<UserModel>> = usersRepo.fetchUsers(pageNum, pageSize)
            if(resp.isSuccessful) {
                resp.body()?.let { listUsers.addAll(it) }
                usersLiveData.value = listUsers
            }
        }
    }
}
