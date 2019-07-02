package com.example.hit.data.http.error

import com.example.hit.data.http.ErrorResult
import retrofit2.Response

interface ErrorParser {

    fun parseError(response: Response<*>): ErrorResult
}