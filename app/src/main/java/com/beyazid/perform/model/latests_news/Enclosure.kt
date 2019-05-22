package com.beyazid.perform.model.latests_news

import com.google.gson.annotations.SerializedName

data class Enclosure(

	@field:SerializedName("length")
	val length: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)