package com.outliers.cleancodekt.userprofile.viewmodels

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.outliers.cleancodekt.constants.Const
import com.outliers.cleancodekt.userprofile.albums.models.AlbumModel
import com.outliers.cleancodekt.userprofile.network.UserProfileRepo
import com.outliers.cleancodekt.userprofile.posts.models.PostModel
import com.outliers.cleancodekt.userprofile.todos.models.TodoModel
import com.outliers.cleancodekt.users.models.UserModel
import kotlinx.coroutines.launch

class UserProfileViewModel(val userModel: UserModel, val userProfileRepo: UserProfileRepo) :
    ViewModel() {

    val currFragLiveData: MutableLiveData<Fragment> = MutableLiveData()

    val postsLiveData: MutableLiveData<ArrayList<PostModel>> = MutableLiveData()
    val albumsLiveData: MutableLiveData<ArrayList<AlbumModel>> = MutableLiveData()
    val todosLiveData: MutableLiveData<ArrayList<TodoModel>> = MutableLiveData()

    val isLastPost = MutableLiveData<Boolean>()
    val isLastAlbum = MutableLiveData<Boolean>()
    val isLastTodo = MutableLiveData<Boolean>()

    init {
        todosLiveData.value = ArrayList()
    }

    fun fetchPosts(pageNum: Int = Const.INIT_PAGE_NUM, pageSize: Int = Const.PAGE_SIZE) {
        if (postsLiveData.value == null)
            postsLiveData.value = ArrayList()
        viewModelScope.launch {
            val response = userModel.id?.let { userProfileRepo.getUserPosts(it, pageNum, pageSize) }
            if (response?.isSuccessful == true) {
                if (response?.body()?.size == 0) {
                    isLastPost.value = true
                    return@launch
                }
                Log.d("test-vmResp", response?.body().toString())
                postsLiveData.value =
                    postsLiveData.value?.apply { addAll(response?.body() as ArrayList) }
            } else {

            }
        }
    }

    fun fetchAlbums(pageNum: Int = Const.INIT_PAGE_NUM, pageSize: Int = Const.PAGE_SIZE) {
        if (albumsLiveData.value == null)
            albumsLiveData.value = ArrayList()
        viewModelScope.launch {
            val response = userModel.id?.let { userProfileRepo.getUserAlbums(it, pageNum, pageSize) }
            if (response?.isSuccessful == true) {
                if (response?.body()?.size == 0) {
                    isLastAlbum.value = true
                    return@launch
                }
                Log.d("test-vmResp", response?.body().toString())
                albumsLiveData.value =
                    albumsLiveData.value?.apply { addAll(response.body() as ArrayList) }
            } else {

            }
        }
    }

    fun fetchTodos(pageNum: Int = Const.INIT_PAGE_NUM, pageSize: Int = Const.PAGE_SIZE) {
        if (albumsLiveData.value == null)
            albumsLiveData.value = ArrayList()
        viewModelScope.launch {
            val response = userModel.id?.let { userProfileRepo.getUserTodos(it, pageNum, pageSize) }
            if (response?.isSuccessful == true) {
                if (response?.body()?.size == 0) {
                    isLastTodo.value = true
                    return@launch
                }
                Log.d("test-vmResp", response?.body().toString())
                todosLiveData.value =
                    todosLiveData.value?.apply { addAll(response.body() as ArrayList) }
            } else {

            }
        }
    }

    fun refreshPosts() {
        postsLiveData.value?.clear()
        isLastPost.value = false
        fetchPosts()
    }

    fun refreshAlbums() {
        albumsLiveData.value?.clear()
        isLastAlbum.value = false
        fetchAlbums()
    }

    fun refreshTodos() {
        todosLiveData.value?.clear()
        isLastTodo.value = false
        fetchTodos()
    }
}