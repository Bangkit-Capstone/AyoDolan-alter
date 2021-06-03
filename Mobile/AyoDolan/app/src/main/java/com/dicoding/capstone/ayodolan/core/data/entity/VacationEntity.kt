package com.dicoding.capstone.ayodolan.core.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VacationEntity (
    val id :String,
    val title :String,
    val image :String,
    val rating :String,
    val review: List<ReviewEntity>
    ) : Parcelable