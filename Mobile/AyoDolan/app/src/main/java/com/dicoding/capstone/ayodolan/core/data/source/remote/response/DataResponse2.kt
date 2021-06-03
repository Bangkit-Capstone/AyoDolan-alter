package com.dicoding.capstone.ayodolan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DataResponse2 (
    @field:SerializedName("results")
    val results: List<ResultsItem>
    )

data class ResultsItem(
@field:SerializedName("overview")
val overview: String,

@field:SerializedName("original_title")
val originalTitle: String,

@field:SerializedName("title")
val title: String,

@field:SerializedName("poster_path")
val posterPath: String,

@field:SerializedName("release_date")
val releaseDate: String,

@field:SerializedName("id")
val id: Int,

@field:SerializedName("first_air_date")
val firstAirDate: String,

@field:SerializedName("original_name")
val originalName: String,

@field:SerializedName("name")
val name: String
)