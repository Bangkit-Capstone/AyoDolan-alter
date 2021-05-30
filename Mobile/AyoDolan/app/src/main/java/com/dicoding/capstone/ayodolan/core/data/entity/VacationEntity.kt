package com.dicoding.capstone.ayodolan.core.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VacationEntity (
    val id :String,
    val title :String,
    val location :String,
    val description :String,
    val image :String,
    val rating :String
    ) : Parcelable