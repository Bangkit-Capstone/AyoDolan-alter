package com.dicoding.capstone.ayodolan.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.capstone.ayodolan.core.data.entity.ReviewEntity
import com.dicoding.capstone.ayodolan.core.data.entity.VacationEntity
import com.dicoding.capstone.ayodolan.core.data.source.remote.RemoteDataSource
import com.dicoding.capstone.ayodolan.core.data.source.remote.response.ResultsItem
import com.dicoding.capstone.ayodolan.core.data.source.remote.response.WisataItem

class VacationRepository private constructor(private val remoteDataSource: RemoteDataSource): VacationDataSource{

    companion object{
        @Volatile
        private var instance: VacationRepository?= null

        fun getInstance(remoteDataSource: RemoteDataSource): VacationRepository =
            instance ?: synchronized(this){
                instance ?: VacationRepository(remoteDataSource).apply { instance = this }
            }
    }

    override fun getPantai(): LiveData<List<VacationEntity>> {
        val movieResult = MutableLiveData<List<VacationEntity>>()
        remoteDataSource.getPantai(object : RemoteDataSource.LoadDataCallback{
            override fun onAllDataReceived(dataResponse: List<WisataItem>) {
                for (vacationRespons in dataResponse){
                    Log.e("Vac","${vacationRespons.namaTempat}")
                }
            }

        })
        return movieResult
    }

    override fun getKebun(): LiveData<List<VacationEntity>> {
        val movieResult = MutableLiveData<List<VacationEntity>>()
        /*remoteDataSource.getKebun(object : RemoteDataSource.LoadDataCallback{
            override fun onAllDataReceived(dataResponse: List<WisataItem>) {
                    val movieList = ArrayList<VacationEntity>()
                    for (vacationRespons in dataResponse){
                    val vachEntity = VacationEntity(
                        vacationRespons.id.toString(),
                        vacationRespons.namaTempat,
                        "",
                        vacationRespons.ratings
                    )
                    movieList.add(vachEntity)
                }
                    movieResult.postValue(movieList)
            }

        })*/
        return movieResult
    }

}