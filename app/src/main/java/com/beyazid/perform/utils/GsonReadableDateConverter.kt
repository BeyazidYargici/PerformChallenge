package com.beyazid.perform.utils

import android.annotation.SuppressLint
import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

/**
 *  I wrote the deserializer to increase the readability of dates
 *  Created by beyazid on 2019-05-26.
 */

class GsonConverterReadableDateNews : JsonDeserializer<String> {

    @SuppressLint("SimpleDateFormat")
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): String? {
        val value = (json as JsonPrimitive)
        return try {
            var formattedDate = ""
            val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("EEEE, d MMMM yy, HH:mm")
            if (value.isString) {
                val date = inputFormat.parse(value.asString)
                formattedDate = outputFormat.format(date)
            }
            formattedDate
        } catch (e: Exception) {
            value.toString().replace("\"", "")
        }
    }
}

class GsonConverterReadableDateScores : JsonDeserializer<String> {

    @SuppressLint("SimpleDateFormat")
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): String? {
        val value = (json as JsonPrimitive)
        return try {
            var formattedDate = ""
            val inputFormat = SimpleDateFormat("yyyy-mm-dd HH:mm:ss")
            val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy, HH:mm")
            if (value.isString) {
                val date = inputFormat.parse(value.asString)
                formattedDate = outputFormat.format(date)
            }
            formattedDate
        } catch (e: Exception) {
            value.toString().replace("\"", "")
        }
    }
}