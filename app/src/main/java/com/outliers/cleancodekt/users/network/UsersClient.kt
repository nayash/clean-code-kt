package com.outliers.cleancodekt.users.network

import com.outliers.cleancodekt.users.models.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersClient {

    @GET("/users")
    suspend fun getUsers(@Query("_page") page: Int, @Query("_limit") limit: Int): Response<List<UserModel>>
}