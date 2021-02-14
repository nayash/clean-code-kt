package com.outliers.cleancodekt.userprofile.network

import com.outliers.cleancodekt.userprofile.albums.models.AlbumModel
import com.outliers.cleancodekt.userprofile.posts.models.PostModel
import com.outliers.cleancodekt.userprofile.todos.models.TodoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserProfileClient {

    @GET("/users/{id}/posts")
    suspend fun getPosts(@Path("id") id: String,
                         @Query("_page") pageNum: Int,
                         @Query("_limit") limit: Int): Response<List<PostModel>>

    @GET("/users/{id}/albums")
    suspend fun getAlbums(@Path("id") id: String,
                         @Query("_page") pageNum: Int,
                         @Query("_limit") limit: Int): Response<List<AlbumModel>>

    @GET("/users/{id}/todos")
    suspend fun getTodos(@Path("id") id: String,
                         @Query("_page") pageNum: Int,
                         @Query("_limit") limit: Int): Response<List<TodoModel>>
}