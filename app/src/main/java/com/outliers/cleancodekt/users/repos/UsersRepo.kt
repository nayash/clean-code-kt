package com.outliers.cleancodekt.users.repos

import com.outliers.cleancodekt.users.models.UserModel
import com.outliers.cleancodekt.users.network.UsersClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class UsersRepo @Inject constructor(var usersClient: UsersClient) {

    suspend fun fetchUsers(page: Int, limit: Int): Response<List<UserModel>> {
        return withContext(Dispatchers.IO) {
            usersClient.getUsers(page, limit)
        }
    }
}