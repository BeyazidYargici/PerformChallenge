package com.beyazid.perform.model.latests_news

import com.google.gson.annotations.SerializedName

data class LatestNewsResponse(

	@field:SerializedName("rss")
	val rss: Rss? = null
)