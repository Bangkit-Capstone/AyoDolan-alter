package com.dicoding.capstone.ayodolan.core.data.source.remote

import android.util.Log
import com.dicoding.capstone.ayodolan.core.data.source.remote.network.ApiConfig
import com.dicoding.capstone.ayodolan.core.data.source.remote.response.DataResponse
import com.dicoding.capstone.ayodolan.core.data.source.remote.response.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiConfig: ApiConfig){

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiConfig: ApiConfig) : RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(apiConfig).apply { instance = this }
            }

        private const val API_KEY = "5a015c1c464b9ace0f8ab1b8c5c3d328"
        private const val TAG ="Remote Data Source"
    }

    fun getMoviePopular(callback: LoadDataCallback){
        val client = apiConfig.getApiService().getMoviePopular(API_KEY)
        client.enqueue(object : Callback<DataResponse>{
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful){
                    response.body()?.results?.let { callback.onAllDataReceived(it) }
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(TAG,"${t.message}")
            }

        })
    }

    fun getMovieTopRated(callback: LoadDataCallback){
        val client = apiConfig.getApiService().getMoviesRate(API_KEY)
        client.enqueue(object : Callback<DataResponse>{
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful){
                    response.body()?.results?.let { callback.onAllDataReceived(it) }
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(TAG,"${t.message}")
            }

        })
    }



    interface LoadDataCallback{
        fun onAllDataReceived(dataResponse: List<ResultsItem>)
    }
}