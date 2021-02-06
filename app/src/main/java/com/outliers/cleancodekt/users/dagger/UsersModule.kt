package com.outliers.cleancodekt.users.dagger

import com.outliers.cleancodekt.framework.RetrofitClient
import com.outliers.cleancodekt.users.network.UsersClient
import dagger.Module
import dagger.Provides

@Module
class UsersModule {
    @Provides
    fun provideUsersclient(/*@Named("app_retrofit") retrofit: Retrofit*/): UsersClient {
        return RetrofitClient.getClient().create(UsersClient::class.java)
    }
}