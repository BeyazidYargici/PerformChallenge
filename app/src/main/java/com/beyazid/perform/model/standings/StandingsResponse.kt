package com.beyazid.perform.model.standings

import com.google.gson.annotations.SerializedName

data class StandingsResponse(

	@field:SerializedName("gsmrs")
	val gsmrs: Gsmrs? = null
)