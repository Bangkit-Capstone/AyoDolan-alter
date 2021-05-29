package com.dicoding.capstone.ayodolan.core.data

import androidx.lifecycle.LiveData
import com.dicoding.capstone.ayodolan.core.data.entity.BeachEntity

interface MovieDataSource {
    fun getMoviePopuler(): LiveData<List<BeachEntity>>

    fun getMovieRate(): LiveData<List<BeachEntity>>
}