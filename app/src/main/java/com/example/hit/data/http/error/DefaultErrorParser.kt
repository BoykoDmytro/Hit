package com.example.hit.data.http.error

import com.example.hit.data.http.ErrorResult
import com.example.hit.data.http.RestServiceFactory
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import com.google.gson.JsonParser
import java.lang.StringBuilder


object DefaultErrorParser : ErrorParser {

    private const val EMAIL_KEY = "email"
    private const val FIELDS_KEY = "fields"
    private const val ERRORS_FIELD_KEY = "errors"
    private const val DESCRIPTION_KEY = "description"

    private val parser = JsonParser()

    override fun parseError(response: Response<*>): ErrorResult {
        val stringMessage = response.errorBody()!!.string()
        val errorJSON = parser.parse(stringMessage)
        val description = when {
            errorJSON.isJsonObject -> {
                val errorObject = errorJSON.asJsonObject
                if (errorObject.has(FIELDS_KEY)) {
                    parseFieldsErrors(errorObject[FIELDS_KEY].asJsonObject)
                } else {
                    val jsonErrorResult = parser.parse(stringMessage)
                    val element = RestServiceFactory.GSON.fromJson(
                            jsonErrorResult,
                            DefaultErrorParser.ServerError::class.java
                    )
                    element.description ?: element.message
                }
            }
            else -> null
        }
        return ErrorResult(response.code(), description)
    }

    private fun parseFieldsErrors(fieldsErrosJsonObject: JsonObject) : String{
        val builder = StringBuilder()
        fieldsErrosJsonObject.keySet().forEach { errorName ->
            val field = fieldsErrosJsonObject[errorName].asJsonObject
            val errors = field[ERRORS_FIELD_KEY].asJsonArray
            errors.forEach { element ->
                element.takeIf { it.isJsonObject }
                    ?.asJsonObject
                    ?.let {
                        when {
                            errorName.startsWith(EMAIL_KEY) -> {
                                builder.append("$errorName - ${it[DESCRIPTION_KEY].asString}\n")
                            }
                            else -> builder.append("${it[DESCRIPTION_KEY].asString}\n")
                        }
                    }
            }
        }
        return builder.toString()
    }

    private data class ServerError(
        @SerializedName("error") val error: String?,
        @SerializedName("error_description") val description: String?,
        val message: String
    )

}