package com.dicoding.capstone.ayodolan.core.data.source.remote

import android.util.Log
import com.dicoding.capstone.ayodolan.core.data.source.remote.network.ApiConfig
import com.dicoding.capstone.ayodolan.core.data.source.remote.response.DataResponse
import com.dicoding.capstone.ayodolan.core.data.source.remote.response.DataResponse2
import com.dicoding.capstone.ayodolan.core.data.source.remote.response.ResultsItem
import com.dicoding.capstone.ayodolan.core.data.source.remote.response.WisataItem
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
        private const val TAG ="Remote Data Source"
    }

    fun getPantai(callback: LoadDataCallback){
        val client = apiConfig.getApiService().getPantai()
        client.enqueue(object : Callback<DataResponse>{
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful){
                    response.body()?.wisata?.let { callback.onAllDataReceived(it) }
                }
            }
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(TAG,"${t.message}")
            }
        })
    }

    /*fun getKebun(callback: LoadDataCallback){
        val client = apiConfig.getApiService().getKebun()
        client.enqueue(object : Callback<DataResponse>{
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                if (response.isSuccessful){
                    response.body()?.wisata?.let { callback.onAllDataReceived(it) }
                }
            }
            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(TAG,"${t.message}")
            }

        })
    }
*/
    interface LoadDataCallback{
        fun onAllDataReceived(dataResponse: List<WisataItem>)
    }
}