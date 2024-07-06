package com.bienvenu.model

import com.google.gson.annotations.SerializedName

data class User(
	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("first")
	val first: String? = null,
	@field:SerializedName("last")
	val last: String? = null,
	@field:SerializedName("gender")
	val gender: String? = null,
	@field:SerializedName("dob")
	val dob: String? = null
)
