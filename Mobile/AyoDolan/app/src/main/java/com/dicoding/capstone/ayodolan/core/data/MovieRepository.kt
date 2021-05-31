package com.dicoding.capstone.ayodolan.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.capstone.ayodolan.core.data.entity.VacationEntity
import com.dicoding.capstone.ayodolan.core.data.source.remote.RemoteDataSource
import com.dicoding.capstone.ayodolan.core.data.source.remote.response.ResultsItem

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource): MovieDataSource{

    companion object{
        @Volatile
        private var instance: MovieRepository?= null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this){
                instance ?: MovieRepository(remoteDataSource).apply { instance = this }
            }
    }

    override fun getMoviePopuler(): LiveData<List<VacationEntity>> {
        val movieResult = MutableLiveData<List<VacationEntity>>()
        remoteDataSource.getMoviePopular(object : RemoteDataSource.LoadDataCallback{
            override fun onAllDataReceived(dataResponse: List<ResultsItem>) {
                    val movieList = ArrayList<VacationEntity>()
                    for (movieRespons in dataResponse){
                        val vachEntity = VacationEntity(
                            movieRespons.id.toString(),
                            movieRespons.title,
                            movieRespons.originalTitle,
                            movieRespons.overview,
                            movieRespons.posterPath,
                            movieRespons.voteAverage.toString()
                        )
                        movieList.add(vachEntity)
                    }
                    movieResult.postValue(movieList)
            }

        })

        return movieResult
    }

    override fun getMovieRate(): LiveData<List<VacationEntity>> {
        val movieResult = MutableLiveData<List<VacationEntity>>()
        remoteDataSource.getMovieTopRated(object : RemoteDataSource.LoadDataCallback{
            override fun onAllDataReceived(dataResponse: List<ResultsItem>) {
                    val movieList = ArrayList<VacationEntity>()
                    for (movieRespons in dataResponse){
                        val vachEntity = VacationEntity(
                            movieRespons.id.toString(),
                            movieRespons.title,
                            movieRespons.originalTitle,
                            movieRespons.overview,
                            movieRespons.posterPath,
                            movieRespons.voteAverage.toString()
                        )
                        movieList.add(vachEntity)
                    }
                    movieResult.postValue(movieList)
            }

        })
        return movieResult
    }
}