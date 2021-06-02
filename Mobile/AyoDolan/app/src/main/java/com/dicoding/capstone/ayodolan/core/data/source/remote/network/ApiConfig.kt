package com.dicoding.capstone.ayodolan.core.data.source.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private fun okhttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    fun getApiService() : ApiService{
        val buildRetrofit = Retrofit.Builder()
            .baseUrl("http://35.225.226.73/wisata/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient())
            .build()
        return buildRetrofit.create(ApiService::class.java)
    }
}