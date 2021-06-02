package com.dicoding.capstone.ayodolan.core.di

import com.dicoding.capstone.ayodolan.core.data.VacationRepository
import com.dicoding.capstone.ayodolan.core.data.source.remote.RemoteDataSource
import com.dicoding.capstone.ayodolan.core.data.source.remote.network.ApiConfig

object Injection {
    fun provideRepository(): VacationRepository{
        val remoteRepository = RemoteDataSource.getInstance(ApiConfig)
        return VacationRepository.getInstance(remoteRepository)
    }
}