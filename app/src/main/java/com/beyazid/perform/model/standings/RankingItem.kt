package com.beyazid.perform.model.standings

import com.google.gson.annotations.SerializedName

data class RankingItem(

	@field:SerializedName("countrycode")
	val countrycode: String? = null,

	@field:SerializedName("matches_won")
	val matchesWon: String? = null,

	@field:SerializedName("last_rank")
	val lastRank: String? = null,

	@field:SerializedName("team_id")
	val teamId: String? = null,

	@field:SerializedName("club_name")
	val clubName: String? = null,

	@field:SerializedName("area_id")
	val areaId: String? = null,

	@field:SerializedName("points")
	val points: String? = null,

	@field:SerializedName("matches_draw")
	val matchesDraw: String? = null,

	@field:SerializedName("matches_lost")
	val matchesLost: String? = null,

	@field:SerializedName("goals_against")
	val goalsAgainst: String? = null,

	@field:SerializedName("rank")
	val rank: String? = null,

	@field:SerializedName("goals_pro")
	val goalsPro: String? = null,

	@field:SerializedName("zone_end")
	val zoneEnd: String? = null,

	@field:SerializedName("matches_total")
	val matchesTotal: String? = null,

	@field:SerializedName("zone_start")
	val zoneStart: String? = null
)