package com.beyazid.perform.model.scores

import com.google.gson.annotations.SerializedName

data class Round(

	@field:SerializedName("end_date")
	val endDate: String? = null,

	@field:SerializedName("round_id")
	val roundId: String? = null,

	@field:SerializedName("last_updated")
	val lastUpdated: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("has_outgroup_matches")
	val hasOutgroupMatches: String? = null,

	@field:SerializedName("groups")
	val groups: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null,

	@field:SerializedName("group")
	val group: List<GroupItem?>? = null
)