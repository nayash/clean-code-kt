package com.outliers.cleancodekt.framework

import com.outliers.cleancodekt.constants.Const
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private lateinit var retrofit: Retrofit

    fun getClient(): Retrofit{
        val httpLoginInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val errorInterceptor: Interceptor = Interceptor(){ chain: Interceptor.Chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            ErrorHandler.handleError(response)
            response
        }

        val okhttp3Client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoginInterceptor)
            .addInterceptor(errorInterceptor)
            .addNetworkInterceptor(Interceptor(){chain: Interceptor.Chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                requestBuilder.addHeader("header-key", "header-val")
                chain.proceed(requestBuilder.build())
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttp3Client)
            .build()
        return retrofit
    }
}