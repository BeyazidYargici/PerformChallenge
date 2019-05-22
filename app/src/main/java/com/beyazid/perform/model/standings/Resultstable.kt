package com.beyazid.perform.model.standings

import com.google.gson.annotations.SerializedName

data class Resultstable(

	@field:SerializedName("ranking")
	val ranking: List<RankingItem?>? = null,

	@field:SerializedName("type")
	val type: String? = null
)