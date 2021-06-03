package com.dicoding.capstone.ayodolan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.ayodolan.core.data.entity.ReviewEntity
import com.dicoding.capstone.ayodolan.core.data.entity.VacationEntity
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainViewModel : ViewModel() {

    fun getPantai(): LiveData<ArrayList<VacationEntity>>{

        val listVacation = MutableLiveData<ArrayList<VacationEntity>>()
        val listVac = ArrayList<VacationEntity>()
        val url = "http://35.225.226.73/wisata/category/1"
        val client = AsyncHttpClient()
        client.addHeader("User-Agent","request")
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String((it)) }
                val responsObject = JSONObject(result!!)
                val list = responsObject.getJSONArray("wisata")

                for (i in 0 until list.length()){
                    val vac = list.getJSONObject(i)
                    val review = vac.getJSONArray("review")
                    val listReview = ArrayList<ReviewEntity>()

                    for (x in 0 until review.length()){
                        val rv = review.getJSONObject(x)
                        val rev = ReviewEntity(
                            rv.getString("username"),
                            rv.getString("desc"),
                            rv.getString("category")
                        )
                        listReview.add(rev)
                    }

                    val items = VacationEntity(
                        vac.getInt("id").toString(),
                        vac.getString("nama_tempat"),
                        "",
                        vac.getDouble("ratings").toString(),
                        listReview
                    )
                    listVac.add(items)
                }
                listVacation.postValue(listVac)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.e("TAG","Error")
            }

        })
        return listVacation
    }

    /*fun getGunung(): LiveData<ArrayList<VacationEntity>>{

        val listVacation = MutableLiveData<ArrayList<VacationEntity>>()
        val listVac = ArrayList<VacationEntity>()
        val url = "http://35.225.226.73/wisata/category/2"
        val client = AsyncHttpClient()
        client.addHeader("User-Agent","request")
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String((it)) }
                val responsObject = JSONObject(result!!)
                val list = responsObject.getJSONArray("wisata")

                for (i in 0 until list.length()){
                    val vac = list.getJSONObject(i)
                    val items = VacationEntity(
                        vac.getInt("id").toString(),
                        vac.getString("nama_tempat"),
                        "",
                        vac.getDouble("ratings").toString(),

                    )
                    listVac.add(items)
                }
                listVacation.postValue(listVac)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.e("TAG","Error")
            }

        })
        return listVacation
    }

    fun getKebun(): LiveData<ArrayList<VacationEntity>>{

        val listVacation = MutableLiveData<ArrayList<VacationEntity>>()
        val listVac = ArrayList<VacationEntity>()
        val url = "http://35.225.226.73/wisata/category/3"
        val client = AsyncHttpClient()
        client.addHeader("User-Agent","request")
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String((it)) }
                val responsObject = JSONObject(result!!)
                val list = responsObject.getJSONArray("wisata")

                for (i in 0 until list.length()){
                    val vac = list.getJSONObject(i)
                    val items = VacationEntity(
                        vac.getInt("id").toString(),
                        vac.getString("nama_tempat"),
                        "",
                        vac.getDouble("ratings").toString()

                    )
                    listVac.add(items)
                }
                listVacation.postValue(listVac)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.e("TAG","Error")
            }

        })
        return listVacation
    }

    fun getCagar(): LiveData<ArrayList<VacationEntity>>{

        val listVacation = MutableLiveData<ArrayList<VacationEntity>>()
        val listVac = ArrayList<VacationEntity>()
        val url = "http://35.225.226.73/wisata/category/4"
        val client = AsyncHttpClient()
        client.addHeader("User-Agent","request")
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?
            ) {
                val result = responseBody?.let { String((it)) }
                val responsObject = JSONObject(result!!)
                val list = responsObject.getJSONArray("wisata")

                for (i in 0 until list.length()){
                    val vac = list.getJSONObject(i)
                    val items = VacationEntity(
                        vac.getInt("id").toString(),
                        vac.getString("nama_tempat"),
                        "",
                        vac.getDouble("ratings").toString()

                    )
                    listVac.add(items)
                }
                listVacation.postValue(listVac)
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable?
            ) {
                Log.e("TAG","Error")
            }

        })
        return listVacation
    }*/
}