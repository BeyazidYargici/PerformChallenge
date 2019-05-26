package com.beyazid.perform.data.model.standings

import com.google.gson.annotations.SerializedName

data class Season(

	@field:SerializedName("end_date")
	val endDate: String? = null,

	@field:SerializedName("last_updated")
	val lastUpdated: String? = null,

	@field:SerializedName("round")
	val round: Round? = null,

	@field:SerializedName("service_level")
	val serviceLevel: String? = null,

	@field:SerializedName("last_playedmatch_date")
	val lastPlayedmatchDate: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("season_id")
	val seasonId: String? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null
)