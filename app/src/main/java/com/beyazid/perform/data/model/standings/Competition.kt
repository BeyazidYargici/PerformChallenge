package com.beyazid.perform.data.model.standings

import com.google.gson.annotations.SerializedName

data class Competition(

	@field:SerializedName("area_name")
	val areaName: String? = null,

	@field:SerializedName("last_updated")
	val lastUpdated: String? = null,

	@field:SerializedName("competition_id")
	val competitionId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("display_order")
	val displayOrder: String? = null,

	@field:SerializedName("season")
	val season: Season? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("area_id")
	val areaId: String? = null,

	@field:SerializedName("teamtype")
	val teamtype: String? = null,

	@field:SerializedName("soccertype")
	val soccertype: String? = null
)