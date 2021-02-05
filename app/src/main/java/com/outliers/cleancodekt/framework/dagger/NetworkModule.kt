package com.outliers.cleancodekt.framework.dagger

import com.outliers.cleancodekt.framework.RetrofitClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NetworkModule {
    @Provides
    // @Named("app_retrofit")
    fun provideRetrofit(): Retrofit{
        return RetrofitClient.getClient()
    }
}