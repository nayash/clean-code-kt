package com.outliers.cleancodekt.framework.dagger

import com.outliers.cleancodekt.framework.RetrofitClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit{
        return RetrofitClient.getClient()
    }
}