package com.outliers.cleancodekt.users.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.outliers.cleancodekt.constants.Const
import com.outliers.cleancodekt.framework.ApiCallState
import com.outliers.cleancodekt.framework.AppViewModel
import com.outliers.cleancodekt.users.models.UserModel
import com.outliers.cleancodekt.users.repos.UsersRepo
import kotlinx.coroutines.launch
import retrofit2.Response

class UsersViewModel(val usersRepo: UsersRepo):ViewModel(), AppViewModel {

    val usersLiveData: MutableLiveData<List<UserModel>> = MutableLiveData()
    val listUsers: MutableList<UserModel> = ArrayList()
    val isLastPage: MutableLiveData<Boolean> = MutableLiveData()
    override val apiCallStateLiveData: MutableLiveData<ApiCallState> = MutableLiveData()

    init{
        isLastPage.value = false
        apiCallStateLiveData.value = ApiCallState.INITIAL
    }

    fun fetchUsers(pageNum:Int = Const.INIT_PAGE_NUM, pageSize:Int = Const.PAGE_SIZE){
        // this is called on main thread. repo functions use withContext with dispatchers to execute on IO threads
        viewModelScope.launch {
            apiCallStateLiveData.value = ApiCallState.LOADING
            val resp: Response<List<UserModel>> = usersRepo.fetchUsers(pageNum, pageSize)
            if(resp.isSuccessful) {
                resp.body()?.let { listUsers.addAll(it)
                    println("users fetched:"+it.size)
                    if(it.size == 0){
                        isLastPage.value = true
                    }
                }
                usersLiveData.value = listUsers
            }
            apiCallStateLiveData.value = ApiCallState.FINISHED
        }
    }

    fun refresh(){
        listUsers.clear()
        usersLiveData.value = listUsers
        isLastPage.value = false
        fetchUsers()
    }
}
