package com.dicoding.capstone.ayodolan.core.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewEntity (
    val username :String,
    val review :String,
    val category :String
        ) : Parcelable