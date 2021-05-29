package com.dicoding.capstone.ayodolan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.capstone.ayodolan.core.data.MovieRepository
import com.dicoding.capstone.ayodolan.core.di.Injection
import com.dicoding.capstone.ayodolan.ui.list.ListViewModel

class ViewModelFactory private constructor(private val mMovieRepository: MovieRepository): ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this){
                ViewModelFactory(Injection.provideRepository()).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ListViewModel::class.java) -> {
                ListViewModel(mMovieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}