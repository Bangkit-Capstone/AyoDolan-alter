package com.dicoding.capstone.ayodolan.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.ayodolan.core.data.VacationRepository
import com.dicoding.capstone.ayodolan.core.data.entity.VacationEntity

class ListViewModel(private val vacationRepository: VacationRepository): ViewModel(){

    fun getPantai(): LiveData<List<VacationEntity>> = vacationRepository.getPantai()

    fun getKebun(): LiveData<List<VacationEntity>> = vacationRepository.getKebun()

}