package com.beyazid.perform.model.latests_news

import com.google.gson.annotations.SerializedName

data class Rss(

	@field:SerializedName("channel")
	val channel: Channel? = null,

	@field:SerializedName("version")
	val version: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)