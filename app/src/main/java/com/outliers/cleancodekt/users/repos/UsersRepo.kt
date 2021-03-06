package com.outliers.cleancodekt.users.repos

import android.util.Log
import com.outliers.cleancodekt.framework.dagger.ActivityScope
import com.outliers.cleancodekt.users.models.UserModel
import com.outliers.cleancodekt.users.network.UsersClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

@ActivityScope
class UsersRepo @Inject constructor(var usersClient: UsersClient) {

    suspend fun fetchUsers(page: Int, limit: Int): Response<List<UserModel>> {
        return withContext(Dispatchers.IO) {
            Log.v("test-fetchUsers", page.toString())
            usersClient.getUsers(page, limit)
        }
    }
}