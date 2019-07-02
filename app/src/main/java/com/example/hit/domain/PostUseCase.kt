package com.example.hit.domain

import com.example.hit.data.http.request
import com.example.hit.data.http.services.PostService

class PostUseCase(private val postService: PostService) {

    suspend fun fetchPosts(map: MutableMap<String, Any?> = mutableMapOf()) = request(postService.fetchPosts(map))
}