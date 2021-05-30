package com.dicoding.capstone.ayodolan.core.data

import androidx.lifecycle.LiveData
import com.dicoding.capstone.ayodolan.core.data.entity.VacationEntity

interface MovieDataSource {
    fun getMoviePopuler(): LiveData<List<VacationEntity>>

    fun getMovieRate(): LiveData<List<VacationEntity>>
}