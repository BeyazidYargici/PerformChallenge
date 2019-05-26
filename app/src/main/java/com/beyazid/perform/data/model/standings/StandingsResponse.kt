package com.beyazid.perform.data.model.standings

import com.google.gson.annotations.SerializedName

data class StandingsResponse(

	@field:SerializedName("gsmrs")
	val gsmrs: Gsmrs? = null
)