package com.example.hit.data.http.services

import com.example.hit.data.models.HitPaginationModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

@JvmSuppressWildcards
interface PostService {

    companion object {
        private const val SEARCH_ENDPOINT = "/api/v1/search_by_date"
    }

    @GET(SEARCH_ENDPOINT)
    fun fetchPosts(@QueryMap map: Map<String, Any?>) : Deferred<Response<HitPaginationModel>>

}