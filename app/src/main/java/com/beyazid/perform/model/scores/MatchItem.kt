package com.beyazid.perform.model.scores

import com.google.gson.annotations.SerializedName

data class MatchItem(

	@field:SerializedName("fs_B")
	val fsB: String? = null,

	@field:SerializedName("time_utc")
	val timeUtc: String? = null,

	@field:SerializedName("ets_B")
	val etsB: String? = null,

	@field:SerializedName("last_updated")
	val lastUpdated: String? = null,

	@field:SerializedName("ets_A")
	val etsA: String? = null,

	@field:SerializedName("date_london")
	val dateLondon: String? = null,

	@field:SerializedName("match_id")
	val matchId: String? = null,

	@field:SerializedName("team_B_name")
	val teamBName: String? = null,

	@field:SerializedName("time_london")
	val timeLondon: String? = null,

	@field:SerializedName("ps_A")
	val psA: String? = null,

	@field:SerializedName("ps_B")
	val psB: String? = null,

	@field:SerializedName("date_utc")
	val dateUtc: String? = null,

	@field:SerializedName("winner")
	val winner: String? = null,

	@field:SerializedName("team_A_id")
	val teamAId: String? = null,

	@field:SerializedName("team_B_id")
	val teamBId: String? = null,

	@field:SerializedName("hts_A")
	val htsA: String? = null,

	@field:SerializedName("team_A_name")
	val teamAName: String? = null,

	@field:SerializedName("team_B_country")
	val teamBCountry: String? = null,

	@field:SerializedName("gameweek")
	val gameweek: String? = null,

	@field:SerializedName("hts_B")
	val htsB: String? = null,

	@field:SerializedName("team_A_country")
	val teamACountry: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("fs_A")
	val fsA: String? = null
)