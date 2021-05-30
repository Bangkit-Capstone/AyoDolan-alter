package com.dicoding.capstone.ayodolan.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.ayodolan.core.data.MovieRepository
import com.dicoding.capstone.ayodolan.core.data.entity.VacationEntity

class ListViewModel(private val movieRepository: MovieRepository): ViewModel(){

    fun getMoviePopuler(): LiveData<List<VacationEntity>> = movieRepository.getMoviePopuler()

    fun getMovieRate(): LiveData<List<VacationEntity>> = movieRepository.getMovieRate()
}