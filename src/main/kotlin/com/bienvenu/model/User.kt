package com.bienvenu.model

import com.google.gson.annotations.SerializedName

data class User(
	@field:SerializedName("id")
	val id: Int? = null,
	@field:SerializedName("nom")
	val nom: String? = null,
	@field:SerializedName("prenom")
	val prenom: String? = null,
	@field:SerializedName("genre")
	val genre: String? = null,
	@field:SerializedName("naissance")
	val naissance: String? = null
)
