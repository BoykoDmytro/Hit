package com.example.hit.data.http

import com.example.hit.data.http.error.DefaultErrorParser
import com.example.hit.data.http.error.ErrorParser
import kotlinx.coroutines.Deferred
import retrofit2.Response
import java.io.IOException

const val CONNECTION_ERROR = -1


suspend fun <T> request(call: Deferred<Response<T>>, parser: ErrorParser = DefaultErrorParser) = tryRequest(call) {
    when (it.isSuccessful) {
        true -> Success(it.body())
        else -> parser.parseError(it)
    }
}

suspend fun <T> tryRequest(call: Deferred<Response<T>>, onSuccess: (Response<T>) -> PendingResult) = try {
    onSuccess(call.await())
} catch (ex: IOException) {
    ErrorResult(CONNECTION_ERROR, ex.message)
}
