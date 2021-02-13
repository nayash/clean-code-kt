package com.outliers.cleancodekt.userprofile.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.outliers.cleancodekt.constants.Const
import com.outliers.cleancodekt.userprofile.network.UserProfileRepo
import com.outliers.cleancodekt.userprofile.posts.models.PostModel
import com.outliers.cleancodekt.users.models.UserModel
import kotlinx.coroutines.launch

class UserProfileViewModel(val userModel: UserModel, val userProfileRepo: UserProfileRepo): ViewModel() {

    val currFragLiveData: MutableLiveData<Fragment> = MutableLiveData()

    val postsLiveData: MutableLiveData<ArrayList<PostModel>> = MutableLiveData()
    val albumsLiveData: MutableLiveData<ArrayList<PostModel>> = MutableLiveData()
    val todosLiveData: MutableLiveData<ArrayList<PostModel>> = MutableLiveData()

    val isLastPost = MutableLiveData<Boolean>()
    val isLastAlbum = MutableLiveData<Boolean>()
    val isLastTodo = MutableLiveData<Boolean>()

    init{
        postsLiveData.value = ArrayList()
        albumsLiveData.value = ArrayList()
        todosLiveData.value = ArrayList()
    }

    fun fetchPosts(pageNum: Int=Const.INIT_PAGE_NUM, pageSize: Int=Const.PAGE_SIZE){
        viewModelScope.launch {
            val response = userModel.id?.let { userProfileRepo.getUserPosts(it, pageNum, pageSize) }
            if(response?.isSuccessful == true){
                postsLiveData.value?.addAll(response?.body() as ArrayList)
            }else{

            }
        }
    }

    fun refreshPosts(){
        postsLiveData.value?.clear()
        fetchPosts()
        isLastPost.value = false
    }
}