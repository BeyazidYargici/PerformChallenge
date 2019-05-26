package com.beyazid.perform.data.model.latests_news

import com.beyazid.perform.utils.GsonConverterReadableDateNews
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class LatestNewsItem(

	@field:SerializedName("enclosure")
	val enclosure: Enclosure? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("guid")
	val guid: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@JsonAdapter(GsonConverterReadableDateNews::class)
	@field:SerializedName("pubDate")
	val pubDate: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)