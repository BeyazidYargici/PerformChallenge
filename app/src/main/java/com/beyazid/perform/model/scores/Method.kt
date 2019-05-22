package com.beyazid.perform.model.scores

import com.google.gson.annotations.SerializedName

data class Method(

	@field:SerializedName("method_id")
	val methodId: String? = null,

	@field:SerializedName("parameter")
	val parameter: List<ParameterItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null
)