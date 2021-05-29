package com.dicoding.capstone.ayodolan.core.di

import com.dicoding.capstone.ayodolan.core.data.MovieRepository
import com.dicoding.capstone.ayodolan.core.data.source.remote.RemoteDataSource
import com.dicoding.capstone.ayodolan.core.data.source.remote.network.ApiConfig

object Injection {
    fun provideRepository(): MovieRepository{
        val remoteRepository = RemoteDataSource.getInstance(ApiConfig)
        return MovieRepository.getInstance(remoteRepository)
    }
}