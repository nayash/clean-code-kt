package com.outliers.cleancodekt.userprofile.network

import com.outliers.cleancodekt.userprofile.posts.models.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class UserProfileRepo @Inject constructor(retrofit: Retrofit) {
    val userProfileClient: UserProfileClient = retrofit.create(UserProfileClient::class.java)
    suspend fun getUserPosts(userId: String, page: Int, limit: Int): Response<List<PostModel>>{
        return withContext(Dispatchers.IO) {
            userProfileClient.getPosts(userId, page, limit)
        }
    }
}