package com.dicoding.capstone.ayodolan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DataResponse(

	@field:SerializedName("wisata")
	val wisata: List<WisataItem>
)

data class ReviewItem(

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("desc")
	val desc: String,

	@field:SerializedName("username")
	val username: String
)

data class WisataItem(

	@field:SerializedName("id_kategori")
	val idKategori: Int,

	@field:SerializedName("ratings")
	val ratings: Double,

	@field:SerializedName("review")
	val review: List<ReviewItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("nama_tempat")
	val namaTempat: String
)
