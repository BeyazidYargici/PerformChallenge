package com.beyazid.perform.data.model.scores

import com.google.gson.annotations.SerializedName

data class Gsmrs(

	@field:SerializedName("method")
	val method: Method? = null,

	@field:SerializedName("last_generated")
	val lastGenerated: String? = null,

	@field:SerializedName("competition")
	val competition: Competition? = null,

	@field:SerializedName("lang")
	val lang: String? = null,

	@field:SerializedName("version")
	val version: String? = null,

	@field:SerializedName("sport")
	val sport: String? = null
)