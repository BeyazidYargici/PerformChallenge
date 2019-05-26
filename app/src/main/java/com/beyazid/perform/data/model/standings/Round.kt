package com.beyazid.perform.data.model.standings

import com.google.gson.annotations.SerializedName

data class Round(

	@field:SerializedName("end_date")
	val endDate: String? = null,

	@field:SerializedName("round_id")
	val roundId: String? = null,

	@field:SerializedName("resultstable")
	val resultstable: Resultstable? = null,

	@field:SerializedName("ordermethod")
	val ordermethod: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("has_outgroup_matches")
	val hasOutgroupMatches: String? = null,

	@field:SerializedName("groups")
	val groups: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null
)