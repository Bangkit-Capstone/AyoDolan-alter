package com.dicoding.capstone.ayodolan.core.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewEntity (
    val id :String,
    val username :String,
    val review :String,
    val rating :String
        ) : Parcelable