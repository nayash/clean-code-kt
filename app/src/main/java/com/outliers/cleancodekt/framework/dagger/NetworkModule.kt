package com.outliers.cleancodekt.framework.dagger

import com.outliers.cleancodekt.framework.RetrofitClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    // @Named("app_retrofit")
    fun provideRetrofit(): Retrofit{
        return RetrofitClient.getClient()
    }
}