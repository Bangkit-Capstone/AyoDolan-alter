package com.dicoding.capstone.ayodolan.core.data.source.remote.network

import com.dicoding.capstone.ayodolan.core.data.source.remote.response.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    fun getMoviesRate(
        @Query("api_key") apiKey: String
    ): Call<DataResponse>

    @GET("movie/popular")
    fun getMoviePopular(
        @Query("api_key") apiKey: String
    ): Call<DataResponse>

    @GET("movie/now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") apiKey: String
    ): Call<DataResponse>

    @GET("movie/upcoming")
    fun getMovieUpComing(
        @Query("api_key") apiKey: String
    ): Call<DataResponse>
}