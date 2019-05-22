package com.beyazid.perform.model.scores

import com.google.gson.annotations.SerializedName

data class ScoresResponse(

	@field:SerializedName("gsmrs")
	val gsmrs: Gsmrs? = null
)