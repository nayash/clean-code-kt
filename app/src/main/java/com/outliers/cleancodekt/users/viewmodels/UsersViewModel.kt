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
    val isLastPage: MutableLiveData<Boolean> = MutableLiveData()

    init{
        isLastPage.value = false
    }

    fun fetchUsers(pageNum:Int = Const.INIT_PAGE_NUM, pageSize:Int = Const.PAGE_SIZE){
        // this is called on main thread. repo functions use withContext with dispatchers to execute on IO threads
        viewModelScope.launch {
            val resp: Response<List<UserModel>> = usersRepo.fetchUsers(pageNum, pageSize)
            if(resp.isSuccessful) {
                resp.body()?.let { listUsers.addAll(it)
                    if(it.size == 0){
                        isLastPage.value = true
                    }
                }
                usersLiveData.value = listUsers
            }
        }
    }

    fun refresh(){
        listUsers.clear()
        usersLiveData.value = listUsers
        isLastPage.value = false
        fetchUsers()
    }
}
