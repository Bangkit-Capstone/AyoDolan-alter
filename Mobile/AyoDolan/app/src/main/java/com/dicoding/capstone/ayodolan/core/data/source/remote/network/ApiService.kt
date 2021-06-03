package com.dicoding.capstone.ayodolan.core.data.source.remote.network

import com.dicoding.capstone.ayodolan.core.data.source.remote.response.DataResponse
import com.dicoding.capstone.ayodolan.core.data.source.remote.response.DataResponse2
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("category/1")
    fun getPantai(
    ): Call<DataResponse>

    @GET("category/2")
    fun getGunung(
    ): Call<DataResponse>

    @GET("category/3")
    fun getKebun(
    ): Call<DataResponse>

    @GET("category/4")
    fun getCagarBudaya(
    ): Call<DataResponse>
}