package com.dicoding.capstone.ayodolan.core.data

import androidx.lifecycle.LiveData
import com.dicoding.capstone.ayodolan.core.data.entity.VacationEntity

interface VacationDataSource {
    fun getPantai(): LiveData<List<VacationEntity>>

    fun getKebun(): LiveData<List<VacationEntity>>
}