package com.outliers.cleancodekt.userprofile.viewmodels

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.outliers.cleancodekt.constants.Const
import com.outliers.cleancodekt.framework.ApiCallState
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

    var postsFragApiCallState: MutableLiveData<ApiCallState> = MutableLiveData()
    var albumsFragApiCallState: MutableLiveData<ApiCallState> = MutableLiveData()
    var todosFragApiCallState: MutableLiveData<ApiCallState> = MutableLiveData()

    init {
        postsFragApiCallState.value = ApiCallState.INITIAL
        albumsFragApiCallState.value = ApiCallState.INITIAL
        todosFragApiCallState.value = ApiCallState.INITIAL
    }

    fun fetchPosts(pageNum: Int = Const.INIT_PAGE_NUM, pageSize: Int = Const.PAGE_SIZE) {
        if (postsLiveData.value == null)
            postsLiveData.value = ArrayList()
        viewModelScope.launch {
            postsFragApiCallState.value = ApiCallState.LOADING
            val response = userModel.id?.let { userProfileRepo.getUserPosts(it, pageNum, pageSize) }
            if (response?.isSuccessful == true) {
                Log.d("test-RespPosts", response?.body().toString())
                postsLiveData.value =
                    postsLiveData.value?.apply { addAll(response?.body() as ArrayList) }
                isLastPost.value = response?.body()?.size == 0
                postsFragApiCallState.value = ApiCallState.FINISHED
            } else {

            }
        }
    }

    fun fetchAlbums(pageNum: Int = Const.INIT_PAGE_NUM, pageSize: Int = Const.PAGE_SIZE) {
        if (albumsLiveData.value == null)
            albumsLiveData.value = ArrayList()
        viewModelScope.launch {
            albumsFragApiCallState.value = ApiCallState.LOADING
            val response = userModel.id?.let { userProfileRepo.getUserAlbums(it, pageNum, pageSize) }
            if (response?.isSuccessful == true) {
                Log.d("test-RespAlbums", response?.body().toString())
                albumsLiveData.value =
                    albumsLiveData.value?.apply { addAll(response.body() as ArrayList) }
                isLastAlbum.value = response?.body()?.size == 0
                albumsFragApiCallState.value = ApiCallState.FINISHED
            } else {

            }
        }
    }

    fun fetchTodos(pageNum: Int = Const.INIT_PAGE_NUM, pageSize: Int = Const.PAGE_SIZE) {
        if (todosLiveData.value == null)
            todosLiveData.value = ArrayList()
        viewModelScope.launch {
            todosFragApiCallState.value = ApiCallState.LOADING
            val response = userModel.id?.let { userProfileRepo.getUserTodos(it, pageNum, pageSize) }
            if (response?.isSuccessful == true) {
                Log.d("test-RespTodo", response?.body().toString())
                todosLiveData.value =
                    todosLiveData.value?.apply { addAll(response.body() as ArrayList) }
                isLastTodo.value = response?.body()?.size == 0
                todosFragApiCallState.value = ApiCallState.FINISHED
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