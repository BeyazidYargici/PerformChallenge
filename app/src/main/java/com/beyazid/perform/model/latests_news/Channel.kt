package com.beyazid.perform.model.latests_news

import com.beyazid.perform.utils.GsonConverterReadableDate
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class Channel(

	@field:SerializedName("item")
	val item: List<LatestNewsItem?>? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: List<CategoryItem?>? = null,

	@JsonAdapter(GsonConverterReadableDate::class)
	@field:SerializedName("pubDate")
	val pubDate: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)