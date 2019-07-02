package com.example.hit.data.http.services

import kotlinx.coroutines.Deferred
import retrofit2.Response

@JvmSuppressWildcards
interface PostService {

    companion object {
        private const val SEARCH_ENDPOINT = "search_by_date"
    }

    fun fetchPosts(map: MutableMap<String, Any?>) : Deferred<Response<Any>>

}