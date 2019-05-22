package com.beyazid.perform.model.standings

import com.google.gson.annotations.SerializedName

data class ParameterItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("value")
	val value: String? = null
)