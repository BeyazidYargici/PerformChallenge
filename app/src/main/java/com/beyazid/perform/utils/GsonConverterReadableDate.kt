package com.beyazid.perform.utils

import android.annotation.SuppressLint
import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat

/**
 *  I wrote the deserializer to increase the readability of dates
 *  Created by beyazid on 2019-05-19.
 */
class GsonConverterReadableDate : JsonDeserializer<String> {

    @SuppressLint("SimpleDateFormat")
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): String? {
        val value = (json as JsonPrimitive)
        try {
            var formattedDate = ""
//        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z")
            val outputFormat = SimpleDateFormat("EEEE, d MMMM yy, HH:mm")
            if (value.isString) {
                val date = inputFormat.parse(value.asString)
                formattedDate = outputFormat.format(date)
            }
            return formattedDate
        } catch (e: Exception) {
            return value.toString().replace("\"","")
        }
    }
}