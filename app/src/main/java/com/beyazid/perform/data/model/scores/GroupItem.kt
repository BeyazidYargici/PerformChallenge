package com.beyazid.perform.data.model.scores

import com.google.gson.annotations.SerializedName

data class GroupItem(

	@field:SerializedName("last_updated")
	val lastUpdated: String? = null,

	@field:SerializedName("winner")
	val winner: String? = null,

	@field:SerializedName("group_id")
	val groupId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("match")
	val match: List<MatchItem?>? = null,

	@field:SerializedName("details")
	val details: String? = null
)